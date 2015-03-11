
package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import data.JSONObject;

/**
 * object to store user preference
 * @author team 8
 */
public class Preference {
	// attribute for current location and temperature unit
	private String location;
	private int tempUnit;
	private final String file = "Preference";
	
	/**
	 * constructor to use default location and unit
	 */
	public Preference(){
		location = "London,ca";
		tempUnit = 1;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the tempUnit
	 */
	public int getTempUnit() {
		return tempUnit;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param tempUnit the tempUnit to set
	 */
	public void setTempUnit(int tempUnit) {
		this.tempUnit = tempUnit;
	}
	
	/**
	 * method to read from the preference file and set the attributes
	 */
	public void read(){
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream input = cl.getResourceAsStream(file);
		InputStreamReader reader = new InputStreamReader(input);
		try{
			JSONObject pref = new JSONObject(reader.read());
			location = pref.getString("location");
			tempUnit = pref.getInt("tempUnit");
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * method to save the current attributes to the file as JSON text
	 */
	public void write(){
		try{
			FileWriter writer = new FileWriter(file);
			writer.write("{\"init\":\"false\",\"location\":\"" + location + "\",\"tempUnit\":\"" + tempUnit + "\"}");
			writer.flush();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * helper method to initiate the preference file
	 */
	private void init(){
		try{
			ClassLoader cl = this.getClass().getClassLoader();
			FileWriter writer = cl.getResourceAsStream(file);
			writer.write("{\"init\":\"true\",\"location\":\" null \",\"tempUnit\":\"null\"}");
			writer.flush();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * static test method to initialize the preference file
	 * @param args
	 */
	public static void main(String[] args){
		Preference p = new Preference();
		p.init();
	}
}
