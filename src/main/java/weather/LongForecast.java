
package weather;


import data.JSONObject;
import data.Query;
/**
 * Class for Long Forecast which contains five entries
 * @author team8
 */
public class LongForecast{
	// array to contain five entries
	private LongForecastEntry[] list;
	
	/**
	 * constructor take city name as parameter to get long forecast data
	 * @param city name for city to get data about
	 */
	public LongForecast(String city){
		// get the long term weather from online and save the data in an array
		Query getter = new Query(city,2);
		while(getter.toString() == null){
			getter = new Query(city,2);
		}
		JSONObject data = new JSONObject(getter.toString());
		list = new LongForecastEntry[5];
		for(int i = 1; i < 6; i++){
			list[i-1] = new LongForecastEntry(
											data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main"),
											data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("day"),
											data.getJSONArray("list").getJSONObject(i).getInt("humidity") + " %",
											data.getJSONArray("list").getJSONObject(i).getDouble("pressure") + " hPa",
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("min"),
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("max"),
											new java.util.Date((long)data.getJSONArray("list").getJSONObject(i).getInt("dt") * 1000).toString().substring(4, 10));
		}
	}
	/**
	 * getter method for humidity
	 * @param index position in array
	 * @return humidity as String
	 */
	public String getHumidity(int index){
		return list[index].getHumidity();
	}
	
	/**
	 * getter method for air pressure
	 * @param index position in array
	 * @return air Pressure as String
	 */
	public String getPressure(int index){
		return list[index].getPressure();
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
	 * test method for Long Forecast
	 * @param args parameter from command line
	 */
	public static void main(String[] args){
		LongForecast weather  = new LongForecast("London,ca");
		System.out.println(weather);
	}
}
