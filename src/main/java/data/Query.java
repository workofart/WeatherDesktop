package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import ui.Main;
import weather.CurrentWeather;
import weather.MarsWeather;
/**
 * class to fetch data from Internet
 * @author Team8
 */
public class Query {
	private String JSon;
	private String address;
	private final int CURRENT_WEATHER = 0,
					  THREE_HOUR_FORECAST = 1,
					  DAILY_FORECAST = 2;
	private int type;
	/**
	 * Get JSon file for the location according to forecast type
	 * @param location City name, Country initial in String
	 * @param type indicator for long, short, current, mars
	 */
	public Query(String location, int type){
		this.type = type;
		JSon = "";
		// get date from appropriate address according to type
		switch(type){
		case CURRENT_WEATHER:
			address = "http://api.openweathermap.org/data/2.5/weather?q=" + location
			+ "&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case THREE_HOUR_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast?q=" + location+ 
			"&APPID=a59d2137be4e7f2ef70800bb88f5a7f5";
			break;
		case DAILY_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location 
			+"&cnt=6&mode=json&APPID=bed420cd0cdd025a171927dacc52d5d7";
			break;
		default:
			address = "http://marsweather.ingenology.com/v1/latest/?format=json";
		}
		String[] s = {"Current", "Short-term", "Long-term", "Mars"};
		URL url;
		while(true){
			try {
				url = new URL(address);
				URLConnection connect = url.openConnection();
				connect.setConnectTimeout(5000);
				connect.setReadTimeout(10000);
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				JSon = br.readLine();
				System.out.println(s[type] + " Finish");
				break;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println(s[type] + " Connection failed");
			}
		}
		
	}
	
	public String toString(){
		return this.JSon;
	}
	/**
	 * test method for Query
	 * @param args system parameter
	 */
	public static void main(String[] args){
		Query q1 = new Query(null, 3);
		System.out.println(q1);
	}
	
	
}
