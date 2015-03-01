import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LForecastPanel extends JPanel{

	public LForecastPanel(int num){
	
		//Temp, sky		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.setBackground(Color.yellow);
		JLabel tempLabel=new JLabel("<html><p style=\"font-size:30px\">"+(num+1)+"00&deg C</p></html>");
		tempLabel.setBounds(10,10,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		this.add(tempLabel);
		
		//Sky
		JLabel sunLabel=new JLabel("<html><p style=\"font-size:12px\">Black Skies</p></html>");
		sunLabel.setBounds(120,60,(int)sunLabel.getPreferredSize().getWidth(),(int)sunLabel.getPreferredSize().getHeight());
		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		this.add(sunLabel);
		
		
		setPreferredSize(new Dimension(262,127));
		setMinimumSize(new Dimension(262,127));
		setMaximumSize(new Dimension(5000,5000));
		this.setBackground(Color.PINK);
	}
}
