package spk;

public class UserDB {
	private String name;
	private int password;
	private int cardNumber;
	private int balanceAvailable;
	
	UserDB(String name,int password,int cardNumber,int balanceAvailable){
		this.name=name;
		this.password=password;
		this.cardNumber=cardNumber;
		this.balanceAvailable=balanceAvailable;
	}
	
	
	public int getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public int getBalanceAvailable() {
		return balanceAvailable;
	}

	
	 public void displayUser() {
		System.out.println(" Name : "+this.name +"&"+" Available Balance :"+this.balanceAvailable);
	}

}
