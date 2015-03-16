
package ui;

import data.Query;
import weather.CurrentWeather;
import weather.LongForecast;
import weather.MarsWeather;
import weather.ShortForecast;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Main object is the runnable Object
 * This object generate the main frame and paint all the subpanel
 * This object handle the exception for unsolveable location input
 * Use multiple thread to keep the main window from freezing when data is pulling
 * 
 * @author ca.uwo.csd.cs2212.team8
 */

public class Main{
	// attributes
	private static TodayPanel tpanel; // panel for current weather
	private static SForecastPanel[] spanelArray; // array of panels for short term forecast
	private static LForecastPanel[] lpanelArray; // array of panels for long term forecast
	private static PreferenceUI preference; // frame for preference window
	private static JFrame frame; // frame for main frame
	private static CurrentWeather cdata; // data for current weather
	private static MarsWeather mdata; // data for Mars weather
	private static ShortForecast sdata; // data for short term forecast
	private static LongForecast ldata; // data for long term forecast
	private static Thread t1,t2,t3; // thread for pulling data
	private static int height; // height of main frame
	
	
	/**
	 * Program start point
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		// if the operating system is Mac or window, height of main frame is 949
		if(System.getProperty("os.name").toLowerCase().startsWith("win") || System.getProperty("os.name").toLowerCase().startsWith("mac")){
			height = 949;
		}else{
		// if the operating system is Ubuntu, height of main frame is 919
			height = 919;
		}
		// helper method to generate the frame and display it
		init();
		
		try {
			// try to read the local Preference file
			// if preference exists, use it as data to refres all the panels
			preference = new PreferenceUI(".Preference");
			refresh();
		} catch (Exception e){
			// if the local file don't exist
			// if the preference UI has not been create, create a new one
			if(preference == null)
				preference = new PreferenceUI();
			else{
				// if the preference UI has alredady exist, show it
				preference.setVisible(true);
			}
		}	
	}
	
	/**
	 * helper method to refresh main window according to the preference object
	 * it use multiple threads to keep the windows from being frozen
	 * @param location The location for the data to pull about
	 * @param tempUnit unit for temperature
	 */
	public static void refresh(String location, int tempUnit){
		// disconnect all the previous threads
		t1 = null;
		t2 = null;
		t3 = null;
		
		// if the location is Mars
		if(location.toLowerCase().equals("mars")){
			// use two thread, one for pulling data, the other one for resize the window
			// thread to resize the window
			EventQueue.invokeLater(new Thread(new Runnable(){
				@Override
				public void run() {
					// the mars frame is 640 smaller in height of the full window
					frame.setSize(520,Main.getHeight()-640);
				}		
			}));
			// thread to pull data for Mars
			t1 = new Thread(new Runnable(){
				@Override
				public void run(){
					// get the data and update the Mars data of the main windows
					Query q = new Query(null, 3);
					MarsWeather mdata = new MarsWeather(q.toString());
					Main.setMdata(mdata);
					// call the refresh mehtod of today panel to refresh it
					Main.tpanel.refreshMars(Main.preference.getUnitPref());
					Main.tpanel.repaint();
				}
			});
			t1.start();
			}
		
		// if the location is not Mars
		else{
			// use four threads, three for pulling data for current, long and short, the other one for resize the window
			// thread to resize the window
			EventQueue.invokeLater(new Thread(new Runnable(){
				@Override
				public void run() {
					// set the window to the full size
					frame.setSize(520,Main.getHeight());
					}
			}));
			
			// thread to pull current weather data
			t1 = new Thread(new Runnable(){
				@Override
				public void run(){
					// get the data and update the current weather data of the main Windows
					Query q = new Query(Main.preference.getLocationPref(), 0);
					CurrentWeather cdata = new CurrentWeather(q.toString());
					Main.setCdata(cdata);
					// call the refresh method of today panel to refresh it
					Main.tpanel.refresh(Main.preference.getUnitPref());
					Main.tpanel.repaint();
				}
			});
			
			// thread to pull short term forecast data
			t2 = new Thread(new Runnable(){
				@Override
				public void run(){
					// get the data and update the short term data of the main windows
					Query q = new Query(Main.preference.getLocationPref(), 1);
					ShortForecast sdata = new ShortForecast(q.toString());
					Main.setSdata(sdata);
					// call the refresh method to refresh each of 8 short term entry
					for(int i = 0; i < 8; i++){
						Main.spanelArray[i].refresh(i, Main.preference.getUnitPref());
						Main.spanelArray[i].repaint();
					}
				}
			});
			
			// thread to pull the long forecast data
			t3 = new Thread(new Runnable(){
				@Override
				public void run(){
					// get the data and update the long term data of the main windows
					Query q = new Query(Main.preference.getLocationPref(), 2);
					LongForecast ldata = new LongForecast(q.toString());
					Main.setLdata(ldata);
					// call the refresh method to refresh each of 5 long term entry
					for(int i = 0; i < 5; i++){
						Main.lpanelArray[i].refresh(i, Main.preference.getUnitPref());
						Main.lpanelArray[i].repaint();
					}
				}	
			});
			
			// start all the pulling thread
			t1.start();
			t2.start();
			t3.start();	
		}
	}
	
	/**
	 * method to refresh temperature unit
	 * @param location the location of data to pull about
	 * @param unit the unit of temperature 0 - K, 1 - C, 2 - F
	 */
	public static void refreshUnit(String location, int unit){
		// if it is mars weather
		// update mars unit
		if(location.toLowerCase().equals("mars")){
			tpanel.refreshMarsUnit(unit);
		}else{
			// if it is from open weather map
			// refresh all the panel's unit
			tpanel.refreshUnit(unit);
			for(int i = 0; i < 5; i++){
				lpanelArray[i].refreshUnit(i,  unit);
			}
			for(int i = 0; i < 8; i++){
				spanelArray[i].refreshUnit(i, unit);
			}
		}
	}
	
