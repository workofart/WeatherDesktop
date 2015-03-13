package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
	/**
	 * Get JSon file for the location according to forecast type
	 * @param location City name, Country initial in String
	 * @param type indicator for long, short, current, mars
	 */
	public Query(String location, int type){
		JSon = "";
		// get date from appropriate address according to type
		switch(type){
		case CURRENT_WEATHER:
			address = "Http://api.openweathermap.org/data/2.5/weather?q=" + location
			+ "&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case THREE_HOUR_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast?q=" + location+ 
			"&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case DAILY_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location 
			+"&cnt=6&mode=json&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		default:
			address = "http://marsweather.ingenology.com/v1/latest/?format=json";
		}
		// handle explicit Exception for URL
		while(JSon.isEmpty()){
			long startTime = 0;
			startTime = System.currentTimeMillis();
			URL url;
			try {
				url = new URL(address);
				URLConnection connect = url.openConnection();
				connect.setConnectTimeout(5000);
				connect.setReadTimeout(10000);
				BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
				System.out.println("Connected");
				JSon = br.readLine();
				System.out.println("Finish pulling within " + (System.currentTimeMillis() - startTime) + " ms");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage() + "\nBad Connection, pulling again " + (System.currentTimeMillis() - startTime) + " ms");
			}
		}
	}
	
	/**
	 * Method to return the JSON as a String
	 * @return JSON as a String
	 */
	public String toString(){
		return this.JSon;
	}
	
	/**
	 * test method for Query
	 * @param args system parameter
	 */
	public static void main(String[] args){
		Query getter = new Query("London,gb", 2);
		//System.out.println(getter);
		//Query getter = new Query("",3);
		System.out.println(getter);
	}
	
}
