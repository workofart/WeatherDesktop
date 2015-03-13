
package ui;

import io.Preference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import weather.CurrentWeather;
import weather.LongForecast;
import weather.ShortForecast;
/**
 * Main Window Class
 * @author team8
 */

public class Main{
	// three main panel for current weather, short forecast and long forecast
	private static TodayPanel tpanel;
	private static SForecastPanel[] spanelArray;
	private static LForecastPanel[] lpanelArray;
	private static Preference pref;
	
	/**
	 * the program starts here
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		init();
		pref = new Preference();
		refresh();
	}
	
	/**
	 * helper method to refresh main window according to the preference object
	 * @param pref the Preference object that contain all the preference data
	 */
	public static void refresh(){
		int tempUnit = pref.getTempUnit();
		String location = pref.getLocation();
		CurrentWeather data = new CurrentWeather(location);
		
		tpanel.setTempLabel(data.getTemp(tempUnit));
		tpanel.setSunLabel(data.getWeather());
		tpanel.setPresLabel(data.getPressure());
		tpanel.setHumLabel(data.getHumidity());
		tpanel.setWinLabel(data.getSpeed(), data.getDirection());
		tpanel.setIcon(data.getIcon());
		
		ShortForecast sdata = new ShortForecast(location);
		
		for(int i = 0; i < 8; i++){
			spanelArray[i].setTemp(sdata.getTemp(i, tempUnit), tempUnit);
			spanelArray[i].setIcon(sdata.getIcon(i));
			spanelArray[i].setSky(sdata.getWeather(i));
		}
		
		LongForecast ldata = new LongForecast(location);
		
		for(int i = 0; i < 5; i++){
			lpanelArray[i].setTemp(ldata.getTemp(i, tempUnit), tempUnit);
			lpanelArray[i].setSky(ldata.getWeather(i));
			lpanelArray[i].setIcon(ldata.getIcon(i));
		}
		
	}
	
	/**
	 * helper method to initialize windows
	 */
	private static void init(){
		JFrame frame = new JFrame("Stage 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		
		
		//Panel
		con.fill=GridBagConstraints.BOTH;
		con.gridx=0;
		con.gridy=0;
		con.gridwidth=2;
		con.gridheight=1;
		con.weighty=0.4;
		con.weightx=1;
		tpanel = new TodayPanel();
		frame.add(tpanel,con);
		
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
		con.fill = GridBagConstraints.BOTH;
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth=1;
		con.gridheight=1;
//		con.weightx=0.5;
		con.weighty=0.7;
		frame.add(spanels, con);

		
		
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
		con.fill = GridBagConstraints.BOTH;
		con.gridx = 1;
		con.gridy = 1;
		con.gridwidth=1;
		con.gridheight=1;
//		con.weightx=0.5;
		con.weighty=0.7;
		lpanels.setBackground(Color.magenta);
		frame.add(lpanels, con);

		
		
		
		
		
		tpanel.setLayout(null);
		frame.pack();
		frame.setSize(525,950);
		frame.setVisible(true);
	}
	/**
	 * 
	 * @param new_pref
	 * Updates preferences object of the program
	 */
	public static void choosePreferences(){
		//Pop-up window here!
	}
	
}
