package ui;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	public TodayPanel tp=null;
	public MainPanel(){
		super();
	}
	
	public void setTPanel(TodayPanel tp){
		this.tp=tp;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// load the background picture from resource
		ClassLoader cl = this.getClass().getClassLoader();
		try {
			// paint the background using the picture
			if(tp!=null){
			    g.drawImage(Main.imageDarken(ImageIO.read(cl.getResource("new_UI_02"+tp.getSkyString()+".png"))),0, 280,null);
			}else{
			    g.drawImage(Main.imageDarken(ImageIO.read(cl.getResource("new_UI_02.png"))),0, 280,null);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
