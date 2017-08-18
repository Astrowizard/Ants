package ants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Pathing {

	int[] dir = {-1,0,1};
	
	public Pathing() {
	}
	
	public LinkedList<int[]> getPath(int[] place, int[] end, int[][] bb) {

		Node[][] board = createBoard(bb, end);
		
		Comparator<Node> comparator = new NodeComparator(place, end);
		PriorityQueue<Node> open = new PriorityQueue<Node>(10,comparator);
		if (board[place[0]][place[1]] == null){
			System.out.println("first is null");
		}
		open.add(board[place[0]][place[1]]);
		
		ArrayList<Node> memq = new ArrayList<Node>();
		memq.add(board[place[0]][place[1]]);
		
		//ArrayList<Node<int[]>> closed = new ArrayList<Node<int[]>>();
		
		Node current = null; 
		Node goal = new Node(end);
		
		board[end[0]][end[1]] = goal;
		
		double cost = 0;
		
		while (!open.isEmpty()){
			
			current = open.poll();
			//memq.remove(memq.indexOf(current));
			if (current.equals(goal)){
				break;
			}
			
			for (int i = 0; i < 3; i++){
				for (int j = 0; j < 3; j++){
					int x = current.data[0]+dir[i];
					int y = current.data[1]+dir[j];
					int[] n = {x,y};
					if ((i == 1 && j == 1) || x >= board.length || y >= board[0].length || x < 0 || y < 0){}
					else{
						if (board[x][y] != null){
							cost = current.gcost + g(current.data,n);
							if (!memq.contains(board[x][y]) || cost < board[x][y].gcost){
								board[x][y].setGcost(cost);
								open.add(board[x][y]);
								memq.add(board[x][y]);
								board[x][y].setCameFrom(current);
							}
						}
					}
				}
			}
			
		}
		
		if (current != null){
			return buildPath(current);
		}
		
		return new LinkedList<int[]>();
		
	}
	
	public Node[][] createBoard(int[][] bb,int[] end){
		Node[][] board = new Node[bb.length][bb[0].length];
		for (int i = 0; i < bb.length; i++){
			for (int j = 0; j < bb[0].length; j++){
				if (bb[i][j] == 0){
					int[] in = {i,j};
					board[i][j] = new Node(in);
				}
			}
		}
		return board;
	}
	
	public double H(int[] current, int[] goal){
		
		double dx = Math.abs(current[0] - goal[0]);
		double dy = Math.abs(current[1] - goal[1]);
		
		double D = 1;
		double D2 = Math.sqrt(2);
		return D*(dx + dy) + (D2 - 2 * D) * Math.min(dx,dy);
	}
	
	public double g(int[] start, int[] current){
		double dx = Math.abs(start[0] - current[0]);
		double dy = Math.abs(start[1] - current[1]);
		
		double D = 1;
		double D2 = Math.sqrt(2);
		return D*(dx + dy) + (D2 - 2 * D) * Math.min(dx,dy);
	}
	
	public double f(int[] start, int[] current, int[] end){
		double f = g(start,current) + H(current, end);
		return f;
	}
	
	public class Node{
		
		public int[] data;

		public Node cameFrom;
		public double gcost = 0;
		
		public Node(){
			
		}
		
		public Node(int[] dataIn){
			this.data = dataIn;
		}
		
		public Node(Node cF, double gC){
		//	this.data = dataIn;
			this.cameFrom = cF;
			this.gcost = gC;
		}
		
		public int[] getData() {
			return data;
		}

		public void setData(int[] data) {
			this.data = data;
		}

		public double getGcost() {
			return gcost;
		}

		public void setGcost(double gcost) {
			this.gcost = gcost;
		}
		
		public Node getCameFrom(){
			return this.cameFrom;
		}
		
		public void setCameFrom(Node node){
			this.cameFrom = node;
		}
		
	}
	
	private class NodeComparator implements Comparator<Node>{

		int[] end;
		int[] start;
		
		public NodeComparator(int[] end, int[] start){
			super();
			this.start = start;
			this.end = end;
		}
		
		public int compare(Node arg0, Node arg1) {
			if (f(start, arg0.data, end) > f(start, arg1.data, end)) return 1;
			if (f(start, arg0.data, end) < f(start, arg1.data, end)) return -1;
			return 0;
		}
		
		
	}
	
	private LinkedList<int[]> buildPath(Node n){
		
		LinkedList<int[]> path = new LinkedList<int[]>();
		
		path.addFirst(n.data);
		
		while (n.getCameFrom() != null){
			path.addFirst(n.getCameFrom().data);
			n = n.getCameFrom();
		}
		
		return path;
	}
}
