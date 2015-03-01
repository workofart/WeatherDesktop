package ui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import weather.LongForecast;
import weather.ShortForecast;

public class LongForecastUI extends JPanel {
	private LongForecastEntryUI[] longForecast;
	/**
	 * Create the panel.
	 */
	public LongForecastUI(LongForecast data, int unit) {
		longForecast = new LongForecastEntryUI[5];
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.setSize(150,128*5);
		setLayout(null);
		for(int i = 0; i < 5; i++){
			longForecast[i] = new LongForecastEntryUI();
			longForecast[i].setLocation(0, 128*i);
			longForecast[i].getTime_label().setText(data.getTime(i));
			longForecast[i].getWeather_label().setText(data.getWeather(i));
			try{
				BufferedImage image = ImageIO.read(new URL("http://openweathermap.org/img/w/" + data.getIcon(i)+".png"));
				longForecast[i].getIcon_label().setIcon(new ImageIcon(image));
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			longForecast[i].getTemp_label().setText(data.getTemp(i,unit));
			longForecast[i].getHumidity_label().setText(data.getHumidity(i));
			longForecast[i].getPressure_lable().setText(data.getPressure(i));
			longForecast[i].getMin_label().setText(data.getMinTemp(i,unit));
			longForecast[i].getMax_label().setText(data.getMaxTemp(i, unit));
			this.add(longForecast[i]);
		}
	}

}
