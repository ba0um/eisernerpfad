package gui;
	
import info.TestCharPrint;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			
			// --------------------------------------	
			// TODO Testing with the label
			TestCharPrint testChar = new TestCharPrint();				
			Label charInfo = new Label();
			charInfo.setText(testChar.printChar());
			root.add(charInfo, 0, 0);
			// --------------------------------------
			
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
