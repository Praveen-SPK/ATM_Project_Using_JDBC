package spk;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class UserInput {
	static Scanner sc=new Scanner(System.in);
	
	
public static void userInput() throws SQLException {
	System.out.println("Enter Your Name : ");
	String name=sc.nextLine();
	System.out.println("============================================================================================");
	
	System.out.println("Set Your Password :");
	int password=sc.nextInt();
	sc.nextLine();
	while(true) {
		if(password>9999 || password<1000) {
			
			password = sc.nextInt();
			sc.nextLine();
			
			
		}else {
			break;
		}	
	}
	System.out.println("============================================================================================");
	
	System.out.println("Enter Your Card Number :");
	long cardNumber=sc.nextLong();
	sc.nextLine();
	while(true) {
		if(cardNumber<100_000_000 ||cardNumber>999_999_999) {
			System.err.println("Invalid Card Number :" + cardNumber);
			cardNumber = sc.nextLong();
			sc.nextLine();
		}else {
			break;
		}
	}
	System.out.println("============================================================================================");
	
	System.out.println("Initial Amount Needed !!! Add Funds Into Your AccountS :");
	int balanceAvailable;
	int balance=sc.nextInt();
	sc.nextLine();
	while(true) {
	if(balance<1000) {
		System.err.println("Minimum Balance Needed is 1000");
		balance=sc.nextInt();
		sc.nextLine();
	}else {
		break;
	}}
	balanceAvailable=balance;
	System.out.println("============================================================================================");
	System.out.print("Funds Added...");
	DBConnection.dbInsert(name,password,cardNumber,balanceAvailable);	
}

public static void switchCase(int balance ,int withdrawAmount ,int addAmount ,int userId) throws SQLException {
	
	loop:while(true) {
		System.out.println(" To Check Balance  -->> Enter : 1 \n To Add Money      -->> Enter : 2 \n To WithDraw Money -->> Enter : 3 \n To Get Receipt    -->> Enter : 4 \n To Exit           -->> Enter : 5");
		int userInput=sc.nextInt();
		System.out.println("============================================================================================");
		
	switch(userInput) {
	case 1:
		System.out.println("Your Current Balance Is : "+ balance);
		if(balance<1000) {
			System.out.println("Bank Balance is Low ... Add Fund ASAP");
		}
		break;
	case 2:
		System.out.println("Enter Amount to Add In Your Account : ");
		addAmount=sc.nextInt();
		balance += addAmount;
		DBConnection.dbUpdateBalance(balance, userId);
		System.out.println("Funds Added...");
		break;
	case 3:
		System.out.println("Enter Amount To Withdraw : ");
		
		withdrawAmount=sc.nextInt();
		sc.nextLine();
		System.out.println("============================================================================================");
		System.out.println("Processing Your Request. Please Wait !!!");
		
		if(withdrawAmount>balance) {
			System.err.println("Oops !!! Your Balance Is Lower Than Your Request...");
		}
		else {
		System.out.println("Please Collect Your Amount " + withdrawAmount);
		balance -=withdrawAmount;
		DBConnection.dbUpdateBalance(balance, userId);
		}
		break;
	case 4:
		System.out.println("======== Online ATM Receipt Is Here ========");
		System.out.println("Balance Available :"+ balance);
		System.out.println("Your Last Withdrawal Amount :"+withdrawAmount);
		System.out.println("Your Last Added Amount :"+addAmount);
		break;
	case 5:
		System.out.println(".. Thank You ..");
		System.out.println("============================================================================================");
		break loop ;
	
	default:
		System.err.println("Enter Valid Number To Proceed .Tranaction Denied...");
		System.out.println("============================================================================================");
		break loop;
		
	}
	System.out.println("============================================================================================");
}
}

public static void retrieveDetails(String user_Id,int password) throws SQLException {
	int balance=0;
	int userId;
	ResultSet res=DBConnection.dbRetrieve();
	while(res.next()) {
		
		if(res.getString(6).equalsIgnoreCase(user_Id) && res.getInt(2)==password){
			System.out.println("Please Wait!!! Processing Your Request....");
			System.out.println("============================================================================================");
			balance = res.getInt(4);
			userId=res.getInt(5);
			int withdrawAmount = 0;
			int addAmount =0;
			UserInput.switchCase(balance, withdrawAmount, addAmount,userId);
			break;
		}
		 
		else if (res.getString(6).equalsIgnoreCase(user_Id) && res.getInt(2)!=password) {
			System.err.println("Wrong Password");
			System.out.println("============================================================================================");
			break;
		}
		else if(!res.getString(6).equalsIgnoreCase(user_Id) && res.getInt(2)==password) {
			System.err.println("Invalid User Id");
			System.out.println("============================================================================================");
			break;
		} 
		
	}
	 
		
}
}
