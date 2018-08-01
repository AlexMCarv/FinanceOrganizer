package parsers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.opencsv.CSVReader;


public class ParseScotiaBank extends BankFileParser{

	@Override
	public void parseFile(File fileToParse) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileToParse));
	    rawData = reader.readAll();
	    invalidTransactions = new ArrayList<>();
	    int size = rawData.size();
	    
	    // initializing properties 
	    date = new LocalDate[size];
		description = new String[size];
		value = new double[size];
		type = new char[size];
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
		
		for(int i = 0; i < size; i++){
			try {
				String[] transaction = rawData.get(i);
				date[i] = LocalDate.parse(transaction[0], formatter);
				
				if (Double.parseDouble(transaction[1]) > 0) {
					type[i] = 'D';
				} else {
					type[i] = 'W';
				}
				
				value[i] = Math.abs(Double.parseDouble(transaction[1]));
				
				if (transaction[4] == "" || transaction[4] == null) {
					description[i] = transaction[3];
				} else {
					description[i] = transaction[4];
				}
			
			} catch (Exception e) {
				invalidTransactions.add(i);
			}
		
		} // End of loop through all lines of the file
		
		reader.close();
	
	} // End of parseFile method
	
} // End of ParseScotiaBank class