import com.opencsv.*;
import java.io.FileReader;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;

public class FileImporter 
{
	public FileImporter() {};
	
	public static void PrintToScreen()
	{
		File testFile = new File("");
	    String currentPath = testFile.getAbsolutePath();
	    System.out.println("current path is: " + currentPath);
	}
	
	public static void Run() 
	{
		CSVReader reader = null;
		
		try 
	    {
			File csvFile = FileSelect.getFile();
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
	
	public static String FileChooser()
	{
		//This is a placeholder for the main window. Change null for the main window component.
		Component parentComponent = null;
		final JFileChooser fileChooser = new JFileChooser();
		int returnVal = fileChooser.showOpenDialog(parentComponent);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        } 
		
		else 
		{
            return "";
        }
	}
}
