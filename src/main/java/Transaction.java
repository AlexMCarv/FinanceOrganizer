
import java.util.Date;

public class Transaction 
{
	private Date date = null;
	private String description = "";
	private double value = 0.0d;
	
	public Transaction (Date transactionDate, String transactionDescription, double transactionValue) 
	{
		setDate(transactionDate);
		setDescription(transactionDescription);
		setValue(transactionValue);
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

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
