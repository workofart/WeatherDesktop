package weather;


import data.JSONObject;
import data.Query;

public class LongForecast {
	private LongForecastEntry[] list;
	
	public LongForecast(String city){
		// get the long term weather from online and save the data in an array
		Query getter = new Query(city,2);
		JSONObject data = new JSONObject(getter.toString());
		list = new LongForecastEntry[5];
		for(int i = 1; i < 6; i++){
			list[i-1] = new LongForecastEntry(
											data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"),
											data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("day"),
											data.getJSONArray("list").getJSONObject(i).getInt("humidity") + " %",
											data.getJSONArray("list").getJSONObject(i).getDouble("pressure") + " hPa",
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("min"),
											data.getJSONArray("list").getJSONObject(i).getJSONObject("temp").getDouble("max"),
											new java.util.Date((long)data.getJSONArray("list").getJSONObject(i).getInt("dt") * 1000).toString().substring(4, 10));
		}
	}
	
	public String getHumidity(int index){
		return list[index].getHumidity();
	}
	public String getPressure(int index){
		return list[index].getPressure();
	}
	public String getTime(int index){
		return list[index].getTime();
	}
	
	public String getWeather(int index){
		return list[index].getWeather();
	}
	
	public String getIcon(int index){
		return list[index].getIcon();
	}
	
	public String getTemp(int index, int unit){
		return list[index].getTemp(unit);
	}
	public String getMinTemp(int index, int unit){
		return list[index].getMinTemp(unit);
	}
	public String getMaxTemp(int index, int unit){
		return list[index].getMaxTemp(unit);
	}
	public String toString(){
		String result = "";
		for(int i = 0; i < 5; i++){
			result = result + i + "\n" +
					list[i] + "\n\n";
		}
		return result;
	}
	public static void main(String[] args){
		LongForecast weather  = new LongForecast("London,ca");
		System.out.println(weather);
	}
}
