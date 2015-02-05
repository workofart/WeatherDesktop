/**
 * This class contains all the data for current weather
 * @author Team8
 *
 */
public class CurrentWeather {
	private String sunrise, // sunrise time
				   sunset, // sunset time
				   weather, // main weather description
				   icon, // weather icon
				   humidity, // humidity in %
				   pressure, // pressure in hPa
				   speed, // wind speed in m/s
				   direction;// wind direction in meteorology
	private float temperature, // Temperature in K
				  minTemp, // Temperature in K
				  maxTemp; // Temperature in K
	
	/**
	 * Constructor for the current weather
	 * @param city the city to be search about
	 */
	public CurrentWeather(String city){
		Query getter = new Query(city,0);
		JSonParser data = new JSonParser(getter);
		// extract data from JsonObject
		sunrise = new java.util.Date((long)num(data.findObject("sys|sunrise").getContent()) * 1000).toString().substring(11, 19);
		sunset = new java.util.Date((long)num(data.findObject("sys|sunset").getContent()) * 1000).toString().substring(11, 19);
		weather = str(data.findObject("weather|0|main").getContent());
		icon = str(data.findObject("weather|0|icon").getContent());
		humidity = data.findObject("main|humidity").getContent() + " %";
		temperature = flo(data.findObject("main|temp").getContent());
		pressure = data.findObject("main|pressure").getContent() + " hPa";
		minTemp = flo(data.findObject("main|temp_min").getContent());
		maxTemp= flo(data.findObject("main|temp_max").getContent());
		speed = data.findObject("wind|speed").getContent() + " m/s";
		makeDirection(flo(data.findObject("wind|deg").getContent()));
	}
	
	/**
	 * helper method to change meteorology direction to String direction 
	 * @param degree meteorology direction
	 */
	private void makeDirection(float degree){
		int dir = (int)(1000 * degree) + 5625; // first transfer from float to integer
		dir %= 360000;
		dir /= 11250;
		dir = (dir + 16) % 32;
		String[] array = {"N", "NbE", "NNE", "NEbN",
						  "NE", "NEbE", "ENE", "EbN",
						  "E", "EbS", "ESE", "SEbE",
						  "SE", "SEbS", "SSE", "SbE",
						  "S", "SbW", "SSW", "SWbS",
						  "SW", "SWbW", "WSW", "WbS",
						  "W", "WbN", "WNW", "NWbW",
						  "NW", "NWbN", "NNW", "NbW"};
		direction = array[dir];
	}
	
	/**
	 * helper method to format string data
	 * @param s data as string to be formatted
	 * @return the formatted string data
	 */
	private String str(String s){
		return s.substring(1,s.length()-1);
	}
	
	/**
	 * helper method to format integer data
	 * @param s data as string to be formatted
	 * @return the formatted integer data
	 */
	private int num(String s){
		return Integer.parseInt(s);
	}
	
	/**
	 * helper method to format float number data
	 * @param s data as string to be formatted
	 * @return the formatted float number data
	 */
	private float flo(String s){
		return Float.parseFloat(s);
	}
	/**
	 * getter method for sunrise
	 * @return sunrise time as String
	 */
	public String getSunrise(){
		return sunrise;
	}
	/**
	 * getter method for sunset
	 * @return sunset time as String
	 */
	public String getSunset(){
		return sunset;
	}
	/**
	 * getter method for weather
	 * @return weather description as String
	 */
	public String getWeather(){
		return weather;
	}
	/**
	 * getter method for icon
	 * @return the name of icon as String
	 */
	public String getIcon(){
		return icon;
	}
	/**
	 * getter method for humidity
	 * @return humidity as String
	 */
	public String getHumidity(){
		return humidity;
	}
	/**
	 * getter method for pressure
	 * @return air pressure as String
	 */
	public String getPressure(){
		return pressure;
	}
	/**
	 * getter method for wind speed
	 * @return wind speed as String
	 */
	public String getSpeed(){
		return speed;
	}
	/**
	 * getter method for wind direction
	 * @return wind direction as String
	 */
	public String getDirection(){
		return direction;
	}
	
	/**
	 * getter method for temperature in the needed format
	 * @param n the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp in String format
	 */
	public String getTemp(int unit) {
		if(unit == 0){
			return Math.round(temperature) + " K";
		}
		if(unit == 1){
			return Math.round(temperature - 273.15) + " C";
		}
		return Math.round(temperature * 9 / 5 - 459.67) + " F";
	}

	/**
	 * getter method for minimum expected temperature in the needed format
	 * @param n the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp_min minimum expected temperature
	 */
	public String getMinTemp(int unit) {
		if(unit == 0){
			return Math.round(minTemp) + " K";
		}
		if(unit == 1){
			return Math.round(minTemp - 273.15) + " C";
		}
		return Math.round(minTemp * 9 / 5 - 459.67) + " F";
	}

	/**
	 * getter method for maximum expected temperature in the needed format
	 * @param n the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp_max maximum expected temperature
	 */
	public String getMaxTemp(int unit) {
		if(unit == 0){
			return Math.round(maxTemp) + " K";
		}
		if(unit == 1){
			return Math.round(maxTemp - 273.15) + " C";
		}
		return Math.round(maxTemp * 9 / 5 - 459.67) + " F";
	}
	/**
	 * toString method for current weather object
	 * @return String contains all the data
	 */
	public String toString(){
		return "Sunrise time " + this.sunrise + "\n" +
			   "Sunset time " + this.sunset + "\n" +
			   "Weather description " + this.weather + "\n" +
			   "Weather Icon " + this.icon + "\n" +
			   "Humidity " + this.humidity + "\n" +
			   "Temperature in K " + this.getTemp(0) + "\n" +
			   "Temperature in C " + this.getTemp(1) + "\n" +
			   "Temperature in F " + this.getTemp(2) + "\n" +
			   "Pressure " + this.pressure + "\n" +
			   "Max Temperature in K " + this.getMaxTemp(0) + "\n" +
			   "Max Temperature in C " + this.getMaxTemp(1) + "\n" +
			   "Max Temperature in F " + this.getMaxTemp(2) + "\n" +
			   "Min Temperature in K " + this.getMinTemp(0) + "\n" +
			   "Min Temperature in C " + this.getMinTemp(1) + "\n" +
			   "Min Temperature in F " + this.getMinTemp(2) + "\n" +
			   "Wind speed " + this.speed + "\n" +
			   "Wind direction " + this.direction;
	}

	public static void main(String[] args){
		CurrentWeather weather  = new CurrentWeather("London,GB");
		System.out.println(weather);
	}
}
