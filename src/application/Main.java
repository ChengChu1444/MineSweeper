package application;
	

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class Main extends Application {

	Controller controller = new Controller(9,9,10);
		
	@Override
	public void start(Stage primaryStage) {
		try {

			//minefield.play();
			BorderPane root = new BorderPane();
			GridPane grid = controller.initGrid();
			root.setCenter(grid);
			Scene scene = new Scene(root,grid.getMinHeight(),grid.getMinWidth());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
