package spk;
import java.sql.*;
import java.util.*;

public class Main {
	static Scanner sc;
														
	public static void main(String[] args) throws SQLException {
		sc = new Scanner(System.in);
		System.out.println(" SPK BANK OF INDIA *** Welcome's You ");
	
		loop1: while (true) {
			System.out.println(" Choose Anyone One Below To Proceed... \n 1. Activate User Account 2. Existing User ");
			int answer = sc.nextInt();
			sc.nextLine();
			
			if (answer==1) {
				System.out.println("============================================================================================");
				UserInput.userInput();
				int serialNo=DBConnection.dbRetrieveSerialNo();
				String userId=DBConnection.dbRetrieveId(serialNo);
				DBConnection.dbInsertUserId(userId,serialNo);
				System.out.println("Account Created Successfully...");
				System.out.println("User ATM Account User Id Is : "+ userId);
				System.out.println("============================================================================================");
			} else if (answer == 2) {
				System.out.println("Enter your User Id Below  ");
				String userId = sc.next();
				System.out.println("Enter your password");
				int userPassword = sc.nextInt();
				sc.nextLine();
				UserInput.retrieveDetails(userId, userPassword);
			} 
			else {
				System.out.println("Thank You");
				break loop1;
			}
		}
		sc.close();
	}
}

