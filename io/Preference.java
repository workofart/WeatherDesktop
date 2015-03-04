package io;

public class Preference {
	private String location;
	private int tempUnit;
	
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
