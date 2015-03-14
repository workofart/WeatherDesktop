
package weather;


import data.JSONObject;
import data.Query;
/**
 * class for short forecast which contains eight entries
 * @author team8
 */
public class ShortForecast{
	// list of eight entries
	private ShortForecastEntry[] list;
	/**
	 * constructor to load data from city name
	 * @param city name of city 
	 */
	public ShortForecast(String city){
		// get data from online and save the first eight entries as an array of short forecast entries
		Query getter = new Query(city,1);
		JSONObject data = new JSONObject(getter.toString());
		list = new ShortForecastEntry[8];
		for(int i = 0; i < 8; i++){
			list[i] = new ShortForecastEntry(data.getJSONArray("list").getJSONObject(i).getString("dt_txt").substring(11,16),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp"));
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
