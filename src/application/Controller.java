package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {

	private int row;
	private int col;
	private int mines;
	boolean start;
	Minefield minefield;

	public Controller(int row, int col, int mines) {
		start = false;
		this.row = row;
		this.col = col;
		this.mines = mines;
		minefield = new Minefield(this.row, this.col, this.mines);

	}

	public GridPane initGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		MineButton[][] btns = new MineButton[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btns[i][j] = new MineButton(i, j);
				btns[i][j].setPrefWidth(25);
				btns[i][j].setPrefHeight(25);
				grid.add(btns[i][j], j, i);
				MineButton btn = btns[i][j];
				btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						MouseButton button = e.getButton();

						if (button == MouseButton.PRIMARY) {
							if (start == false) {
								minefield.placeMines(btn.getRow(), btn.getCol());
								start = true;
							}
							minefield.testIfMineHit(btn.getRow(), btn.getCol());
							updateGrid(btns);
						}						
						if (button == MouseButton.SECONDARY) {
							System.out.println("LONK");
						}
					}
				});
			}
		}
		updateGrid(btns);

		return grid;
	}

	public void updateGrid(MineButton[][] btns) {
		int field[][] = minefield.getField();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (field[i][j] != 9 && field[i][j] != 0 && field[i][j] != -1) {
					btns[i][j].setText(Integer.toString(field[i][j]));
				}
				if (field[i][j] == -1) {
					btns[i][j].setId("uncovered");
				}
				if (field[i][j] == 9 && minefield.getGameOver()) {
					btns[i][j].setId("uncoverMines");
				}

			}
		}
	}
}
