import com.opencsv.*;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

// This class holds the file reference and is responsible for parsing the data of a given CSV file
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
	
	// This method parses the CSV file and returns the each individual data separating first 
	// the lines in a String array and each data in a String 
	public List<String[]> parseFile() throws Exception
	{
		CSVReader reader = null;
		
		try 
	    {
			reader = new CSVReader(new FileReader(file));
	        List<String[]> importedFile = reader.readAll();
	        reader.close();
	        return importedFile;
	    } 
	    
	    catch (IOException e) 
	    {
	    	System.out.println("opa entrou no catch");
	    	e.printStackTrace();
	    	return null;
	    }
    }
}
