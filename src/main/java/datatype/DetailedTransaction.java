package datatype;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DetailedTransaction extends SimpleTransaction{
	
	private ObjectProperty<Date> date;
	private char type;

	public DetailedTransaction(Date date, String category, String description, double value, char type) {
		super(category, description, value);
		this.date = new SimpleObjectProperty<>(date);
		this.type = type;
	}

	public Date getDate() {
		return date.get();
	}
	public void setDate(Date date) {
		this.date.set(date);
	}
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	
	
}
	
