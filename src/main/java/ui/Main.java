
package ui;

import data.JSONException;
import weather.CurrentWeather;
import weather.LongForecast;
import weather.ShortForecast;
import io.Preference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Main Window Class
 * @author team8
 */

public class Main {
	// three main panel for current weather, short forecast and long forecast
	private static TodayPanel tpanel;
	private static SForecastPanel[] spanelArray;
	private static LForecastPanel[] lpanelArray;
	private static PreferenceUI preference;
	
	/**
	 * the program starts here
	 * @param args parameter from command line
	 */
	public static void main(String[] args) {
		preference = new PreferenceUI();
		init();
		while(preference.isVisible()){
			System.out.print("");
		}
		//System.out.println("refresh main window");
		//System.out.println(preference.getLocationPref() +"\n" + preference.getUnitPref());
		refresh(preference.getLocationPref(), preference.getUnitPref());
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
	
}
