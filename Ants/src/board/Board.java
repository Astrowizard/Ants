package board;

public class Board {
	
	int[][] board;
	int[][] weightedboard;
	int[] end = new int[2];
	int[] start = new int[2];

	public Board() {
		board = new int[20][20];
		int[] s = {2,4};
		start = s;
		int[] e = {9,17};
		end = e;
	}
	
	

}
