
package weather;


import data.JSONException;
import data.JSONObject;

/**
 * Mars Weather Object contain all the information for Mars weather
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public final class MarsWeather{
	private double wind_speed,min_temp,  max_temp, pressure;
	private int humidity; // Atmospheric Pressure
	private String  wind_direction, weather, icon;
			
	/**
	 * constructor to get data from website and store
	 * @param mdata JSON string that contains all the Mars weather data 
	 */
	public MarsWeather(String mdata){
		// extract the data from JSON
		// because the info is actually the String return from Query
		// there are two possible kind of String
		// one is the correct string, so the data will be extracted correctly
		// the other one is error message because the city doe not exist
		JSONObject data = new JSONObject(mdata);
		min_temp = data.getJSONObject("report").getDouble("min_temp");
		max_temp = data.getJSONObject("report").getDouble("max_temp");
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
		if(this.weather.equals("Sunny")){
			this.icon = "01d";
		}
		else if(this.weather.equals("Cloudy")){
			this.icon = "03d";
		}
		else{
			this.icon = "50d";
		}
	}
	
	/**
	 * getter methsd for Temperature
	 * @param unit temperature unit
	 * @return the calculated temperature
	 */
	public String getTemp(int unit){
		// because there is no current temperature in the Mar, we use the average
		double temp = (min_temp + max_temp)/2;
		if(unit == 0){
			return Math.round(temp + 273.15) + "";
		}
		if(unit == 1){
			return Math.round(temp) + "";
		}
		return Math.round(temp * 9.0 / 5.0 +32) + "";
	}

	/**
	 * get minTemp field in the object with proper unit
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the report_temp_min minimum expected temperature
	 */
	public String getMinTemp(int unit) {
		if(unit == 0){
			return Math.round(min_temp + 273.15) + "";
		}
		if(unit == 1){
			return Math.round(min_temp) + "";
		}
		return Math.round(min_temp * 9.0 / 5.0 +32) + "";
	}

	/**
	 * get maxTemp field in the object with proper unit
	 * @param unit the indicator for the unit of temperature, 0 - K, 1 - C, 2 - F
	 * @return the report_temp_max minimum expected temperature
	 */
	public String getMaxTemp(int unit) {
		if(unit == 0){
			return Math.round(max_temp + 273.15) + "";
		}
		if(unit == 1){
			return Math.round(max_temp) + "";
		}
		return Math.round(max_temp * 9.0 / 5.0+32) + "";
	}

	/**
	 * getter method for air pressure
	 * @return the pressure
	 */
	public String getPressure() {
		return pressure + "";
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
		return wind_speed + "";
	}
	
	/**
	 * getter method for humidity
	 * @return humidity in String
	 */
	public String getHumidity(){
		if(this.humidity == -1.0f){
			return "--";
		}
		return humidity + "";
	}
	
	/**
	 * method to generate a String contains all the information
	 * @return String all the information
	 */
	public String toString(){
		return "Weather description " + this.weather + "\n" +
			   "Weather Icon " + this.icon + "\n" +
			   "Humidity " + this.getHumidity() + "\n" +
			   "Pressure " + this.pressure + "\n" +
			   "Max Temperature in K " + this.getMaxTemp(0) + "\n" +
			   "Max Temperature in C " + this.getMaxTemp(1) + "\n" +
			   "Max Temperature in F " + this.getMaxTemp(2) + "\n" +
			   "Min Temperature in K " + this.getMinTemp(0) + "\n" +
			   "Min Temperature in C " + this.getMinTemp(1) + "\n" +
			   "Min Temperature in F " + this.getMinTemp(2) + "\n" +
			   "Wind speed " + this.getSpeed() + "\n" +
			   "Wind direction " + this.wind_direction;
	}
	
}
