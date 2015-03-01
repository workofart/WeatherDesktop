package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class LongForecastEntryUI extends JPanel {
	private JLabel time_label, weather_label, icon_label, temp_label, humidity_label, pressure_lable, min_label, max_label;
	/**
	 * Create the panel.
	 */
	public LongForecastEntryUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setSize(150,128);
		setLayout(null);
		
		time_label = new JLabel("");
		time_label.setToolTipText("Time for forecast");
		time_label.setHorizontalAlignment(SwingConstants.CENTER);
		time_label.setBounds(0, 0, 82, 14);
		add(time_label);
		
		weather_label = new JLabel("");
		weather_label.setToolTipText("Weather Name");
		weather_label.setHorizontalAlignment(SwingConstants.CENTER);
		weather_label.setBounds(0, 11, 69, 14);
		add(weather_label);
		
		icon_label = new JLabel("");
		icon_label.setToolTipText("Weather Icon");
		icon_label.setBounds(0, 78, 50, 50);
		add(icon_label);
		
		temp_label = new JLabel("");
		temp_label.setToolTipText("Temperature Forcast");
		temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		temp_label.setBounds(0, 25, 69, 14);
		add(temp_label);
		
		humidity_label = new JLabel("");
		humidity_label.setToolTipText("Humidity");
		humidity_label.setHorizontalAlignment(SwingConstants.CENTER);
		humidity_label.setBounds(92, 0, 69, 14);
		add(humidity_label);
		
		pressure_lable = new JLabel("");
		pressure_lable.setToolTipText("Humidity");
		pressure_lable.setHorizontalAlignment(SwingConstants.CENTER);
		pressure_lable.setBounds(0, 36, 69, 14);
		add(pressure_lable);
		
		min_label = new JLabel("");
		min_label.setToolTipText("Humidity");
		min_label.setHorizontalAlignment(SwingConstants.CENTER);
		min_label.setBounds(0, 53, 69, 14);
		add(min_label);
		
		max_label = new JLabel("");
		max_label.setToolTipText("Humidity");
		max_label.setHorizontalAlignment(SwingConstants.CENTER);
		max_label.setBounds(92, 11, 69, 14);
		add(max_label);
	}
	/**
	 * @return the time_label
	 */
	public JLabel getTime_label() {
		return time_label;
	}
	/**
	 * @return the weather_label
	 */
	public JLabel getWeather_label() {
		return weather_label;
	}
	/**
	 * @return the icon_label
	 */
	public JLabel getIcon_label() {
		return icon_label;
	}
	/**
	 * @return the temp_label
	 */
	public JLabel getTemp_label() {
		return temp_label;
	}
	/**
	 * @return the humidity_label
	 */
	public JLabel getHumidity_label() {
		return humidity_label;
	}
	/**
	 * @return the pressure_lable
	 */
	public JLabel getPressure_lable() {
		return pressure_lable;
	}
	/**
	 * @return the min_label
	 */
	public JLabel getMin_label() {
		return min_label;
	}
	/**
	 * @return the max_label
	 */
	public JLabel getMax_label() {
		return max_label;
	}
	/**
	 * @param time_label the time_label to set
	 */
	public void setTime_label(JLabel time_label) {
		this.time_label = time_label;
	}
	/**
	 * @param weather_label the weather_label to set
	 */
	public void setWeather_label(JLabel weather_label) {
		this.weather_label = weather_label;
	}
	/**
	 * @param icon_label the icon_label to set
	 */
	public void setIcon_label(JLabel icon_label) {
		this.icon_label = icon_label;
	}
	/**
	 * @param temp_label the temp_label to set
	 */
	public void setTemp_label(JLabel temp_label) {
		this.temp_label = temp_label;
	}
	/**
	 * @param humidity_label the humidity_label to set
	 */
	public void setHumidity_label(JLabel humidity_label) {
		this.humidity_label = humidity_label;
	}
	/**
	 * @param pressure_lable the pressure_lable to set
	 */
	public void setPressure_lable(JLabel pressure_lable) {
		this.pressure_lable = pressure_lable;
	}
	/**
	 * @param min_label the min_label to set
	 */
	public void setMin_label(JLabel min_label) {
		this.min_label = min_label;
	}
	/**
	 * @param max_label the max_label to set
	 */
	public void setMax_label(JLabel max_label) {
		this.max_label = max_label;
	}

}
