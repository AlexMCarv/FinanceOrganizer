import com.opencsv.*;
import java.io.FileReader;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html
//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
public class FileImporter extends Application
{
	public FileImporter() {};
	
	public void PrintToScreen()
	{
		File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);
	}
	
	public void start(Stage stage) throws Exception
	{
		CSVReader reader = null;
		
		try 
	    {
			File csvFile = FileSelect.getFile(stage);
			reader = new CSVReader(new FileReader(csvFile));
	        List<String[]> importedFile = reader.readAll();
	    	for (String[] line : importedFile)
	    	{
	    		for (int i = 0; i < line.length; i++) 
	    		{
	    			System.out.println(line[i] + " ");
	    		}
	    		System.out.println("");
	    	}
	    } 
	    
	    catch (IOException e) 
	    {
	    	System.out.println("opa entrou no catch");
	    	e.printStackTrace();
	    }
    }
	
	public String FileChooser()
	{
		//This is a placeholder for the main window. Change null for the main window component.
		Stage stage = null;
		final FileChooser fileChooser = new FileChooser();
		File returnVal = fileChooser.showOpenDialog(stage);
		
		if (returnVal != null) 
		{
            return returnVal.getAbsolutePath();
        } 
		
		else 
		{
            return "";
        }
	}
}
