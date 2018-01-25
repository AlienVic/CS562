package stevens.cs562.EMFQuery.start;


import java.util.Scanner;

import stevens.cs562.EMFQuery.Business.EMFVariables;
import stevens.cs562.EMFQuery.Util.ConnectDB;
import stevens.cs562.EMFQuery.Util.FileInput;
//import stevens.cs562.EMFQuery.Util.InterfaceInput;


public class start {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String command;
		EMFVariables variables = null;
		ConnectDB connectdb = null;
		try {
			connectdb = new ConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FileInput f = new FileInput();
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("**********************************************");
			System.out.println("*** 1> Read variables from file              *");
			System.out.println("*** 2> Read variables as input               *");
			System.out.println("*** q> exit;                                 *");
			System.out.println("**********************************************");
			
			
			command = scan.nextLine();
			
			if (command.equals("1")) {
				variables = f.load();
				
			}else if (command.equals("2")) {
				//InterfaceInput interf = new InterfaceInput();
				
			}else if (command.equals("q")) {
				
				System.out.println("System exit!");
				return;
				
			}else {
				System.err.println("Input is invalid!!!!!");
			}
		}while(!command.equals("1")&&!command.equals("2")&&!command.equals("q"));
		
		long startTime=System.currentTimeMillis();

		String fileName = f.writeFile(connectdb,variables);
		System.out.println(fileName+" create success!!!!!!");
		
		long endTime=System.currentTimeMillis();
		long Time=endTime-startTime;
		
		System.out.println("the program run "+Time+"ms");
		System.out.print("Would you like to run this program again?(Y/N)");
		// if the user want to create another file, he can back the the start point
		command = scan.nextLine();
		
		scan.close();
	}

}
