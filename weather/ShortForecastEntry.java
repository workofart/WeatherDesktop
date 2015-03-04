
package weather;
/**
 * class for Short forecast entry
 * @author team8
 */

public class ShortForecastEntry {
		private double temperature;
		private String weather, icon, time;
		
		/**
		 * constructor to store data properly
		 * @param time data time
		 * @param weather main weather description
		 * @param icon icon code
		 * @param temperature temperature
		 */
		public ShortForecastEntry(String time, String weather, String icon, double temperature){
			this.time = time;
			this.temperature = temperature;
			this.weather = weather;
			this.icon = icon;
		}
		
		/**
		 * method to generate a string contain all the information
		 * @return string that contains all the information
		 */
		public String toString(){
			return  "Time " + time + "\n" +
					"Weather " + weather + "\n" +
				   "Icon " + icon + "\n" +
					"temperature in K " + getTemp(0) + "\n" +
					"temperature in C " + getTemp(1) + "\n" +
					"temperature in F " + getTemp(2);
		}
		
		/**
		 * getter method for data time
		 * @return data time as String
		 */
		public String getTime(){
			return time;
		}
		
		/**
		 * getter method for main weather description
		 * @return main weather description as String
		 */
		public String getWeather(){
			return weather;
		}
		
		/**
		 * getter method for icon code
		 * @return icon code as String
		 */
		public String getIcon(){
			return icon;
		}
		
		/**
		 * getter method for temperature
		 * @param unit indicator for unit of temperature
		 * @return the temperature in proper unit as String
		 */
		public String getTemp(int unit){
			if(unit == 0){
				return Math.round(temperature) + "";
			}
			if(unit == 1){
				return Math.round(temperature - 273.15) + "";
			}
			return Math.round(temperature * 9 / 5 - 459.67) + "";
		}
	}