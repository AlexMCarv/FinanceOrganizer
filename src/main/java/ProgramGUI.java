import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProgramGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		Text hello = new Text(50, 50, "Hello, JavaFX");
		hello.setStroke(Color.AQUA);
		Text question = new Text(120, 80, "How is it going?");
		Line myLine = new Line(0, 0, 50, 50);
		myLine.setStroke(Color.BISQUE);
		
		Group root = new Group(hello, question, myLine);
		Scene scene = new Scene(root, 300,300, Color.RED);
		
		primaryStage.setTitle("Finance Organizer");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String [] args)	{
		launch(args);
	}
}
