package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application {
	int row = 10;
	int col = 10;
	int mines = 8;		
	Minefield minefield = new Minefield(row,col,mines);

	@Override
	public void start(Stage primaryStage) {
		try {
			minefield.placeMines();
			minefield.output();
			minefield.play();
			BorderPane root = new BorderPane();
			GridPane grid = new GridPane();
			Button[][] btns = new Button[row][col];
			for(int i = 0; i < row;i++) {
				for(int j = 0; j < col;j++) {
					btns[i][j] = new Button();
					grid.add(btns[i][j], j, i);
				}
			}
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
