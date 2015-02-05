public final class MarsWeather {
	private float wind_speed,min_temp, min_temp_fahrenheit, max_temp, max_temp_fahrenheit, pressure;
	private int humidity; // Atmospheric Pressure
	private String  wind_direction, weather, icon;
			
	// Constructor
	public MarsWeather(){
			Query getter = new Query(null, 3);
			JSonParser data = new JSonParser(getter);
			min_temp = flo(data.findObject("report|min_temp").getContent());
			min_temp_fahrenheit = flo(data.findObject("report|min_temp_fahrenheit").getContent());
			max_temp = flo(data.findObject("report|max_temp").getContent());
			max_temp_fahrenheit = flo(data.findObject("report|max_temp_fahrenheit").getContent());
			pressure = flo(data.findObject("report|pressure").getContent());
			wind_speed = flo(data.findObject("report|wind_speed").getContent());
			wind_direction = str(data.findObject("report|wind_direction").getContent());
			weather = str(data.findObject("report|atmo_opacity").getContent());
			humidity = num(data.findObject("report|abs_humidity").getContent());
			makeIcon();
	}
	private void makeIcon(){
		icon =  weather;
	}
	
	private String str(String s){
		return s.substring(1,s.length()-1);
	}
	
	private int num(String s){
		if(s.equals("null")){
			return -1;
		}
		return Integer.parseInt(s);
	}
	
	private float flo(String s){
		if(s.equals("null")){
			return -1.0F;
		}
		return Float.parseFloat(s);
	}

	/**
	 * @return the min_temp
	 */
	public String getMinTemp(int unit) {
		if(unit == 0){
			return Math.round(min_temp + 273.15) + " K";
		}
		if(unit == 1){
			return Math.round(min_temp) + " C";
		}
		return Math.round(min_temp_fahrenheit) + " F";
	}

	/**
	 * @return the max_temp
	 */
	public String getMaxTemp(int unit) {
		if(unit == 0){
			return Math.round(max_temp + 273.15) + " K";
		}
		if(unit == 1){
			return Math.round(max_temp) + " C";
		}
		return Math.round(max_temp_fahrenheit) + " F";
	}

	/**
	 * @return the pressure
	 */
	public String getPressure() {
		return pressure + " hPa";
	}

	/**
	 * @return the wind_direction
	 */
	public String getDirection() {
		return wind_direction;
	}

	/**
	 * @return the atmo_opacity
	 */
	public String getWeather() {
		return weather;
	}

	public String getIcon(){
		return icon;
	}
	public String getSpeed(){
		if(this.wind_speed == -1.0f){
			return "--";
		}
		return wind_speed + " kph";
	}
	public String getHumidity(){
		if(this.humidity == -1.0f){
			return "--";
		}
		return humidity + "%";
	}
	public static void main(String[] args){
		MarsWeather weather = new MarsWeather();
		System.out.println(weather.getMinTemp(0));
		System.out.println(weather.getMinTemp(1));
		System.out.println(weather.getMinTemp(2));
		System.out.println(weather.getMaxTemp(0));
		System.out.println(weather.getMaxTemp(1));
		System.out.println(weather.getMaxTemp(2));
		System.out.println(weather.getDirection());
		System.out.println(weather.getWeather());
		System.out.println(weather.getIcon());
		System.out.println(weather.getSpeed());
		System.out.println(weather.getHumidity());
		System.out.println(weather.getPressure());
	}
}
