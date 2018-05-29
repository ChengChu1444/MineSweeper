package application;

import java.util.Random;
import java.util.Scanner;

public class Minefield {
	
	private int rowSize;
	private int colSize;
	private int mines;
	int[][] field;
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
	public int isMine(int mines, int col, int row) {
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
	public void minesNearby(int col,int row) {
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
				field[col][row] = mines;
			}
			
	}
	
	
	public void water(int col, int row) {
				
				/*
				if(col < colSize-1) {
				if(field[col+1][row]<9) {
					field[col+1][row]=-1;
				}
				else{
					water(col+1,row);
				}}
				if(col > 0) {
				if(field[col-1][row]<9) {
					field[col-1][row]=-1;
				}
				else {
					water(col-1,row);
				}}
				if(row<rowSize-1) {
				if(field[col][row+1]<9) {
					field[col][row+1]=-1;
				}
				else {
					water(col,row+1);
				}}
				if(row>0) {
				if(field[col][row-1]<9) {
					field[col][row]=-1;
				}
				else {
					water(col,row-1);
				}}
				*/
			
		
	}
}
