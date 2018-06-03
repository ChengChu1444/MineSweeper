package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class Controller {
	
	private int row;
	private int col;
	private int mines;
	Minefield minefield;

	public Controller(int row, int col, int mines)
	{
		this.row = row;
		this.col = col;
		this.mines = mines;
		minefield = new Minefield(row,col,mines);
		minefield.placeMines();
		
		
	}
	
	public GridPane initGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		MineButton[][] btns = new MineButton[row][col];
		int field[][] = minefield.getField();
		for(int i = 0; i < row;i++) {
			for(int j = 0; j < col;j++) {
				btns[i][j] = new MineButton(i,j);
				btns[i][j].setPrefWidth(30);
				btns[i][j].setPrefHeight(30);
				grid.add(btns[i][j], j, i);
				MineButton btn = btns[i][j];
	            btn.setOnAction(new EventHandler<ActionEvent>() {
	                @Override public void handle(ActionEvent e) {
	                	minefield.testIfMineHit(btn.getRow(), btn.getCol());
	                	updateGrid(btns);
	                }
	            });
			}
		}
    	updateGrid(btns);
		
		return grid;
	}
	
	public void updateGrid(MineButton[][] btns) {
		int field[][] = minefield.getField();
		for(int i = 0; i < row;i++) {
			for(int j = 0; j < col;j++) {
				if(field[i][j] != 9 && field[i][j] != 0 && field[i][j] != -1) {
					btns[i][j].setText(Integer.toString(field[i][j]));
				}
				if(field[i][j] == -1) {
					btns[i][j].setId("uncovered");
				}
				
			}
		}
	}
}
