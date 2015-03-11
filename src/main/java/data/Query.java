package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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
		// get date from appropriate address according to type
		switch(type){
		case CURRENT_WEATHER:
			address = "Http://api.openweathermap.org/data/2.5/weather?q=" + location +
			"&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case THREE_HOUR_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast?q=" + location + 
			"&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case DAILY_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location + 
			"&cnt=6&mode=json&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		default:
			address = "http://marsweather.ingenology.com/v1/latest/?format=json";
		}
		// handle explicit Exception for URL
		try{
			URL url = new URL(address);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			JSon = br.readLine();	
		}catch(Exception e){
			System.out.println("Bad Connection, pulling again");
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
		//getter = new Query("",3);
		System.out.println(getter);
	}
	
}
