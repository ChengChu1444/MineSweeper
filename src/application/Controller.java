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
	private MineButton[][] btns;

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
		btns = new MineButton[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btns[i][j] = new MineButton(i, j);
				btns[i][j].setPrefWidth(32);
				btns[i][j].setPrefHeight(32);
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
					case 0: 
						btns[i][j].setId("default");
						break;
					case -1:
						btns[i][j].setId("uncovered");
						break;
					case 1:
						btns[i][j].setId("one");
						break;
					case 2:
						btns[i][j].setId("two");
						break;
					case 3:
						btns[i][j].setId("three");
						break;
					case 4:
						btns[i][j].setId("four");
						break;
					case 5:
						btns[i][j].setId("five");
						break;
					case 6:
						btns[i][j].setId("six");
						break;
					case 7:
						btns[i][j].setId("seven");
						break;
					case 8:
						btns[i][j].setId("eight");
						break;
				}
				if(minefield.getGameOver()) {
					btns[i][j].setDisable(true);
					if(field[i][j] == 9) {
						btns[i][j].setId("uncoverMines");
					}
					if(field[i][j] > 9 && field[i][j] < 109) {
						btns[i][j].setId("notmine");
					}
					if(i == minefield.getLastRow() && j == minefield.getLastCol()) {
						btns[i][j].setId("clickedMine");
					}	
				}
				else if(minefield.getWin()) {
					btns[i][j].setDisable(true);
					if(field[i][j] == 9) {
						btns[i][j].setId("flag");
						minefield.setFlag(i, j);

					}
					
				}

				


			}
		}
	}

	public void restart(GridPane grid) {
		start = false;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btns[i][j].setDisable(false);
			}
		}
		minefield = new Minefield(row, col, mines);
		updateGrid(btns);
		}
}


