
package ui;

import data.JSONException;
import weather.CurrentWeather;
import weather.LongForecast;
import weather.MarsWeather;
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
import java.util.Date;
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
	private static JFrame frame;
	/**
	 * the program starts here
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		init();
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
	public static void refresh(String location, int tempUnit) throws JSONException{
		if(location.toLowerCase().equals("mars")){
			frame.setSize(530,300);
			MarsWeather data = new MarsWeather();
			tpanel.setTempLabel(data.getTemp(tempUnit), tempUnit);
			tpanel.setSunLabel(data.getWeather());
			tpanel.setPresLabel(data.getPressure());
			tpanel.setHumLabel(data.getHumidity());
			tpanel.setWinLabel(data.getSpeed(), data.getDirection());
			tpanel.setIcon(data.getIcon());
			tpanel.setMaxMinLabel(data.getMaxTemp(tempUnit), data.getMinTemp(tempUnit), tempUnit);
			tpanel.setLocationLabel("Mars");
			tpanel.setRefreshLabel();
			
		}
		else{
			frame.setSize(530,933);
			try{
				CurrentWeather data = new CurrentWeather(location);
				tpanel.setTempLabel(data.getTemp(tempUnit), tempUnit);
				tpanel.setSunLabel(data.getWeather());
				tpanel.setPresLabel(data.getPressure());
				tpanel.setHumLabel(data.getHumidity());
				tpanel.setWinLabel(data.getSpeed(), data.getDirection());
				tpanel.setIcon(data.getIcon());
				tpanel.setMaxMinLabel(data.getMaxTemp(tempUnit), data.getMinTemp(tempUnit), tempUnit);
				tpanel.setRisetLabel(data.getSunrise(),data.getSunset());
				tpanel.setLocationLabel(data.getLocation());
				tpanel.setRefreshLabel();
			}catch(JSONException e){
				JOptionPane.showMessageDialog(null," is invalid.");
				preference.showPreference();
				return;
			}
			
			
			//if(spanelArray[0].getTime().equals("--") || new Date().toString().compareTo(spanelArray[0].getTime()) >= 0){
				ShortForecast sdata = new ShortForecast(location);
				
				for(int i = 0; i < 8; i++){
					spanelArray[i].setTemp(sdata.getTemp(i, tempUnit), tempUnit);
					spanelArray[i].setIcon(sdata.getIcon(i));
					spanelArray[i].setSky(sdata.getWeather(i));
					spanelArray[i].setTime(sdata.getTime(i));
				}
			//}
			
						
			LongForecast ldata = new LongForecast(location);
			
			for(int i = 0; i < 5; i++){
				lpanelArray[i].setTemp(ldata.getTemp(i, tempUnit), tempUnit);
				lpanelArray[i].setSky(ldata.getWeather(i));
				lpanelArray[i].setIcon(ldata.getIcon(i));
				lpanelArray[i].setMaxMin(ldata.getMaxTemp(i, tempUnit), ldata.getMinTemp(i, tempUnit), tempUnit);
				lpanelArray[i].setTime(ldata.getTime(i));
				
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
	
}
