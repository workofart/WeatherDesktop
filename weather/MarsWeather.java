package weather;


import data.JSONException;
import data.JSONObject;
import data.Query;

public final class MarsWeather {
	private double wind_speed,min_temp, min_temp_fahrenheit, max_temp, max_temp_fahrenheit, pressure;
	private int humidity; // Atmospheric Pressure
	private String  wind_direction, weather, icon;
			
	// Constructor
	public MarsWeather(){
		Query getter = new Query(null, 3);
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
	public static void main(String[] args){
		MarsWeather weather = new MarsWeather();
		System.out.println(weather);
	}
	/**
	 * @return the wind_speed
	 */
	public double getWind_speed() {
		return wind_speed;
	}
	/**
	 * @return the min_temp
	 */
	public double getMin_temp() {
		return min_temp;
	}
	/**
	 * @return the min_temp_fahrenheit
	 */
	public double getMin_temp_fahrenheit() {
		return min_temp_fahrenheit;
	}
	/**
	 * @return the max_temp
	 */
	public double getMax_temp() {
		return max_temp;
	}
	/**
	 * @return the max_temp_fahrenheit
	 */
	public double getMax_temp_fahrenheit() {
		return max_temp_fahrenheit;
	}
	/**
	 * @return the wind_direction
	 */
	public String getWind_direction() {
		return wind_direction;
	}
}
