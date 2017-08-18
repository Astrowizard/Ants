package ants;

import java.util.LinkedList;
import java.util.Random;

public class Ant {
	
	int[][] board;
	private int[] end = new int[2];
	int[] start = new int[2];
	private int[] loc = new int[2];
	int[] dir = {-1,0,1};
	int[] lastloc = new int[2];
	Pathing pathing = new Pathing();

	Random r = new Random();
	
	public Ant() {
		board = new int[20][20];
		createBoard();
	}

	public Ant(int[] startloc) {
		board = new int[20][20];
		start = startloc;
		int[] e = {18,9};
		setEnd(e);
		setLoc(start);
		lastloc = getLoc();
	}
	
	public void step(){
		int[] newloc={0,0};
		for (int i = 0; i < 2; i++){
		if (getLoc()[i]<getEnd()[i]){
			newloc[i] = getLoc()[i] + 1;
		}
		else if (getLoc()[i]>getEnd()[i]){
			newloc[i] = getLoc()[i] - 1;
		}
		else newloc[i] = getLoc()[i];
		}
		lastloc = getLoc();
		setLoc(newloc);
		//step();
	}
	
	public int[] step1(int[] step){
		int[] newloc= new int[2];
		newloc[0] = step[0];
		newloc[1] = step[1];
		for (int i = 0; i < 2; i++){
		if (newloc[i]<getEnd()[i]){
			newloc[i] = newloc[i] + 1;
		}
		else if (newloc[i]>getEnd()[i]){
			newloc[i] = newloc[i] - 1;
		}
		else newloc[i] = newloc[i];
		}
		lastloc = getLoc();
		setLoc(newloc);
		return newloc;//step();
	}
	
	public LinkedList<int[]> path(){
		LinkedList<int[]> l1 = new LinkedList<int[]>();
		l1 = pathing.getPath(loc, end, board);
		return l1;
	}

	public void createBoard(){
		Random r = new Random();
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if (r.nextDouble()>.7){
					board[i][j] = 1;
				}
				//if (r.nextDouble()>.9 && board[i][j] != 1){
				//	board[i][j] = 2;
				//}
			}
		}
	}
	
	public int[] getEnd() {
		return end;
	}

	public void setEnd(int[] end) {
		this.end = end;
	}

	public int[] getLoc() {
		return loc;
	}

	public void setLoc(int[] loc) {
		this.loc = loc;
	}

	public void setEnd(int i, int j) {
		end[0] = i;
		end[1] = j;
		
	}

	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] board) {
		this.board = board;
	}

}
