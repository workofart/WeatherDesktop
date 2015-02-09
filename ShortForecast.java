public class ShortForecast {
	
	private ShortForecastEntry[] list;
	public ShortForecast(String city){
		Query getter = new Query(city,1);
		JSonParser data = new JSonParser(getter);
		list = new ShortForecastEntry[8];
		for(int i = 0; i < 8; i++){
			list[i] = new ShortForecastEntry(data.findObject("list|"+i+"|dt_txt").getContent().substring(11,19),
										 	 str(data.findObject("list|"+i+"|weather|0|main").getContent()),
										 	 str(data.findObject("list|"+i+"|weather|0|icon").getContent()),
										 	 Float.parseFloat(data.findObject("list|"+i+"|main|temp").getContent()));
		}
	}
	private String str(String s){
		return s.substring(1,s.length()-1);
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
