public class LongForecast {
	private LongForecastEntry[] list;
	
	public LongForecast(String city){
		Query getter = new Query(city,2);
		JSonParser data = new JSonParser(getter);
		list = new LongForecastEntry[5];
		for(int i = 1; i < 6; i++){
			list[i-1] = new LongForecastEntry(
											str(data.findObject("list|"+i+"|weather|0|main").getContent()),
											str(data.findObject("list|"+i+"|weather|0|icon").getContent()),
											Float.parseFloat(data.findObject("list|"+i+"|temp|day").getContent()),
											data.findObject("list|"+i+"|humidity").getContent() + " %",
											data.findObject("list|"+i+"|pressure").getContent() + " hPa",
											Float.parseFloat(data.findObject("list|"+i+"|temp|min").getContent()),
											Float.parseFloat(data.findObject("list|"+i+"|temp|max").getContent()),
											new java.util.Date((long)Integer.parseInt(data.findObject("list|"+i+"|dt").getContent()) * 1000).toString().substring(4, 10));
		}
	}
	/**
	 * helper method to format string data
	 * @param s data as string to be formatted
	 * @return the formatted string data
	 */
	private String str(String s){
		return s.substring(1,s.length()-1);
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
					list[i] + "\n";
		}
		return result;
	}
	public static void main(String[] args){
		LongForecast weather  = new LongForecast("London,ca");
		System.out.println(weather);
	}
}
