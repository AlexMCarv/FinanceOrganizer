
import java.time.LocalDate;

// This class holds the data for each transaction
public class Transaction {
	private LocalDate date;
	private String description;
	private double value;
	private String type;
	private String formatedDescription;
	private String[] formatedData;
	
	public Transaction (LocalDate date,  double value, String type, String description) {
		setDate(date);
		setValue(value);
		setDescription(description);
		setType(type);
		setFormatedDescription(description);
	}

	public double getValue() {return value;}
	public void setValue(double value) {this.value = value;}

	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	public LocalDate getDate() {return date;}
	public void setDate(LocalDate date) {this.date = date;}
	
	public String getFormatedDescription() {return formatedDescription;}
	public void setFormatedDescription(String fDescription) {this.formatedDescription = fDescription;}
	
	public String toString() {
		return ("Desc: " + formatedDescription + " Date: " + date + " Value: " + value + " Type: " + type);
	}
	
	public boolean equals(Transaction trans) {
		return ((this.date == trans.date) && (this.description == trans.description) && 
				(this.value == trans.value) && (this.type == trans.type));
	}
	
	public String[] getFormatedData() {
		formatedData = new String[4];
		formatedData[0] = date.toString();
		formatedData[1] = formatedDescription;
		formatedData[2] = type;
		formatedData[3] = Double.toString(value);
		return formatedData;
	}
}
