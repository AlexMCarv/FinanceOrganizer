package parsers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract public class BankFileParser {

	protected LocalDate[] date;
	protected String[] description;
	protected double[] value;
	protected char[] type;
	protected List<Integer> invalidTransactions;
	protected List<String[]> rawData;
	
	public LocalDate[] getDate(){
		return date;
	}
	
	public String[] getDescription(){
		return description;
	}
	
	public double[] getValue(){
		return value;
	}
	
	public char[] getType(){
		return type;
	}
	
	/**
	 * This method takes in a CSV file and parses the data to compose a transaction.
	 * For each String line in the CSV file the following data must be extracted:
	 * Date, Description, Value and Type ('W' for withdraw, and 'D' for deposit ).
	 * If the parsing of any of the data throws an exception, it populates the
	 * invalidTransactions list with the index of the invalid entry.
	 * @param fileToParse CSV file selected to be parsed
	 * @throws IOException when opening a CSV file fails
	 */
	public abstract void parseFile(File fileToParse) throws IOException;
	
	public boolean haveInvalidTransactions() {
		if (invalidTransactions.size() > 0) {
			return true;
		}
		return false;
	}
	
	public List<String[]> listInvalidTransactions(){
		
		 List<String[]> list = new ArrayList<>();
		 for (int invalidIdx : invalidTransactions) {
			 list.add(rawData.get(invalidIdx));
		 }
		
		 return list;
	}
	
	public boolean updateInvalidTransaction(Integer index, LocalDate newDate, 
			String newDescription, double newValue, char newType) {
		
		if (invalidTransactions.contains(index)) {
			date[index] = newDate;
			description[index] = newDescription;
			value[index] = newValue;
			type[index] = newType;
			invalidTransactions.remove(index);
			return true;
		}
		
		return false;
	}
	
	public List<String> printTransactions() {
		List<String> list = new ArrayList<>(); 
		System.out.println("List of valid transactions: ");
		for (int i = 0; i < description.length; i++) {
			if (!invalidTransactions.contains(i)) {
				String transaction = "Transaction: " + i +
									 ", Date: " + date[i] +
									 ", Description: " + description[i] +
									 ", Type: " + type[i] +
									 ", Value: " + value[i];
				list.add(transaction);
				System.out.println(transaction);
			} 
		}
		
		return list;
	}
	
	public List<String> printInvalidTransactions() {
		List<String> list = new ArrayList<>();
		System.out.println("List of invalid transactions: ");
		for (int i = 0; i < description.length; i++) {
			if (invalidTransactions.contains(i)) {
				String transaction = "Transaction: " + i +
									 " - " + Arrays.toString(rawData.get(i));
				System.out.println(transaction);
				list.add(transaction);
			} 
		}
		
		return list;
	}
}
