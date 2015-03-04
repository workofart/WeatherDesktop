import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TodayPanel extends JPanel{

	public TodayPanel(){
		this.setBackground(Color.cyan);
		this.setMinimumSize(new Dimension(0,285));
		this.setPreferredSize(new Dimension(525,(int)(910*0.3)));
		//Temp, wind, pressure, humidity, sun, sky
		
		
		//Temp
		JLabel tempLabel=new JLabel();
		tempLabel.setOpaque(true);
		tempLabel.setBackground(Color.green);
		tempLabel.setText("<html><p style=\"color:blue; font-size:75px\">5&deg C</p></html>");
//		tempLabel.setFont(new Font("Serif",Font.BOLD,50));
//		System.out.println(this.getHeight());
		tempLabel.setBounds(50,50,(int)tempLabel.getPreferredSize().getWidth(),(int)tempLabel.getPreferredSize().getHeight());
//		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		this.add(tempLabel);
		
		//Wind
		JLabel winLabel=new JLabel();
		winLabel.setOpaque(true);
		winLabel.setBackground(Color.green);
		winLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>54</b>knots NE</p></html>");
		winLabel.setBounds(330,100,(int)winLabel.getPreferredSize().getWidth()+5,(int)winLabel.getPreferredSize().getHeight()+5);
		this.add(winLabel);
		//Pressure
		JLabel presLabel=new JLabel();
		presLabel.setOpaque(true);
		presLabel.setBackground(Color.green);
		presLabel.setText("<html><p style=\"color:blue; font-size:16px\"><b>54</b>mmHg</p></html>");
		presLabel.setBounds(330,150,(int)presLabel.getPreferredSize().getWidth()+5,(int)presLabel.getPreferredSize().getHeight()+5);
		this.add(presLabel);
		//Humidity
		JLabel humLabel=new JLabel();
		humLabel.setOpaque(true);
		humLabel.setBackground(Color.green);
		humLabel.setText("<html><p style=\"color:blue; font-size:16px\">93% humidity</p></html>");
		humLabel.setBounds(330,200,(int)humLabel.getPreferredSize().getWidth()+5,(int)humLabel.getPreferredSize().getHeight()+5);
		this.add(humLabel);
		//Sun
		JLabel sunLabel=new JLabel();
		sunLabel.setOpaque(true);
		sunLabel.setBackground(Color.green);
		sunLabel.setText("<html><p style=\"color:blue; font-size:16px\">Cloudy</p></html>");
		sunLabel.setBounds((int)tempLabel.getPreferredSize().getWidth()-30,(int)tempLabel.getPreferredSize().getHeight()+40,(int)sunLabel.getPreferredSize().getWidth()+5,(int)sunLabel.getPreferredSize().getHeight()+5);
		this.add(sunLabel);
		
	}
	
	

	
	
	public static void main(String[] args) {
		JFrame frame=new JFrame("Test Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints con=new GridBagConstraints();
		
		//Panel
		con.fill=GridBagConstraints.BOTH;
		con.gridx=0;
		con.gridy=0;
		con.gridwidth=2;
		con.gridheight=1;
		con.weighty=0.4;
		con.weightx=1;
		TodayPanel panel=new TodayPanel();
		frame.add(panel,con);
		
		
		//*****Short Term Panels****
		JPanel spanels = new JPanel();
		spanels.setBackground(Color.magenta);
		spanels.setLayout(new BoxLayout(spanels,BoxLayout.PAGE_AXIS));
		spanels.setPreferredSize(new Dimension(262,(int)(910*0.7)));
		SForecastPanel[] spanelarray=new SForecastPanel[8];
		for(int i=0;i<spanelarray.length;i++){
			spanelarray[i]=new SForecastPanel(i);
			spanels.add(spanelarray[i]);
		}
		con.fill = GridBagConstraints.BOTH;
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth=1;
		con.gridheight=1;
//		con.weightx=0.5;
		con.weighty=0.7;
		frame.add(spanels, con);

		
		
		//*****Long Term Panels****
		JPanel lpanels = new JPanel();
		
		lpanels.setBackground(Color.gray);
		lpanels.setLayout(new BoxLayout(lpanels,BoxLayout.PAGE_AXIS));
		lpanels.setPreferredSize(new Dimension(262,(int)(910*0.7)));
		LForecastPanel[] lpanelarray=new LForecastPanel[5];
		for(int i=0;i<lpanelarray.length;i++){
			lpanelarray[i]=new LForecastPanel(i);
			lpanels.add(lpanelarray[i]);
		}
		con.fill = GridBagConstraints.BOTH;
		con.gridx = 1;
		con.gridy = 1;
		con.gridwidth=1;
		con.gridheight=1;
//		con.weightx=0.5;
		con.weighty=0.7;
		lpanels.setBackground(Color.magenta);
		frame.add(lpanels, con);

		
		
		
		
		
		panel.setLayout(null);
		frame.pack();
		frame.setSize(525,950);
		frame.setVisible(true);
	}

}
