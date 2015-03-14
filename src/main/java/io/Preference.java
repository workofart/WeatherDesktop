
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
 * object to store user preference
 * @author team 8
 */
public class Preference implements Serializable {

	// The user's current location
	
	private String location;
	//The user's temperature unit
	private int tempUnit;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor to use default location and unit
	 */
	public Preference(){
		this("", 1);
	}
	
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
