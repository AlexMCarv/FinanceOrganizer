import com.opencsv.*;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * CSV Parser object. Given a csv file object, parses the data and stores it in a List of String arrays. 
 * @author Alexandre Carvalho
 */

public class FileImporter {
	/** File to be parsed */
	private File file;
	
	/** 
	 * Creates the object to hold the reference to the csv file to be parsed 
	 * @param file File to be parsed
	 */
	public FileImporter(File file){
		this.file = file;
	}
	
	/** 
	 * Accessor for file
	 * @return file reference 
	 * */
	public File getFile() {return file;}
		
	/**
	 * Parses the CSV file
	 * @return List of String arrays that holds each individual data
	 * @throws 
	 */ 
	public List<String[]> parseFile() throws Exception {
		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader(file));
	        List<String[]> parsedFile = reader.readAll();
	        reader.close();
	        return parsedFile;
	    
		} catch (IOException e) {
	    	System.out.println("opa entrou no catch");
	    	e.printStackTrace();
	    	return null;
	    }
    }
}
