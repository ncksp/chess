package utilities;

import java.util.Scanner;

public class Utilities {
	public Scanner scan = new Scanner(System.in); 
	
	public void cls() {
		for(int i = 0 ; i < 30; i++)
			System.out.println();
	}
	
	public int menu(int choose) {
		choose = 0;
		cls();
		System.out.println("1. Algebraic");
		System.out.println("2. Coordinate");
		System.out.println("3. Exit");
		System.out.print("Choose move notation [1-2]: ");
		try {
			choose = scan.nextInt();
		} catch (Exception e) {
			System.out.println("Number Only!");
		}
		scan.nextLine();
		return choose;
	}
}
