package ui;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class ShortForecastEntryUI extends JPanel {

	private JLabel time_label, weather_label, icon_label, temp_label;
	/**
	 * Create the panel.
	 */
	public ShortForecastEntryUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setSize(170,80);
		setLayout(null);
		
		time_label = new JLabel("");
		time_label.setToolTipText("Time for forecast");
		time_label.setHorizontalAlignment(SwingConstants.CENTER);
		time_label.setBounds(0, 0, 82, 14);
		add(time_label);
		
		weather_label = new JLabel("");
		weather_label.setToolTipText("Weather Name");
		weather_label.setHorizontalAlignment(SwingConstants.CENTER);
		weather_label.setBounds(92, 0, 69, 14);
		add(weather_label);
		
		icon_label = new JLabel("");
		icon_label.setToolTipText("Weather Icon");
		icon_label.setBounds(0, 30, 50, 50);
		add(icon_label);
		
		temp_label = new JLabel("");
		temp_label.setToolTipText("Temperature Forcast");
		temp_label.setHorizontalAlignment(SwingConstants.CENTER);
		temp_label.setBounds(92, 39, 69, 14);
		add(temp_label);
	}
	
	public JLabel getTimeLabel(){
		return time_label;
	}
	
	public JLabel getWeatherLabel(){
		return weather_label;
	}
	
	public JLabel getIconLabel(){
		return icon_label;
	}
	
	public JLabel getTempLabel(){
		return temp_label;

		
		
	}
}
