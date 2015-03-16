package weather;

import ui.Main;
import data.JSONException;
import data.JSONObject;

/**
 * Current Weather Object contain all the information for current weather
 * This object also deal with the wrong location
 * if the location is wrong, send a signal to Main object
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class CurrentWeather{
	private String sunrise, // sunrise time
				   sunset, // sunset time
				   weather, // main weather description
				   direction,// wind direction in meteorology
				   location, // location of the data 
				   icon; // weather icon
				   
				   
	private double temperature, // Temperature in K
					pressure, // pressure in hPa
					speed, // wind speed in m/s
				  minTemp, // Temperature in K
				  maxTemp; // Temperature in K
	private int	humidity; // humidity in %
				
	
	/**
	 * Constructor for the current weather
	 * @param info JSON string that contains all the current weather data 
	 */
	public CurrentWeather(String info){
		// extract the data from JSON
		// because the info is actually the String return from Query
		// there are two possible kind of String
		// one is the correct string, so the data will be extracted correctly
		// the other one is error message because the city doe not exist
		try{
			JSONObject data = new JSONObject(info);
			sunrise = new java.util.Date((long)data.getJSONObject("sys").getInt("sunrise") * 1000).toString().substring(11,16);
			sunset = new java.util.Date((long)data.getJSONObject("sys").getInt("sunset") * 1000).toString().substring(11,16);
			weather = data.getJSONArray("weather").getJSONObject(0).getString("main");
			icon = data.getJSONArray("weather").getJSONObject(0).getString("icon");
			humidity = data.getJSONObject("main").getInt("humidity");
			temperature = data.getJSONObject("main").getDouble("temp");
			pressure = data.getJSONObject("main").getDouble("pressure");
			minTemp = data.getJSONObject("main").getDouble("temp_min");
			maxTemp = data.getJSONObject("main").getDouble("temp_max");
			speed = data.getJSONObject("wind").getInt("speed");
			location = data.getString("name") + ", "+ data.getJSONObject("sys").getString("country");
			makeDirection(data.getJSONObject("wind").getInt("deg"));
		}catch(JSONException e){
			// if the string is error message
			// print error message
			// use wrong location method in main to show error message
			// stop all the pulling thread because the location is wrong
			System.out.println("Current Location wrong");
			System.out.println("No guess");
			Main.wrongLocation();
			Main.interrupt();
		}
		
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
		// use the calculated number as index
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
	 * getter method for wind direction
	 * @return wind direction as String
	 */
	public String getDirection(){
		return direction;
	}
	
	/**
	 * getter method for temperature in the needed format
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp in String format
	 */
	public String getTemp(int unit) {
		if(unit == 0){
			return Math.round(temperature) + "";
		}
		if(unit == 1){
			return Math.round(temperature - 273.15) + "";
		}
		return Math.round(temperature * 9.0 / 5.0 - 459.67) + "";
	}

	/**
	 * getter method for minimum expected temperature in the needed format
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp_min minimum expected temperature
	 */
	public String getMinTemp(int unit) {
		if(unit == 0){
			return Math.round(minTemp) + "";
		}
		if(unit == 1){
			return Math.round(minTemp - 273.15) + "";
		}
		return Math.round(minTemp * 9.0 / 5.0 - 459.67) + "";
	}

	/**
	 * getter method for maximum expected temperature in the needed format
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the main_temp_max maximum expected temperature
	 */
	public String getMaxTemp(int unit) {
		if(unit == 0){
			return Math.round(maxTemp) + "";
		}
		if(unit == 1){
			return Math.round(maxTemp - 273.15) + "";
		}
		return Math.round(maxTemp * 9.0 / 5.0 - 459.67) + "";
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
			   "Wind direction " + this.direction + "\n" +
			   "Location " + this.location;
	}
	/**
	 * getter method for location of the data
	 * @return the location of the data
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * getter method for temperature
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * getter method for air Pressure
	 * @return the pressure
	 */
	public String getPressure() {
		return pressure+"";
	}

	/**
	 * getter method for wind speed
	 * @return the speed
	 */
	public String getSpeed() {
		return speed+"";
	}

	/**
	 * getter method for humidity
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity+"";
	}
	/**
	 * test method for Current Weather
	 * @param args parameter from command line
	 */
	public static void main(String[] args){
		CurrentWeather weather  = new CurrentWeather("London,CA");
		System.out.println(weather);
	}
}
