package datatype;

public class Category {

	private String categoryCode;
	private String categoryName;
	
	public Category(String categoryCode, String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}
	
}
