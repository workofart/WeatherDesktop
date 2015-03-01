package weather;


import data.JSONObject;
import data.Query;

public class ShortForecast {
	
	private ShortForecastEntry[] list;
	public ShortForecast(String city){
		// get data from online and save the first eight entries as an array of short forecast entries
		Query getter = new Query(city,1);
		JSONObject data = new JSONObject(getter.toString());
		list = new ShortForecastEntry[8];
		for(int i = 0; i < 8; i++){
			list[i] = new ShortForecastEntry(data.getJSONArray("list").getJSONObject(i).getString("dt_txt").substring(11,19),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon"),
										 	 data.getJSONArray("list").getJSONObject(i).getJSONObject("main").getDouble("temp"));
		}
	}
	
	public String toString(){
		String result = "";
		for(int i = 0; i < 8; i++){
			result = result + i + "\n" + list[i] + "\n";
		}
		return result;
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
	
	public static void main(String[] args){
		ShortForecast weather  = new ShortForecast("London,ca");
		System.out.println(weather);
	}

}
