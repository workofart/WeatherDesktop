package ui;

import io.Preference;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import weather.CurrentWeather;
import weather.LongForecast;
import weather.ShortForecast;


public class Main {
	private static TodayPanel tpanel;
	private static SForecastPanel[] spanelArray;
	private static LForecastPanel[] lpanelArray;
	
	public static void main(String[] args) {
		init();
		Preference pref = new Preference();
		refresh(pref);
	}
	
	private static void refresh(Preference pref){
		int tempUnit = pref.getTempUnit();
		String location = pref.getLocation();
		CurrentWeather data = new CurrentWeather(location);
		
		tpanel.setTempLabel(data.getTemp(tempUnit));
		tpanel.setSunLabel(data.getWeather());
		tpanel.setPresLabel(data.getPressure());
		tpanel.setHumLabel(data.getHumidity());
		tpanel.setWinLabel(data.getSpeed(), data.getDirection());
		
		ShortForecast sdata = new ShortForecast(location);
		
		for(int i = 0; i < 7; i++){
			spanelArray[i].setTemp(sdata.getTemp(i, tempUnit), tempUnit);
			
			spanelArray[i].setSky(sdata.getWeather(i));
			System.out.println("short refresh " + i);
		}
		
		LongForecast ldata = new LongForecast(location);
		
		for(int i = 0; i < 5; i++){
			lpanelArray[i].setTemp(ldata.getTemp(i, tempUnit), tempUnit);
			lpanelArray[i].setSky(ldata.getWeather(i));
		}
		
	}
	
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
		tpanel=new TodayPanel();
		frame.add(tpanel,con);
		
		//*****Short Term Panels****
		
		JPanel spanels = new JPanel();
		spanels.setBackground(Color.magenta);
		spanels.setLayout(new BoxLayout(spanels,BoxLayout.PAGE_AXIS));
		spanels.setPreferredSize(new Dimension(262,(int)(910*0.7)));
		spanelArray=new SForecastPanel[7];
		for(int i=0;i<spanelArray.length;i++){
			spanelArray[i]=new SForecastPanel(i);
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
