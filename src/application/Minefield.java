package application;

import java.util.Random;
import java.util.Scanner;

public class Minefield {

	private int rowSize;
	private int colSize;
	private int mines;
	private int[][] field;
	private boolean gameOver;

	public Minefield(int rowSize, int colSize, int mines) {
		this.rowSize = rowSize;
		this.colSize = colSize;
		this.mines = mines;
		field = new int[rowSize][colSize];
		gameOver = false;
	}

	public void placeMines(int row, int col) {
		Random rnd = new Random();
		int counter = 0;
		while (counter < mines) {
			int innercount = 0;
			int rndRow = rnd.nextInt(this.rowSize);
			int rndCol = rnd.nextInt(this.colSize);
			if (row == rndRow && col == rndCol) {

			} else {
				field[rndRow][rndCol] = 9;
			}
			if (minesNearby(row, col) && counter < colSize * rowSize - 9) {
				field[rndRow][rndCol] = 0;
			}
			for (int i[] : field) {
				for (int j : i) {
					if (j == 9) {
						innercount++;
					}
				}
			}
			if (innercount > counter) {
				counter++;
			}
		}
	}

	public void output() {

		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				System.out.print("[" + field[i][j] + "]");
			}
			System.out.println();
		}
	}

	public boolean testIfMineHit(int row, int col) {

		if (field[row][col] == 9) {
			System.out.println("Bomb hit");
			gameOver = true;
			//output();
			return true;
		} else {
			System.out.println("No Bomb");
			floodFill(row, col, false);
			field[row][col] = -1;
			field[row][col] = minesNearbyAmount(row, col);
			//output();
			return false;
		}

	}

	public void play() {
		Scanner sc = new Scanner(System.in);
		int x = Integer.parseInt(sc.nextLine());
		int y = Integer.parseInt(sc.nextLine());
		testIfMineHit(x, y);
		while (!gameOver) {
			x = Integer.parseInt(sc.nextLine());
			y = Integer.parseInt(sc.nextLine());
			testIfMineHit(x, y);
		}
	}

	public boolean isMine(int row, int col) {
		try {
			if (field[row][col] == 9) {
				return true;
			}
			return false;
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}

	public boolean minesNearby(int row, int col) {

		boolean nearby = false;
		// North
		if (isMine(row - 1, col)) {
			nearby = true;
		}
		// North-East
		if (isMine(row - 1, col + 1)) {
			nearby = true;
		}
		// East
		if (isMine(row, col + 1)) {
			nearby = true;
		}
		// South-East
		if (isMine(row + 1, col + 1)) {
			nearby = true;
		}
		// South
		if (isMine(row + 1, col)) {
			nearby = true;
		}
		// South-West
		if (isMine(row + 1, col - 1)) {
			nearby = true;
		}
		// West
		if (isMine(row, col - 1)) {
			nearby = true;
		}
		// North-West
		if (isMine(row - 1, col - 1)) {
			nearby = true;
		}

		if (nearby) {
			return true;
		}
		return false;

	}

	public int minesNearbyAmount(int row, int col) {

		int mines = 0;
		// North
		if (isMine(row - 1, col)) {
			mines++;
		}
		// North-East
		if (isMine(row - 1, col + 1)) {
			mines++;
		}
		// East
		if (isMine(row, col + 1)) {
			mines++;
		}
		// South-East
		if (isMine(row + 1, col + 1)) {
			mines++;
		}
		// South
		if (isMine(row + 1, col)) {
			mines++;
		}
		// South-West
		if (isMine(row + 1, col - 1)) {
			mines++;
		}
		// West
		if (isMine(row, col - 1)) {
			mines++;
		}
		// North-West
		if (isMine(row - 1, col - 1)) {
			mines++;
		}

		if (mines > 0) {
			return mines;
		}
		return -1;

	}

	public int[][] getField() {
		return field;
	}

	public void floodFill(int row, int col, boolean nearby) {

		if (field[row][col] == 0 && nearby == false) {
			field[row][col] = -1;
			field[row][col] = minesNearbyAmount(row, col);

			nearby = minesNearby(row, col);
			tryFill(row - 1, col, nearby);
			tryFill(row - 1, col + 1, nearby);
			tryFill(row, col + 1, nearby);
			tryFill(row + 1, col + 1, nearby);
			tryFill(row + 1, col, nearby);
			tryFill(row + 1, col - 1, nearby);
			tryFill(row, col - 1, nearby);
			tryFill(row - 1, col - 1, nearby);
		} else {
			return;
		}
	}

	public void tryFill(int row, int col, boolean nearby) {
		try {
			floodFill(row, col, nearby);
		} catch (IndexOutOfBoundsException ex) {
		}
	}

	public boolean getGameOver(){
		return gameOver;
	}
}

