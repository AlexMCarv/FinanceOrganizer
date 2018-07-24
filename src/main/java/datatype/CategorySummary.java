package datatype;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class CategorySummary {
	
	private Category category;
	private double[] months;
	private SimpleStringProperty categoryName;
	private SimpleStringProperty categoryCode;
	private SimpleDoubleProperty jan;
	private SimpleDoubleProperty feb;
	private SimpleDoubleProperty mar;
	private SimpleDoubleProperty apr;
	private SimpleDoubleProperty may;
	private SimpleDoubleProperty jun;
	private SimpleDoubleProperty jul;
	private SimpleDoubleProperty aug;
	private SimpleDoubleProperty sep;
	private SimpleDoubleProperty oct;
	private SimpleDoubleProperty nov;
	private SimpleDoubleProperty dec;
	private SimpleDoubleProperty total;
	private int year;
	
	
	public CategorySummary(Category category, int year, double[] months){
		this.category = category;
		this.year = year;
		this.months = months;
			
		categoryCode = new SimpleStringProperty(category.getCategoryCode());
		categoryName = new SimpleStringProperty(category.getCategoryName());

		jan = new SimpleDoubleProperty(getvalueAtMonth(1));
		feb = new SimpleDoubleProperty(getvalueAtMonth(2));
		mar = new SimpleDoubleProperty(getvalueAtMonth(3));
		apr = new SimpleDoubleProperty(getvalueAtMonth(4));
		may = new SimpleDoubleProperty(getvalueAtMonth(5));
		jun = new SimpleDoubleProperty(getvalueAtMonth(6));
		jul = new SimpleDoubleProperty(getvalueAtMonth(7));
		aug = new SimpleDoubleProperty(getvalueAtMonth(8));
		sep = new SimpleDoubleProperty(getvalueAtMonth(9));
		oct = new SimpleDoubleProperty(getvalueAtMonth(10));
		nov = new SimpleDoubleProperty(getvalueAtMonth(11));
		dec = new SimpleDoubleProperty(getvalueAtMonth(12));
		total = new SimpleDoubleProperty(getSum());
	}

	
	public Category getCategory() {
		return category;
	}
	
	public double getvalueAtMonth(int month) {
		return months[month - 1];
	}
	
	public int getYear() {
		return year;
	}
		
	
	public double getSum() {
		double sum = 0;
		
		for(int i = 0; i < months.length; i++) {
			sum += months[i];
		}
		
		DecimalFormat df = new DecimalFormat("#.##");      
		sum = Double.valueOf(df.format(sum));
		return sum;
	}

	public String getCategoryCode() {return categoryCode.get();}
	public String getCategoryName() {return categoryName.get();}
	public double getJan() {return jan.get();}
	public double getFeb() {return feb.get();}
	public double getMar() {return mar.get();}
	public double getApr() {return apr.get();}
	public double getMay() {return may.get();}
	public double getJun() {return jun.get();}
	public double getJul() {return jul.get();}
	public double getAug() {return aug.get();}
	public double getSep() {return sep.get();}
	public double getOct() {return oct.get();}
	public double getNov() {return nov.get();}
	public double getDec() {return dec.get();}
	public double getTotal() {return total.get();}
		
}
