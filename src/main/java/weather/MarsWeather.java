
package weather;


import data.JSONException;
import data.JSONObject;
import data.Query;
/**
 * Class for Mars Weather
 * @author team8
 */
public final class MarsWeather{
	private double wind_speed,min_temp, min_temp_fahrenheit, max_temp, max_temp_fahrenheit, pressure;
	private int humidity; // Atmospheric Pressure
	private String  wind_direction, weather, icon;
			
	/**
	 * constructor to get data from website and store
	 */
	public MarsWeather(){
		Query getter = new Query(null, 3);
		while(getter.toString() == null){
			getter = new Query(null, 3);
		}
		// get data from JSON
		JSONObject data = new JSONObject(getter.toString());
		min_temp = data.getJSONObject("report").getDouble("min_temp");
		min_temp_fahrenheit = data.getJSONObject("report").getDouble("min_temp_fahrenheit");
		max_temp = data.getJSONObject("report").getDouble("max_temp");
		max_temp_fahrenheit = data.getJSONObject("report").getDouble("max_temp_fahrenheit");
		pressure = data.getJSONObject("report").getDouble("pressure");
		// if the data not exist, give -1
		try{
			wind_speed = data.getJSONObject("report").getDouble("wind_speed");
			}catch(JSONException e){
				wind_speed = -1.0;
				}
		wind_direction = data.getJSONObject("report").getString("wind_direction");
		weather = data.getJSONObject("report").getString("atmo_opacity");
		// if the data not exist, give -1
		try{
			humidity = data.getJSONObject("report").getInt("abs_humidity");
			}catch(JSONException e){
				humidity = -1;
				}
		makeIcon();
	}
	/**
	 * helper method to generate icon according to weather
	 */
	private void makeIcon(){
		icon =  weather;
	}
	
	

	/**
	 * get minTemp field in the object with proper unit
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the report_temp_min minimum expected temperature
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
	 * get maxTemp field in the object with proper unit
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the report_temp_max minimum expected temperature
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
	 * getter method for air pressure
	 * @return the pressure
	 */
	public String getPressure() {
		return pressure + " hPa";
	}

	/**
	 * getter method for wind direction
	 * @return the wind_direction
	 */
	public String getDirection() {
		return wind_direction;
	}

	/**
	 * getter method for main weather description
	 * @return the atmo_opacity
	 */
	public String getWeather() {
		return weather;
	}
	
	/**
	 * getter method for icon code
	 * @return icon code in String
	 */
	public String getIcon(){
		return icon;
	}
	
	/**
	 * getter method for wind speed in kph
	 * @return wind speed in String
	 */
	public String getSpeed(){
		if(this.wind_speed == -1.0f){
			return "--";
		}
		return wind_speed + " kph";
	}
	
	/**
	 * getter method for humidity
	 * @return humidity in String
	 */
	public String getHumidity(){
		if(this.humidity == -1.0f){
			return "--";
		}
		return humidity + "%";
	}
	
	/**
	 * method to generate a String contains all the information
	 * @return String all the information
	 */
	public String toString(){
		return "Weather description " + this.weather + "\n" +
			   "Weather Icon " + this.icon + "\n" +
			   "Humidity " + this.humidity + "\n" +
			   "Pressure " + this.pressure + "\n" +
			   "Max Temperature in K " + this.getMaxTemp(0) + "\n" +
			   "Max Temperature in C " + this.getMaxTemp(1) + "\n" +
			   "Max Temperature in F " + this.getMaxTemp(2) + "\n" +
			   "Min Temperature in K " + this.getMinTemp(0) + "\n" +
			   "Min Temperature in C " + this.getMinTemp(1) + "\n" +
			   "Min Temperature in F " + this.getMinTemp(2) + "\n" +
			   "Wind speed " + this.wind_speed + "\n" +
			   "Wind direction " + this.wind_direction;
	}
	
	/**
	 * test method 
	 * @param args parameter from command line
	 */
	public static void main(String[] args){
		MarsWeather weather = new MarsWeather();
		System.out.println(weather);
	}
	/**
	 * getter method for wind speed
	 * @return the wind_speed
	 */
	public double getWind_speed() {
		return wind_speed;
	}
	/**
	 * getter method for minimum temperature in C
	 * @return the min_temp
	 */
	public double getMin_temp() {
		return min_temp;
	}
	/**
	 * getter method for minimum temperature in F
	 * @return the min_temp_fahrenheit
	 */
	public double getMin_temp_fahrenheit() {
		return min_temp_fahrenheit;
	}
	/**
	 * getter method for maximum temperature in C
	 * @return the max_temp
	 */
	public double getMax_temp() {
		return max_temp;
	}
	/**
	 * getter method for maximum temperature in F
	 * @return the max_temp_fahrenheit
	 */
	public double getMax_temp_fahrenheit() {
		return max_temp_fahrenheit;
	}
	/**
	 * getter method for wind direction
	 * @return the wind_direction
	 */
	public String getWind_direction() {
		return wind_direction;
	}
}
