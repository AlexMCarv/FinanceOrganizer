package datatype;
import java.io.Serializable;
import java.time.LocalDate;

// This class holds the data for each transaction
public class Transaction implements Comparable<Transaction>, Serializable{

	private LocalDate date;
	private String description;
	private double value;
	private char type;
	private String formatedDescription;
	private String[] formatedData;
	private String category;
	
	public Transaction (LocalDate date,  double value, char type, String description, String category) {
		setDate(date);
		setValue(value);
		setDescription(description);
		setType(type);
		setFormatedDescription(description);
		setCategory(category);
	}

	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getFormatedDescription() {
		return formatedDescription;
	}
	public void setFormatedDescription(String fDescription) {
		this.formatedDescription = fDescription;
	}
	
	public String toString() {
		return ("Desc: " + formatedDescription + 
				" Date: " + date + 
				" Value: " + value + 
				" Type: " + type +
				" Category: " + category);
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public boolean equals(Transaction trans) {
		return ((this.date == trans.date) && 
				(this.description == trans.description) && 
				(this.value == trans.value) && 
				(this.type == trans.type));
	}
	
	public int compareTo(Transaction other) {
		return this.getDate().compareTo(other.getDate());
	}
	
	public String[] getFormatedData() {
		formatedData = new String[4];
		formatedData[0] = date.toString();
		formatedData[1] = formatedDescription;
		formatedData[2] = type + "";
		formatedData[3] = Double.toString(value);
		return formatedData;
	}
}
