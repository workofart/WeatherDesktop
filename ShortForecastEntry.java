public class ShortForecastEntry {
		private float temperature;
		private String weather, icon, time;
		
		public ShortForecastEntry(String time, String weather, String icon, float temperature){
			this.time = time;
			this.temperature = temperature;
			this.weather = weather;
			this.icon = icon;
		}
		
		public String toString(){
			return  "Time " + time + "\n" +
					"Weather " + weather + "\n" +
				   "Icon " + icon + "\n" +
					"temperature in K " + getTemp(0) + "\n" +
					"temperature in C " + getTemp(1) + "\n" +
					"temperature in F " + getTemp(2);
		}
		
		public String getTime(){
			return time;
		}
		
		public String getWeather(){
			return weather;
		}
		
		public String getIcon(){
			return icon;
		}
		
		public String getTemp(int unit){
			if(unit == 0){
				return Math.round(temperature) + " K";
			}
			if(unit == 1){
				return Math.round(temperature - 273.15) + " C";
			}
			return Math.round(temperature * 9 / 5 - 459.67) + " F";
		}
	}