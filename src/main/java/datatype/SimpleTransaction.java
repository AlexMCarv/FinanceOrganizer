package datatype;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SimpleTransaction {

	private SimpleStringProperty description;
	private SimpleDoubleProperty value;
	private SimpleStringProperty category;
	
	public SimpleTransaction (String category, String description, double value) {
		this.category = new SimpleStringProperty(category);
		this.value = new SimpleDoubleProperty(value);
		this.description = new SimpleStringProperty(description);
	}

	public String getCategory() {
		return category.get();
	}
	
	public void setCategory(String category) {
		this.category.set(category);
	}
	
	public double getValue() {
		return value.get();
	}
	public void setValue(double value) {
		this.value.set(value);
	}

	public String getDescription() {
		return description.get();
	}
	public void setDescription(String description) {
		this.description.set(description);
	}
	
}
