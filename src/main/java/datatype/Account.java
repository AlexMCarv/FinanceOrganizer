package datatype;

public class Account {

	private int id;
	private String owner;
	private String bank;
		
	public Account(int id, String owner, String bank) {
		this.id = id;
		this.owner = owner;
		this.bank = bank;
	}
	
	public int getid() {
		return id;
	}

	public String getOwner() {
		return owner;
	}
	
	public String getBank() {
		return bank;
	}
	
	
}
