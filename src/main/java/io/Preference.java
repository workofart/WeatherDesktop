
package io;
/**
 * object to store user preference
 * @author team 8
 */
public class Preference {
	// attribute for current location and temperature unit
	private String location;
	private int tempUnit;
	
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
	
	
}
