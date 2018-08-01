package parsers;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class ParsePresidentChoice extends BankFileParser{

	@Override
	public void parseFile(File fileToParse) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(fileToParse));
	    rawData = reader.readAll();
	    rawData.remove(0); // Removes the Header line
	    invalidTransactions = new ArrayList<>();
	    int size = rawData.size();
	    
	    // initializing properties 
	    date = new LocalDate[size];
		description = new String[size];
		value = new double[size];
		type = new char[size];
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		for(int i = 0; i < size; i++){
			try {
				String[] transaction = rawData.get(i);
				date[i] = LocalDate.parse(transaction[0], formatter);
				String formattedValue = transaction[2].replace("$", "");
				formattedValue = formattedValue.replace("(", "");
				formattedValue = formattedValue.replace(")", "");
				value[i] = Math.abs(Double.parseDouble(formattedValue));
				
				description[i] = transaction[3];
				
				if (transaction[8] == "C") {
					type[i] = 'D';
				} else {
					type[i] = 'W';
				}
			
			} catch (Exception e) {
				invalidTransactions.add(i);
			}
		
		} // End of loop through all lines of the file
		
		reader.close();
	
	} // End of parseFile method

} // End of ParsePresidentChoice class
