package application;

import java.util.Random;
import java.util.Scanner;

public class Minefield {
	
	private int rowSize;
	private int colSize;
	private int mines;
	private int[][] field;
	boolean gameOver = false;
	public Minefield(int rowSize,int colSize, int mines) {
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.mines = mines;
		field = new int[rowSize][colSize];
	}
	public void placeMines() {
		Random rnd = new Random();
		int counter = 0;
		while(counter < mines) {
			int innercount = 0;
			field[rnd.nextInt(this.rowSize)][rnd.nextInt(this.colSize)] = 9;
			for(int i[] : field) {
				for(int j : i) {
					if(j == 9) {
						innercount++;
					}
				}
			}
			if(innercount>counter) {
				counter++;
			}
			
		}
	}
	public void output() {
		
		for(int i = 0; i< rowSize; i++) {
			for(int j = 0; j < colSize; j++) {
				System.out.print("["+field[i][j]+"]");
			}
			System.out.println();
		}
	}
	public boolean testIfMineHit(int row, int col) {
		
		if(field[row][col] == 9) {
			System.out.println("Bomb hit");
			output();
			return true;
		}
		else {
			System.out.println("No Bomb");
			floodFill(row,col,false);
			field[row][col] = -1;
			minesNearby(row,col);
			output();
			return false;
		}
		
	}
	public void play() {
		Scanner sc = new Scanner(System.in);
		int x=Integer.parseInt(sc.nextLine());
		int y=Integer.parseInt(sc.nextLine());
		testIfMineHit(x,y);
		while(!gameOver) {
			x = Integer.parseInt(sc.nextLine());
			y = Integer.parseInt(sc.nextLine());
			testIfMineHit(x,y);
		}
	}
	public int isMine(int mines, int row, int col) {
		try {
		if(field[row][col] == 9) {
			return mines+1;
		}
		return mines;
		}
		catch(IndexOutOfBoundsException ex) {
		return mines;
		}
	}
	public boolean minesNearby(int row,int col) {
		int mines = 0;
			
			
			//North
			mines = isMine(mines,row-1,col);
			//North-East
			mines = isMine(mines,row-1,col+1);
			//East
			mines = isMine(mines,row,col+1);
			//South-East
			mines = isMine(mines,row+1,col+1);
			//South
			mines = isMine(mines,row+1,col);
			//South-West
			mines = isMine(mines,row+1,col-1);
			//West
			mines = isMine(mines,row,col-1);
			//North-West
			mines = isMine(mines,row-1,col-1);

			if(mines > 0) {
				field[row][col] = mines;
				return true;
			}
			return false;
			
	}
	
	public int[][] getField(){
		return field;
	}
	
	public void floodFill( int row , int col,boolean nearby) {
		   if ( field[row][col] == 0 && nearby == false) {
			   field[row][col] = -1;
			   nearby = minesNearby(row,col);
		       tryFill( row+1, col,nearby);
		       tryFill( row-1, col,nearby);
		       tryFill( row, col-1,nearby);
		       tryFill( row, col+1,nearby);
		   } else {
		       return;
		   }
	}
	public void tryFill(int row, int col,boolean nearby) {
		try {
			 floodFill( row, col,nearby);
		}
		catch(IndexOutOfBoundsException ex) {
		}
	}
	
}
