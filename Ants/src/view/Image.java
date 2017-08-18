package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Image {

	BufferedImage surface;
	private JLabel view;
	Color[] colors = {Color.BLACK,Color.PINK,Color.RED,Color.ORANGE,Color.YELLOW,Color.GREEN,Color.CYAN,Color.BLUE,
			Color.MAGENTA,Color.WHITE};
	public int b = 10;
	
	Color color;
	
	public Image(){
		setView(new JLabel());
	}
	

	public Image(int blocksize, int Z, int[] end, int[] loc, int[][] board){
		
		b = blocksize;
		int z = Z;		
		
		while (z*b>800){b-=1;}
		
		surface = new BufferedImage(z*b,z*b,BufferedImage.TYPE_INT_RGB);
		setView(new JLabel(new ImageIcon(surface)));
		Graphics g = surface.getGraphics();
		//g.setColor(new Color(139,30,0));
		//g.fillRect(0, 0, z*b, z*b);
		g.setColor(Color.BLACK);
		for (int i= b;i<z*b;i=i+b){ //board grid draw
			g.drawLine(i, 0, i, z*b);
			g.drawLine(0, i, z*b, i);
		}
		for (int i = 0; i<z; i++){ //character, obstacle, path, end draw
			for (int j = 0; j<z; j++){
				if (i == loc[0] && j == loc[1]){
					color = colors[0];
				}
				else if (i == end[0] && j == end[1]){
					color = colors[2];
				}
				else if(board[i][j] == 1){
					color = colors[7];
				}
				else{
					color = colors[9];
				}
				drawRect(i,j,color, g);
			}
		}
	}
	
	public Image(int blocksize, int Z, int[] end, int[] loc, LinkedList<int[]> l1, int[][] board){
		
		b = blocksize;
		int z = Z;		
		
		while (z*b>800){b-=1;}
		
		surface = new BufferedImage(z*b,z*b,BufferedImage.TYPE_INT_RGB);
		setView(new JLabel(new ImageIcon(surface)));
		Graphics g = surface.getGraphics();
		//g.setColor(new Color(139,30,0));
		//g.fillRect(0, 0, z*b, z*b);
		g.setColor(Color.BLACK);
		for (int i= b;i<z*b;i=i+b){
			g.drawLine(i, 0, i, z*b);
			g.drawLine(0, i, z*b, i);
		}
		for (int i = 0; i<z; i++){
			for (int j = 0; j<z; j++){
				int[] z1 = {i,j};
				color = colors[9];
				for (int k = 0; k < l1.size(); k++){
					if (Arrays.equals( l1.get(k), z1)){
						color = colors[5];
					}
				}
				if (i == loc[0] && j == loc[1]){
					color = colors[0];
				}
				else if (i == end[0] && j == end[1]){
					color = colors[2];
				}
				if (board[i][j] ==1){
					color = colors[7];
				}
				if (board[i][j] == 2){
					color = colors[4];
				}
				drawRect(i,j,color, g);
			}
		}
		
	}
	
	public void drawRect(int x, int y, Color color, Graphics g){
		
		int x1 = x*b;
		int y1 = y*b;
		g.setColor(color);
		g.fillRect(x1, y1, b-1, b-1);
		
	}
	
	public void repaint(){
    	view.setIcon(new ImageIcon(surface));
    	view.repaint();
	}


	public JLabel getView() {
		return view;
	}


	public void setView(JLabel view) {
		this.view = view;
	}

}
