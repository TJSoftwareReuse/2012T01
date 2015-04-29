package com.team01.pm;

import java.io.Console;
import java.util.Scanner;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ErrorUtil eu = new ErrorUtil();
		Scanner sc = new Scanner(System.in);
		while (true) {
			Integer error_id = new Integer(sc.nextInt());
			if(error_id == -1)
				break;
			eu.report_an_error(error_id);
		}
	}

}
