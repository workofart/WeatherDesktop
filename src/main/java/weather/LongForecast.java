package weather;

import ui.Main;
import data.JSONException;
import data.JSONObject;
/**
 * Long Forecast Object contain all the information for long forecast
 * This object also deal with the wrong location
 * if the location is wrong, catch the exception, let current weather end the thread
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class LongForecast{
	// array to contain five entries
	private LongForecastEntry[] list;
	private int cnt; // number of data returned from the website
	
	/**
	 * constructor take city name as parameter to get long forecast data
	 * @param info JSON string that contains all the long forecast data 
	 */
	public LongForecast(String info){
		// extract the data from JSON
		// because the info is actually the String return from Query
		// there are two possible kind of String
		// one is the correct string, so the data will be extracted correctly
		// the other one is error message because the city doe not exist
		JSONObject data = new JSONObject(info);
		list = new LongForecastEntry[5];
		try{
			cnt = data.getInt("cnt");
			for(int i = 1; i < cnt; i++){
				list[i-1] = new LongForecastEntry(
												data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"),
												data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
												data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("day"),
												data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("min"),
												data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("max"),
												new java.util.Date((long)data.getJSONArray("list").getJSONObject(i).getInt("dt") * 1000).toString().substring(4, 10));
			}
		}catch(JSONException e){
			System.out.println("Long Forecast Location Wrong");
			Main.interrupt(Main.getLongTermThread());
		}catch(Exception e){
			System.out.println("Long Forecast unknown problem");
			Main.interrupt(Main.getLongTermThread());
		}
		
	}
	
	/**
	 * getter method for data time
	 * @param index position in array
	 * @return date time as String
	 */
	public String getTime(int index){
		return list[index].getTime();
	}
	
	/**
	 * getter method for weather description
	 * @param index position in array
	 * @return main weather description as String
	 */
	public String getWeather(int index){
		return list[index].getWeather();
	}
	
	/**
	 * getter method for icon
	 * @param index position in array
	 * @return icon code
	 */
	public String getIcon(int index){
		return list[index].getIcon();
	}
	
	/**
	 * getter method for temperature
	 * @param index position in array
	 * @param unit flag to indicate temperature unit
	 * @return temperature in String in proper unit
	 */
	public String getTemp(int index, int unit){
		return list[index].getTemp(unit);
	}
	
	/**
	 * getter method for minimum temperature
	 * @param index position in array
	 * @param unit flag to indicate temperature unit
	 * @return minimum temperature in String in proper unit
	 */
	public String getMinTemp(int index, int unit){
		return list[index].getMinTemp(unit);
	}
	
	/**
	 * getter method for maximum temperature
	 * @param index position in array
	 * @param unit flag to indicate temperature unit
	 * @return maximum temperature in String in proper unit
	 */
	public String getMaxTemp(int index, int unit){
		return list[index].getMaxTemp(unit);
	}
	
	/**
	 * method to list all the information
	 * @return String which contains all the information in this object
	 */
	public String toString(){
		String result = "";
		for(int i = 0; i < 5; i++){
			result = result + i + "\n" +
					list[i] + "\n\n";
		}
		return result;
	}
	
	/**
	 * getter method to the number of data entries contained in the returned data
	 * @return cnt the number of data entries contained in the returned data
	 */
	public int getCnt(){
		return cnt;
	}
	/**
	 * test method for Long Forecast
	 * @param args parameter from command line
	 */
	public static void main(String[] args){
		LongForecast weather  = new LongForecast("London,ca");
		System.out.println(weather);
	}
}
