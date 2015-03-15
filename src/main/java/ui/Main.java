
package ui;

import data.JSONException;
import data.Query;
import weather.CurrentWeather;
import weather.LongForecast;
import weather.MarsWeather;
import weather.ShortForecast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * Main Window Class
 * @author team8
 */

public class Main{
	// three main panel for current weather, short forecast and long forecast
	private static TodayPanel tpanel;
	private static SForecastPanel[] spanelArray;
	private static LForecastPanel[] lpanelArray;
	private static PreferenceUI preference;
	private static JFrame frame;
	private static Query q;
	private static CurrentWeather cdata;
	private static MarsWeather mdata;
	private static ShortForecast sdata;
	private static LongForecast ldata;
	private static Thread t1,t2,t3;
	/**
	 * the program starts here
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				init();	
			}
		});
		try {
			preference = new PreferenceUI(".Preference");
			refresh();
		} catch (Exception e){
			if(preference == null)
				preference = new PreferenceUI();
			else
				preference.setVisible(true);
		}
	
		
	}
	
	/**
	 * helper method to refresh main window according to the preference object
	 * @param pref the Preference object that contain all the preference data
	 */
	public static void refresh(String location, int tempUnit){
		t1 = null; t2 = null; t3 = null;
		try{
			if(location.toLowerCase().equals("mars")){
				EventQueue.invokeLater(new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub

						frame.setSize(530,310);
					}
					
				}));
				t1 = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Query q = new Query(null, 3);
						MarsWeather mdata = new MarsWeather(q.toString());
						Main.setMdata(mdata);
						Main.tpanel.refreshMars(tempUnit);
					}
					
				});
				t1.start();
			}
			else{
				EventQueue.invokeLater(new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						frame.setSize(530,933);
						frame.repaint();
					}
					
				}));
				t1 = new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Query q = new Query(location, 0);
						CurrentWeather cdata = new CurrentWeather(q.toString());
						Main.setCdata(cdata);
						Main.tpanel.refresh(tempUnit);
					}
					
				});
				t2 = new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Query q = new Query(location, 1);
						ShortForecast sdata = new ShortForecast(q.toString());
						Main.setSdata(sdata);
						for(int i = 0; i < 8; i++){
							Main.spanelArray[i].refresh(i, tempUnit);
						}

					}
					
				});
				t3 = new Thread(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Query q = new Query(location, 2);
						LongForecast ldata = new LongForecast(q.toString());
						Main.setLdata(ldata);
						for(int i = 0; i < 5; i++){
							Main.lpanelArray[i].refresh(i,  tempUnit);
							
						}
					}
					
				});
				t1.start();
				t2.start();
				t3.start();
				
			}
		}catch(JSONException e){
			JOptionPane.showMessageDialog(null, "City cannot be found", "Wrong city name", JOptionPane.INFORMATION_MESSAGE);
			preference.showPreference();
		}
		
	}
	
	public static void refreshUnit(String location, int unit){
		if(location.toLowerCase().equals("mars")){
			tpanel.refreshMarsUnit(unit);
		}else{
			tpanel.refreshUnit(unit);
			for(int i = 0; i < 5; i++){
				lpanelArray[i].refreshUnit(i,  unit);
				
			}
			for(int i = 0; i < 8; i++){
				spanelArray[i].refresh(i, unit);
			}
		}
	}
	
	public static void refresh(){
		refresh(preference.getLocationPref(), preference.getUnitPref());
	}
	public static PreferenceUI getPrefUI(){
		return preference;
	}
	
	/**
	 * helper method to initialize windows
	 */
	private static void init(){
		frame = new JFrame("8_TheWeather");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		//Panel
		tpanel = new TodayPanel();
		frame.add(tpanel,BorderLayout.PAGE_START);
		
		//*****Short Term Panels****
		
		JPanel spanels = new JPanel();
		spanels.setBackground(Color.magenta);
		spanels.setLayout(new BoxLayout(spanels,BoxLayout.PAGE_AXIS));
		spanels.setPreferredSize(new Dimension(262,(int)(910*0.7)));
		spanelArray = new SForecastPanel[8];
		for(int i=0;i<spanelArray.length;i++){
			spanelArray[i]=new SForecastPanel();
			spanels.add(spanelArray[i]);
		}
		frame.add(spanels, BorderLayout.LINE_START);

		
		
		//*****Long Term Panels****
		JPanel lpanels = new JPanel();
		
		lpanels.setBackground(Color.gray);
		lpanels.setLayout(new BoxLayout(lpanels,BoxLayout.PAGE_AXIS));
		lpanels.setPreferredSize(new Dimension(262,(int)(910*0.7)));
		lpanelArray=new LForecastPanel[5];
		for(int i=0;i<lpanelArray.length;i++){
			lpanelArray[i]=new LForecastPanel();
			lpanels.add(lpanelArray[i]);
		}
		lpanels.setBackground(Color.magenta);
		frame.add(lpanels,BorderLayout.LINE_END);

		
		tpanel.setLayout(null);
		frame.pack();
		frame.setSize(530,933);
		frame.setVisible(true);
	}
	public static boolean refreshed(){
		if(preference.getLocationPref().equalsIgnoreCase("mars")){
			return mdata != null;
		}
		else 
			return cdata != null && sdata != null && ldata != null;
	}
	public static void interupt(){
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
	 * @return the cdata
	 */
	public static CurrentWeather getCdata() {
		return cdata;
	}

	/**
	 * @return the mdata
	 */
	public static MarsWeather getMdata() {
		return mdata;
	}

	/**
	 * @return the sdata
	 */
	public static ShortForecast getSdata() {
		return sdata;
	}

	/**
	 * @return the ldata
	 */
	public static LongForecast getLdata() {
		return ldata;
	}

	/**
	 * @param cdata the cdata to set
	 */
	public static void setCdata(CurrentWeather cdata) {
		Main.cdata = cdata;
	}

	/**
	 * @param mdata the mdata to set
	 */
	public static void setMdata(MarsWeather mdata) {
		Main.mdata = mdata;
	}

	/**
	 * @param sdata the sdata to set
	 */
	public static void setSdata(ShortForecast sdata) {
		Main.sdata = sdata;
	}

	/**
	 * @param ldata the ldata to set
	 */
	public static void setLdata(LongForecast ldata) {
		Main.ldata = ldata;
	}
	
	
}