	/**
	 * method to refresh the frame, with the preference got by the Main window
	 */
	public static void refresh(){
		refresh(preference.getLocationPref(), preference.getUnitPref());
	}
	
	/**
	 * helper method to initialize windows and show it
	 */
	private static void init(){
		// create the main frame with 8_TheWeather as title
		// the frame is not resizable
		// when click the close button, stop the program
		// use absolute layout
		// the frame use absolute size
		frame = new JFrame("8_TheWeather");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(520,height);
		
		// initiate the today panel for current weahter and mars weather
		// the windoe is at the very top of the window
		tpanel = new TodayPanel();
		frame.add(tpanel);
		tpanel.setLocation(0, 0);
		
		// create a panel container for short term forecasts
		// the container use box layout
		JPanel spanels = new JPanel();
		spanels.setLayout(new BoxLayout(spanels,BoxLayout.PAGE_AXIS));
		spanels.setSize(new Dimension(260,80*8));
		spanelArray = new SForecastPanel[8];
		// put all the short term into the container
		for(int i=0;i<spanelArray.length;i++){
			spanelArray[i]=new SForecastPanel();
			spanels.add(spanelArray[i]);
		}
		// put the container into the main frame
		frame.add(spanels);
		// the container is to the very left of the frame and below the today panel
		spanels.setLocation(0, 280);

		// create a panel container for long term forecasts
		// the container use box layout
		JPanel lpanels = new JPanel();
		lpanels.setLayout(new BoxLayout(lpanels,BoxLayout.PAGE_AXIS));
		lpanels.setSize(new Dimension(260,128*5));
		lpanelArray=new LForecastPanel[5];
		// put all the long term into the containers
		for(int i=0;i<lpanelArray.length;i++){
			lpanelArray[i]=new LForecastPanel();
			lpanels.add(lpanelArray[i]);
		}
		// put the container into the frame
		// long term forecast is below today panel and to the right of the short temr forecast
		frame.add(lpanels);
		lpanels.setLocation(260, 280);
		
		// show the main frame
		frame.setVisible(true);
	}
	
	/**
	 * method to test whether ther is at least an older data stored in Main frame
	 * @return true if there is no risk for null pointer exception; false, otherwise
	 */
	public static boolean refreshed(){
		if(preference.getLocationPref().equalsIgnoreCase("mars")){
			return mdata != null;
		}
		else{
			return cdata != null && sdata != null && ldata != null;
		}
	}
	
	/**
	 * method to show the eroor message box when the location format is wrong
	 */
	public static void wrongLocationFormat(){
		JOptionPane.showMessageDialog(null, "example: cityname,two character country code or Mars", "Wrong city name format", JOptionPane.INFORMATION_MESSAGE);
		preference.showPreference();
	}
	
	/**
	 * methdo to show the error message box when the server cannot guess the location based on the input location
	 */
	public static void wrongLocation(){
		JOptionPane.showMessageDialog(null, "Server cannot guess base on your input", "Wrong city name", JOptionPane.INFORMATION_MESSAGE);
		preference.showPreferenceDefault();
	}
	
	/**
	 * method to interrupt all the thread to prevent duplicate threading
	 */
	public static void interrupt(){
		if(t3!= null){
			t3.stop();
		}
		if(t2!= null){
			t2.stop();
		}
		if(t1!= null){
			t1.stop();
		}
		System.out.println("Interupt");
	}
	
	/**
	 * method for preference button in today panel to show preference window
	 */
	public static void showPreference(){
		preference.showPreference();
	}

	/**
	 * method for refresh of current weather to get the current weather data
	 * @return the cdata Current Weather object containing the most successfully pulled data
	 */
	public static CurrentWeather getCdata() {
		return cdata;
	}

	/**
	 * method for refresh of Mars weather to get the mar weather data
	 * @return the mdata Mars weather object containing the most successfully pull data
	 */
	public static MarsWeather getMdata() {
		return mdata;
	}

	/**
	 * method for refresh of short forecast weather to get the mar weather data
	 * @return the sdata short forecast object containing the most successfully pull data
	 */
	public static ShortForecast getSdata() {
		return sdata;
	}

	/**
	 * method for refresh of long forecast weather to get the mar weather data
	 * @return the ldata long forecast object containing the most successfully pull data
	 */
	public static LongForecast getLdata() {
		return ldata;
	}

	/**
	 * method for pulling thread to update current weather data in main windows
	 * @param cdata the current weahter data to set
	 */
	public static void setCdata(CurrentWeather cdata) {
		Main.cdata = cdata;
	}

	/**
	 * method for pulling thread to update mars weather data in main windows
	 * @param mdata the mars weather data to set
	 */
	public static void setMdata(MarsWeather mdata) {
		Main.mdata = mdata;
	}

	/**
	 * method for pulling thread to update short forecast data in main windows
	 * @param sdata the short forecast data to set
	 */
	public static void setSdata(ShortForecast sdata) {
		Main.sdata = sdata;
	}

	/**
	 * method for pulling thread to update long forecast data in main windows
	 * @param ldata the long forecast data to set
	 */
	public static void setLdata(LongForecast ldata) {
		Main.ldata = ldata;
	}
	
	/**
	 * method for resizing thread to get the height of main frame
	 * @return height height of the full main window
	 */
	public static int getHeight(){
		return height;
	}
}
