
import java.time.LocalDate;

// This class holds the data for each transaction
public class Transaction 
{
	private LocalDate date = null;
	private String description = "";
	private double value = 0.0d;
	private String type = "";
	
	public Transaction (LocalDate date,  double value, String type, String description) 
	{
		setDate(date);
		setValue(value);
		setDescription(description);
		setType(type);
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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String toString() {
		return ("Desc:" + description + " Date:" + date + " Value: " + value + " Type:" + type);
	}
}
