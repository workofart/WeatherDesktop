
package weather;

/**
 * Class for Long Forecast Entry
 * @author team8
 */

public class LongForecastEntry{
	
	private double  minTemp, maxTemp,temperature; // attribute for minimum, maximum and current temperature
	private String pressure,weather, icon, humidity, time; // attribute for air pressure, weather description, icon code, humidity and data time
	
	/**
	 * constructor to set all the field
	 * @param weather weather description
	 * @param icon icon code
	 * @param temperature current temperature
	 * @param humidity humidity
	 * @param pressure air pressure
	 * @param minTemp minimum temperature
	 * @param maxTemp maximum temperature
	 * @param time data time
	 */
	public LongForecastEntry(String weather, String icon, double temperature, String humidity, String pressure, double minTemp, double maxTemp, String time) {
		this.weather = weather;
		this.icon = icon;
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.time = time;
	}
	
	/**
	 * method to generate a string that contains all the information in this object
	 * @return String that contains all the information
	 */
	public String toString(){
		String result = "";
		result = result +
				 "Weather " + weather + "\n" +
				 "Icon " + icon + "\n" +
				 "Temperature in K " + getTemp(0) + "\n" +
				 "Temperature in C " + getTemp(1) + "\n" +
				 "Temperature in F " + getTemp(2) + "\n" +
				 "Max temperature in K " + getMaxTemp(0) + "\n" +
				 "Max temperature in C " + getMaxTemp(1) + "\n" +
				 "Max temperature in F " + getMaxTemp(2) + "\n" +
				 "Min temperature in K " + getMinTemp(0) + "\n" +
				 "Min temperature in C " + getMinTemp(1) + "\n" +
				 "Min temperature in F " + getMinTemp(2) + "\n" +
				 "Humidity " + humidity + "\n" +
				 "Pressure " + pressure + "\n" +
				 "Date " + time;
		return result;
	}
	
	/**
	 * getter method for air pressure
	 * @return air pressure in String
	 */
	public String getPressure(){
		return this.pressure;
	}
	/**
	 * getter method for data time
	 * @return data time in String
	 */
	public String getTime(){
		return time;
	}
	
	/**
	 * getter method for Humidity
	 * @return humidity in String
	 */
	public String getHumidity(){
		return humidity;
	}
	
	/**
	 * getter method for main weather description
	 * @return main weather description in String
	 */
	public String getWeather(){
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
		return Math.round(temperature * 9 / 5 - 459.67) + "";
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
		return Math.round(minTemp * 9 / 5 - 459.67) + "";
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
		return Math.round(maxTemp * 9 / 5 - 459.67) + "";
	}
}
