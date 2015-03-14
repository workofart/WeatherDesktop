
package ui;

import data.JSONException;
import weather.CurrentWeather;
import weather.LongForecast;
import weather.ShortForecast;
import io.Preference;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


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
	private static Preference pref;
	
	/**
	 * the program starts here
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		init();
		//System.out.println("refresh main window");
		//System.out.println(preference.getLocationPref() +"\n" + preference.getUnitPref());
		choosePreferences();
//		refresh(preference.getLocationPref(), preference.getUnitPref());
		pref = new Preference();
	}
	
	/**
	 * helper method to refresh main window according to the preference object
	 * @param pref the Preference object that contain all the preference data
	 */
	private static void refresh(String location, int tempUnit) throws JSONException{
		if(location.toLowerCase().equals("mars")){
		}
		else{
			CurrentWeather data = new CurrentWeather(location);
			
			tpanel.setTempLabel(data.getTemp(tempUnit), tempUnit);
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
	}
	
	/**
	 * Public method to refresh
	 */	
	public static void refresh(){
		refresh(preference.getLocationPref(), preference.getUnitPref());
	}
	
	/**
	 * helper method to initialize windows
	 */
	private static void init(){
		JFrame frame = new JFrame("Stage 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
//		GridBagConstraints con=new GridBagConstraints();
		
		
		//Panel
//		con.fill=GridBagConstraints.BOTH;
//		con.gridx=0;
//		con.gridy=0;
//		con.gridwidth=2;
//		con.gridheight=1;
//		con.weighty=0.4;
//		con.weightx=1;
		tpanel = new TodayPanel();
//		tpanel.setBounds(0,0,(int)tpanel.getPreferredSize().getWidth(),(int)tpanel.getPreferredSize().getHeight());
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
//		con.fill = GridBagConstraints.BOTH;
//		con.gridx = 0;
//		con.gridy = 1;
//		con.gridwidth=1;
//		con.gridheight=1;
//		con.weighty=0.7;
//		spanels.setBounds(-100,(int)tpanel.getPreferredSize().getHeight(),(int)spanels.getPreferredSize().getWidth(),(int)spanels.getPreferredSize().getHeight());
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
//		con.fill = GridBagConstraints.BOTH;
//		con.gridx = 1;
//		con.gridy = 1;
//		con.gridwidth=1;
//		con.gridheight=1;
//		con.weighty=0.7;
		lpanels.setBackground(Color.magenta);
//		spanels.setBounds((int)spanels.getPreferredSize().getWidth(),(int)tpanel.getPreferredSize().getHeight(),(int)lpanels.getPreferredSize().getWidth(),(int)lpanels.getPreferredSize().getHeight());
		frame.add(lpanels,BorderLayout.LINE_END);

		
		tpanel.setLayout(null);
		frame.pack();
		frame.setSize(539,949);
		frame.setVisible(true);
	}
	/**
	 * 
	 * @param new_pref
	 * Updates preferences object of the program
	 */
	public static void choosePreferences(){
		preference = new PreferenceUI();
//		refresh();
	}
	
}
