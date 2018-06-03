package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Controller {

	private int row;
	private int col;
	private int mines;
	private boolean start;
	private Minefield minefield;

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
		grid.setHgap(0.5);
		grid.setVgap(0.5);
		MineButton[][] btns = new MineButton[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btns[i][j] = new MineButton(i, j);
				btns[i][j].setPrefWidth(30);
				btns[i][j].setPrefHeight(30);
				grid.add(btns[i][j], j, i);
				MineButton btn = btns[i][j];
				btn.setId("default");
				btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						MouseButton button = e.getButton();

						if (button == MouseButton.PRIMARY) {
							if (start == false) {
								minefield.placeMines(btn.getRow(), btn.getCol());
								start = true;
							}
							if(btn.getId().compareTo("flag")!=0)
							{
								minefield.testIfMineHit(btn.getRow(), btn.getCol());
								updateGrid(btns);
							}
						}						
						if (button == MouseButton.SECONDARY) {
							if(btn.getId().compareTo("default")==0) {
								minefield.setFlag(btn.getRow(), btn.getCol());
								btn.setText("🚩");
								btn.setId("flag");
							}
							else if(btn.getId().compareTo("flag")==0) {
								minefield.removeFlag(btn.getRow(), btn.getCol());
								btn.setText("");
								btn.setId("default");
							}
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
				switch(field[i][j]) {
					case -1:
						btns[i][j].setId("uncovered");
						break;
					case 1:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.BLUE);
						break;
					case 2:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.GREEN);
						break;
					case 3:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.RED);
						break;
					case 4:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.DARKBLUE);
						break;
					case 5:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.BROWN);
						break;
					case 6:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.DARKCYAN);
						break;
					case 7:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.BLACK);
						break;
					case 8:
						btns[i][j].setText(Integer.toString(field[i][j]));
						btns[i][j].setId("uncovered");
						btns[i][j].setTextFill(Color.GRAY);
						break;
				}
				if(field[i][j] == 9 && minefield.getGameOver()) {
					btns[i][j].setId("uncoverMines");
					btns[i][j].setText("💣");

				}


			}
		}
	}
}
