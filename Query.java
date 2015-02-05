/**
 * class to fetch data from internet
 * @author Team8
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
public class Query {
	private String JSon;
	private String address;
	private final int CURRENT_WEATHER = 0,
					  THREE_HOUR_FORECAST = 1,
					  DAILY_FORECAST = 2;
	public Query(String location, int type){
		switch(type){
		case CURRENT_WEATHER:
			address = "Http://api.openweathermap.org/data/2.5/weather?q=" + location +
			"&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case THREE_HOUR_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast?q=" + location + 
			"&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		case DAILY_FORECAST:
			address = "http://api.openweathermap.org/data/2.5/forecast/daily?q=" + location + 
			"&cnt=6&mode=json&APPID=1b9a3efc4f0b62ef0b0ba00532015985";
			break;
		default:
			address = "http://marsweather.ingenology.com/v1/latest/?format=json";
		}
		try{
			URL url = new URL(address);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			JSon = br.readLine();	
		}catch(Exception e){
			System.out.println("Query Problem\n");
		}
	}
	
	public String toString(){
		return this.JSon;
	}
	
	public static void main(String[] args){
		Query getter = new Query("London,gb", 2);
		System.out.println(getter);
		//getter = new Query("",3);
		//System.out.println(getter);
	}
	
}
