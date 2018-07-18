package core;
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

import datatype.Transaction;

public class TransactionHandler {
	protected List<String[]> rawData;
	protected List<Transaction> data = new ArrayList<Transaction>();
	private YearMonth date = YearMonth.now();
	private List<String> uniqueDate = new ArrayList<String>();
	
	
	public List<Transaction> getTransaction() {
		return data;
	}
	
	public List<String[]> getRawData() {
		return rawData;
	}
	
	
	public void printTransaction() {
		// Printing result for DEBUG
		for (int i = 0; i < data.size(); i++)
			System.out.println(data.get(i));
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
    		char type = line[3].charAt(0);
    		String description = line[4];
    		String category = "";
    		Transaction transaction = new Transaction(date, value, type, description, category);
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
