package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import weather.CurrentWeather;
import weather.LongForecast;
import weather.ShortForecast;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	private JFrame mainFrame;
	private JPanel shortForecast, longForecast, current;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setBounds(0, 0, 800, 1000);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		current = new CurrentWeatherUI(new CurrentWeather("London,ca"),1);
		mainFrame.getContentPane().add(current);
		current.setLocation(11, 11);
		
		longForecast = new LongForecastUI(new LongForecast("London,ca"),1);
		longForecast.setSize(150, 640);
		current.add(longForecast);
		longForecast.setLocation(300, 26);
		
		shortForecast = new ShortForecastUI(new ShortForecast("London,ca"),1);
		shortForecast.setSize(170, 640);
		current.add(shortForecast);
		shortForecast.setLocation(614, 26);
		
		JButton btnPreference = new JButton("Preference");
		mainFrame.getContentPane().add(btnPreference, BorderLayout.NORTH);
		btnPreference.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PreferenceUI pref = new PreferenceUI();
			}
		});
		
		}
	}

