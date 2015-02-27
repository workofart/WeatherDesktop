package ca.uwo.csd.cs2212.zwang688;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App{
	static Logger logger = LogManager.getLogger(App.class.getName());
	public static void main(String[] args){
		logger.trace("Entering main");
		logger.warn("Hello Maven/log4j World");
		logger.info("My username is zwang688");
		logger.trace("Exiting main");
	}
}
