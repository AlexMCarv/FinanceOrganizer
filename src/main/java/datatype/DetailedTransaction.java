package datatype;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DetailedTransaction extends SimpleTransaction{
	
	private ObjectProperty<LocalDate> date;
	private char type;

	public DetailedTransaction(LocalDate date, String category, String description, double value, char type) {
		super(category, description, value);
		this.date = new SimpleObjectProperty<>(date);
		this.type = type;
	}

	public LocalDate getDate() {
		return date.get();
	}
	public void setDate(LocalDate date) {
		this.date.set(date);
	}
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	
	
}
	
