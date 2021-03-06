package weather;


import ui.Main;
import data.JSONException;
import data.JSONObject;

/**
 * Short Forecast Weather Object contain all the information for short Forecast weather
 * This object also deal with the wrong location
 * if the location is wrong, catch the exception, let current weather to end the thread
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class ShortForecast{
	// list of eight entries
	private ShortForecastEntry[] list;
	/**
	 * constructor to load data from city name
	 * @param info JSON string that contains all the short forecast data 
	 */
	public ShortForecast(String info){
		// get data from online and save the first eight entries as an array of short forecast entries
		JSONObject data = new JSONObject(info);
		list = new ShortForecastEntry[8];
		// extract the data from JSON
		// because the info is actually the String return from Query
		// there are two possible kind of String
		// one is the correct string, so the data will be extracted correctly
		// the other one is error message because the city doe not exist
		try{
			for(int i = 0; i < 8; i++){
				list[i] = new ShortForecastEntry(data.getJSONArray("list").getJSONObject(i).getString("dt_txt").substring(11,16),
											 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"),
											 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
											 	 data.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp"));
			}
		}catch(JSONException e){
			System.out.println("Short forecast location wrong");
			Main.interrupt(Main.getShortTermThread());
		}catch(Exception e){
			System.out.println("Short Forecast unknown problem");
			Main.interrupt(Main.getShortTermThread());
		}
	}
	
	
	/**
	 * generate a string contains all the information
	 * @return String contains all the information
	 */
	public String toString(){
		String result = "";
		for(int i = 0; i < 8; i++){
			result = result + i + "\n" + list[i] + "\n";
		}
		return result;
	}
	/**
	 * getter method for data time
	 * @param index position in array
	 * @return data time as String
	 */
	public String getTime(int index){
		return list[index].getTime();
	}
	
	/**
	 * getter method for main weather description
	 * @param index position in array
	 * @return weather description as String
	 */
	public String getWeather(int index){
		return list[index].getWeather();
	}
	
	/**
	 * getter method for icon code
	 * @param index position in array
	 * @return icon code as String
	 */
	public String getIcon(int index){
		return list[index].getIcon();
	}
	
	/**
	 * getter method for temperature
	 * @param index position in array
	 * @param unit indicator for the unit of temperature
	 * @return temperature in the proper unit as String
	 */
	public String getTemp(int index, int unit){
		return list[index].getTemp(unit);
	}
	
	/**
	 * test method 
	 * @param args command line parameter
	 */
	public static void main(String[] args){
		ShortForecast weather  = new ShortForecast("London,ca");
		System.out.println(weather);
	}

}
