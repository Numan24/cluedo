package cluedo;

import java.util.Scanner;

public class Input {

	private static Scanner input = new Scanner(System.in);

	public static String getString(String string) {
		System.out.println(string);
		return input.next();
	}
	
	public static String getLine(String string) {
		System.out.println(string);
		String line = input.nextLine();
		while(line.equals("")) {
			line = input.nextLine();
		}
		return line;
	}

	public static int getInt(String string) {
		System.out.println(string);
		while(!input.hasNextInt()) {
			input.next();
		}
		return input.nextInt();
	}

}
