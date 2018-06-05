package application;
	



import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {

	Controller controller = new Controller(16,31,99);
		
	@Override
	public void start(Stage primaryStage) {
		try {

			//minefield.play();
			BorderPane root = new BorderPane();
			GridPane grid = controller.initGrid();
			root.setCenter(grid);
			Scene scene = new Scene(root,grid.getMinHeight(),grid.getMinWidth());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			BorderPane top = new BorderPane();
			top.setPadding(new Insets(10,10,10,10));
			root.setTop(top);
			
			Text timert = new Text();
			timert.setFont(new Font("Arial",24));
			timert.setFill(Color.RED);
			StackPane left = new StackPane();
			left.setPrefWidth(50);
			left.getChildren().add(timert);
			top.setLeft(left);
			AnimationTimer timer = new AnimationTimer() {
			    private long timestamp;
			    private long time = 0;
			    private long fraction = 0;

			    @Override
			    public void start() {
			        // current time adjusted by remaining time from last run
			        timestamp = System.currentTimeMillis() - fraction;
			        super.start();
			    }

			    @Override
			    public void stop() {
			        super.stop();
			        // save leftover time not handled with the last update
			        fraction = System.currentTimeMillis() - timestamp;
			    }

			    @Override
			    public void handle(long now) {
			        long newTime = System.currentTimeMillis();
			        if (timestamp + 1000 <= newTime) {
			            long deltaT = (newTime - timestamp) / 1000;
			            time += deltaT;
			            timestamp += 1000 * deltaT;
			            timert.setText(Long.toString(time));
			        }
			    }
			};
			timer.start();

			


			Button restart = new Button();
			restart.setPrefHeight(32);
			restart.setPrefWidth(32);
			top.setCenter(restart);
			
			
			Text mines = new Text();
			top.setRight(mines);
			
			restart.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e){
					controller.restart(grid);
				}
			});
			
			

			
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
