package ui;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import weather.ShortForecast;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;


public class ShortForecastUI extends JPanel {
	private ShortForecastEntryUI[] shortForecast = new ShortForecastEntryUI[8];
	/**
	 * Create the panel.
	 */
	public ShortForecastUI(ShortForecast data, int unit) {
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.setSize(170,80*8);
		setLayout(null);
		for(int i = 0; i < 8; i++){
			shortForecast[i] = new ShortForecastEntryUI();
			shortForecast[i].setLocation(0, 80*i);
			shortForecast[i].getTimeLabel().setText(data.getTime(i));
			shortForecast[i].getWeatherLabel().setText(data.getWeather(i));
			try{
				BufferedImage image = ImageIO.read(new URL("http://openweathermap.org/img/w/" + data.getIcon(i)+".png"));
				shortForecast[i].getIconLabel().setIcon(new ImageIcon(image));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			shortForecast[i].getTempLabel().setText(data.getTemp(i,unit));
			this.add(shortForecast[i]);
		}
	}

}
