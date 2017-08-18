package controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import ants.Ant;
import view.Image;
import view.Window;

public class Control {
	
	Ant ant1;
	Image img;
	Window window;
	Thread t = new Thread(new threader());
	Random r = new Random();
	LinkedList<int[]> l1;
	boolean showPath = false;
	
	int x;
	int y;
	int[] location = new int[2];
	int[][] golocs = new int[2][3];
	int[][] board;
	
	boolean go = true;
	
	int count = 0;
	int gocount = 1;
	
	public Control() {
		
		ant1 = new Ant();
		board = ant1.getBoard();
		do{
			x = r.nextInt(20);
			y = r.nextInt(20);
		}while(board[x][y] == 1);
		
		int[] s = {x,y};
		ant1.setLoc(s);
		
		ant1 = new Ant();
		board = ant1.getBoard();
		do{
			x = r.nextInt(20);
			y = r.nextInt(20);
		}while(board[x][y] == 1);
		
		int[] e = {x,y};
		ant1.setEnd(e);
		
		img = new Image(20,20, ant1.getEnd(), ant1.getLoc(), ant1.getBoard());
		window = new Window(img.getView());
		
		l1 = (LinkedList<int[]>) ant1.path();
		count = 0;
		t.run();
	}
	
	public void advance(){

		if (count<l1.size()){
			location[0] = l1.get(count)[0];
			location[1] = l1.get(count)[1];
		}
		if (showPath)
		{
			img = new Image(20,20, ant1.getEnd(), location, l1, ant1.getBoard());
		}
		else img = new Image(20,20, ant1.getEnd(), location, ant1.getBoard());
		window.repaint(img);
		count++;
		
		if (Arrays.equals(location,ant1.getEnd()))
		{
			do{
				x = r.nextInt(20);
				y = r.nextInt(20);
			}while(board[x][y] == 1);
			System.out.println(x + ", " + y);
			ant1.setEnd(x,y);
			ant1.setLoc(location);
			l1 = ant1.path();
			count = 0;
		}
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		Control control = new Control();
	}

	public class threader implements Runnable{
		
		public void run(){
			try{
				while(go){
						advance();
						Thread.sleep(50);
				}}
			catch(Exception e){
				e.printStackTrace(System.out);
			}
		}
	}

}
