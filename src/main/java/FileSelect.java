import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class FileSelect 
{
	private static File file = null;
	private static final FileChooser fileChooser = new FileChooser();
	private static final ExtensionFilter filter = new ExtensionFilter(
			"CSV File", "csv");
	private static Stage stageComponent;
	
	public FileSelect () 
	{
		setStageComponent(null);
		
	}
	public FileSelect (Stage stage) 
	{
		setStageComponent(stage);
	}
	
	public Stage getStageComponent() 
	{
		return stageComponent;
	}
	
	public void setStageComponent(Stage stage) 
	{
		this.stageComponent = stage;
	}
	
	//This function returns the file reference, after testing if a file was selected. Returns null otherwise. 
	public static File getFile(Stage stage) throws FileNotFoundException
	{
		if (openDialog(stage))
		{
			return file;
		}
		
		else
		{
			System.out.println("File was not selected");
			throw new FileNotFoundException();
		}
	}
	
	/* This function displays the Open File dialog box and return the file reference 
	 * if selected by the user, or null if dismissed or error
	 */
	private static boolean openDialog(Stage stage)
	{
		fileChooser.getExtensionFilters().addAll(filter);
		//Opens the Open File dialog box, and return either true if file was selected or false if error or dismissed
		File selectedFile = fileChooser.showOpenDialog(stage);
				
		if (selectedFile != null) 
		{
			return true;
        } 
		
		else 
		{
            return false;
        }
	}
}
