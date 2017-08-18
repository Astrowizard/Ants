package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {
	
	JFrame f = new JFrame();
	Image img = new Image();

	public Window() {
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setFocusable(true);
        f.setResizable(true);
        f.setVisible(true);
        f.pack();
	}
	
	public Window(JLabel p1){
		f.add(p1);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setFocusable(true);
        f.setResizable(true);
        f.setVisible(true);
        f.pack();
	}
	
	public void repaint(Image imgn){
    	img = imgn;
    	img.repaint();
    	f.setContentPane(img.getView());
    	f.pack();
    	f.setVisible(true);
	}

}
