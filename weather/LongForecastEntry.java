package weather;



public class LongForecastEntry{

	private double  minTemp, maxTemp,temperature;
	private String pressure,weather, icon, humidity, time;
	
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
	public String getPressure(){
		return this.pressure;
	}
	public String getTime(){
		return time;
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
	/**
	 * @return the minTemp
	 */
	public double getMinTemp() {
		return minTemp;
	}
	/**
	 * @return the maxTemp
	 */
	public double getMaxTemp() {
		return maxTemp;
	}
	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}
	/**
	 * @param minTemp the minTemp to set
	 */
	public void setMinTemp(double minTemp) {
		this.minTemp = minTemp;
	}
	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(double maxTemp) {
		this.maxTemp = maxTemp;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	/**
	 * @param pressure the pressure to set
	 */
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
}
