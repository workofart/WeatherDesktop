package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import weather.CurrentWeather;

public class CurrentWeatherUI extends JPanel {
	private JLabel sunrise_label, sunset_label, weather_label, wind_direction_label, icon_label, temp_label, pressure_label, wind_speed_label, min_label, max_label, humidity_label;
	/**
	 * Create the panel.
	 */
	public CurrentWeatherUI(CurrentWeather data, int unit) {
		setBorder(new LineBorder(new Color(100, 0, 0)));
		this.setSize(488,301);
		setLayout(null);
		
		sunrise_label = new JLabel("");
		sunrise_label.setToolTipText("Time for sunrise");
		sunrise_label.setText(data.getSunrise());
		sunrise_label.setHorizontalAlignment(SwingConstants.CENTER);
		sunrise_label.setBounds(108, 11, 82, 14);
		add(sunrise_label);
		
		sunset_label = new JLabel("");
		sunset_label.setText(data.getSunset());
		sunset_label.setToolTipText("Time for sunset");
		sunset_label.setHorizontalAlignment(SwingConstants.CENTER);
		sunset_label.setBounds(0, 11, 82, 14);
		add(sunset_label);
		
		weather_label = new JLabel("");
		weather_label.setText(data.getWeather());
		weather_label.setToolTipText("Weather Name");
		weather_label.setHorizontalAlignment(SwingConstants.CENTER);
		weather_label.setBounds(121, 129, 69, 14);
		add(weather_label);
		
		wind_direction_label = new JLabel("");
		wind_direction_label.setText(data.getDirection());
		wind_direction_label.setToolTipText("Wind direction");
		wind_direction_label.setHorizontalAlignment(SwingConstants.CENTER);
		wind_direction_label.setBounds(0, 0, 82, 14);
		add(wind_direction_label);
		
		icon_label = new JLabel("");
		icon_label.setToolTipText("Weather Icon");
		icon_label.setBounds(0, 251, 50, 50);
		try{
			BufferedImage image = ImageIO.read(new URL("http://openweathermap.org/img/w/" + data.getIcon()+".png"));
			icon_label.setIcon(new ImageIcon(image));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		add(icon_label);
		
		temp_label = new JLabel("");
		temp_label.setText(data.getTemp(unit));
		temp_label.setToolTipText("Temperature Forcast");
		temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		temp_label.setBounds(156, 25, 69, 14);
		add(temp_label);
		
		pressure_label = new JLabel("");
		pressure_label.setText(data.getPressure()+"");
		pressure_label.setToolTipText("Air pressure");
		pressure_label.setHorizontalAlignment(SwingConstants.CENTER);
		pressure_label.setBounds(0, 36, 69, 14);
		add(pressure_label);
		
		wind_speed_label = new JLabel("");
		wind_speed_label.setText(data.getSpeed()+"");
		wind_speed_label.setToolTipText("Wind speed");
		wind_speed_label.setHorizontalAlignment(SwingConstants.CENTER);
		wind_speed_label.setBounds(0, 81, 82, 14);
		add(wind_speed_label);
		
		min_label = new JLabel("");
		min_label.setToolTipText("Minimum temperature");
		min_label.setText(data.getMinTemp(unit));
		min_label.setHorizontalAlignment(SwingConstants.CENTER);
		min_label.setBounds(13, 144, 69, 14);
		add(min_label);
		
		max_label = new JLabel("");
		max_label.setToolTipText("Maximum temperature");
		max_label.setText(data.getMaxTemp(unit));
		max_label.setHorizontalAlignment(SwingConstants.CENTER);
		max_label.setBounds(137, 52, 69, 14);
		add(max_label);
		
		humidity_label = new JLabel("");
		humidity_label.setText(data.getHumidity()+"");
		humidity_label.setToolTipText("Humidity");
		humidity_label.setHorizontalAlignment(SwingConstants.CENTER);
		humidity_label.setBounds(180, 96, 69, 14);
		add(humidity_label);
	}

}
