import com.opencsv.*;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

//https://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html
//https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html

// 
public class FileImporter
{
	private File file;
	
	public FileImporter(File file) {
		this.file = file;
	};
	
	public void PrintToScreen()
	{
		File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);
	}
	
	public void parseFile() throws Exception
	{
		CSVReader reader = null;
		
		try 
	    {
			reader = new CSVReader(new FileReader(file));
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
}
