
public class LongForecastEntry{

	private float  minTemp, maxTemp,temperature;
	private String pressure,weather, icon, humidity, sunrise, sunset, time;
	
	public LongForecastEntry(String sunrise, String sunset, String weather, String icon, float temperature, String humidity, String pressure, float minTemp, float maxTemp, String time) {
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.weather = weather;
		this.icon = icon;
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.time = time;
	}
	public String toString(){
		String result = "";
		result = result +
				 "Sunrise " + sunrise + "\n" +
				 "Sunset " + sunset + "\n" +
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
	public String getPressure(){
		return this.pressure;
	}
	public String getTime(){
		return time;
	}
	public String getSunrise(){
		return sunrise;
	}
	
	public String getSunset(){
		return sunset;
	}
	
	public String getHumidity(){
		return humidity;
	}
	
	public String getWeather(){
		return weather;
	}
	
	public String getIcon(){
		return icon;
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
	
}
