
package io;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 * Preference object to store user preference for location and temperature unit
 * This object implements serializable interface
 * User preference persist between run
 * 
 * @author ca.uwo.csd.cs2212.team8
 */
public class Preference implements Serializable {

	// The user's current location
	private String location;
	// The user's temperature unit
	private int tempUnit;
	// ID for identify data
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor to use empty location and unit
	 */
	public Preference(){
		this("", 1);
	}
	
	/**
	 * constructor with location and unit specified
	 * @param location user's current location
	 * @param tempUnit user's temperature unit
	 */
	public Preference(String location, int tempUnit){
		this.location = location;
		this.tempUnit = tempUnit;
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
	 * method to make a copy of the preference
	 * @return Preference a copy of the current preference
	 */
	public Preference clone(){
		Preference p = new Preference(this.getLocation(), this.getTempUnit());
		return p;
	}
	
	/**
	 * test method for Preference Object
	 * @param args System input arguments
	 */
	public static void main(String[] args) {
		String path;
		try {
			path = URLDecoder.decode(Preference.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			System.out.println(path);
			Preference p = new Preference("London,ca", 1);
			File f = new File(path + ".Preference");
			f.createNewFile();
			System.out.println("File Not Exist, Created");
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path + ".Preference"));
			output.writeObject(p);
			output.close();
		} catch (UnsupportedEncodingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
