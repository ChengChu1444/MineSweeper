package application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Controller {

	private Text timerText;

	private int row;
	private int col;
	private int mines;
	private boolean start;
	private Minefield minefield;
	private MineButton[][] btns;
	private int time;

	public Controller(int row, int col, int mines) {
		start = false;
		this.row = row;
		this.col = col;
		this.mines = mines;
		minefield = new Minefield(this.row, this.col, this.mines);
		time = 0;
		timerText = new Text(Integer.toString(time));

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
								timer.start();

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
					timer.stop();
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
		time = 0;
		timer.stop();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				btns[i][j].setDisable(false);
			}
		}
		minefield = new Minefield(row, col, mines);
		updateGrid(btns);
		}


	
	
	public Text getTimer() {
		timerText.setFont(new Font("Arial",24));
		timerText.setFill(Color.RED);

		return timerText;
		
	}
	

	AnimationTimer timer = new AnimationTimer() {
	    private long timestamp;
	    private long fraction = 0;

	    @Override
	    public void start() {
	        // current time adjusted by remaining time from last run
	        timestamp = System.currentTimeMillis() - fraction;
	        time = 0;
			timerText.setText(Integer.toString(time));
	        super.start();
	    }

	    @Override
	    public void stop() {
	        super.stop();
			timerText.setText(Integer.toString(time));
	        // save leftover time not handled with the last update
	        fraction = System.currentTimeMillis() - timestamp;
	    }

	    @Override
	    public void handle(long now) {
	        long newTime = System.currentTimeMillis();
	        if (timestamp + 1000 <= newTime) {
	            long deltaT = (newTime - timestamp) / 1000;
	            timestamp += 1000 * deltaT;
	            time++;
				timerText.setText(Integer.toString(time));
				}
	    }
	};
}