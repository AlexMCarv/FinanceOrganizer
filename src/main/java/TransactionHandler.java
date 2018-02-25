import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class TransactionHandler {
	private List<String[]> rawData;
	private List<Transaction> data = new ArrayList<Transaction>();
	YearMonth date = YearMonth.now();
	List<String> uniqueDate = new ArrayList<String>();
	
	
	public List<Transaction> getTransaction() {return data;}
	
	public void printTransaction() {
		// Printing result for DEBUG
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
	}

	public void parseFile(File file) throws Exception {
		CSVReader reader;

		try {
			reader = new CSVReader(new FileReader(file));
	        rawData = reader.readAll();
	        reader.close();
	        createTransaction();
	        	    
		} catch (IOException e) {
	    	System.out.println("opa entrou no catch");
	    	e.printStackTrace();
		}
   }
	
	public void createTransaction () {
		// Creating transaction objects
		for (String[] line : rawData) 
		{
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
    		LocalDate date = LocalDate.parse(line[0], formatter);
    		double value = Double.parseDouble(line[1]);
    		String type = line[3];
    		String description = line[4];
    		Transaction transaction = new Transaction(date, value, type, description);
    		data.add(transaction);
    	}
	}
	
	public void saveTransaction() throws IOException {
		for (int i = 0; i < data.size(); i++) {
			int year = data.get(i).getDate().getYear();
 			int month = data.get(i).getDate().getMonthValue();
 			getUniqueDate(year, month);
 			String fileName = String.format("%d-%d.csv", year, month); 
 			//Check if data is already inserted in the file
 			//if not append
 			CSVWriter writer = new CSVWriter(new FileWriter(fileName , true));
			writer.writeNext(data.get(i).getFormatedData());
			writer.close();
		}
		
	}
	
	public void getUniqueDate(int year, int month) {
		String date = String.format("%d-%d.csv", year, month);
		if (!uniqueDate.contains(date)){
			uniqueDate.add(date);
		}
	}
}
