import java.util.Scanner;

public class UserContact {

	private Scanner scanner;
	
	public UserContact() {
		scanner = new Scanner(System.in);
	}
	
	public int askObjectType()
	{
		boolean goodInput = false;
		int input = 0;
		
		System.out.print("PLEASE SPECIFY THE GENERAL TYPE OF OBJECT YOU WOULD LIKE TO CREATE. \n"
					+ "Enter 1 for a simple object with only primitives for instance variables. \n"
					+ "Enter 2 for an object that contains references to other objects. \n"
					+ "Enter 3 for an object that contains an array of primitives. \n"
					+ "Enter 4 for an object that contains an array of object references. \n"
					+ "Enter 5 for an object that uses an instance of one of Java’s collection classes to refer to several other objects. \n"
					+ "Your selection: ");

		while (!goodInput)
		{	
			input = scanner.nextInt();
			if (input >= 1 && input <= 5)
				goodInput = true;
			else 
				System.out.print("\nYour selection was not valid! Please enter an integer according to the menu above. \nYour selection: ");
		}
		return input;
	}	

	public int askFieldValueInt(String fieldName) 
	{	
		int input = 0;
		System.out.print("Please enter any integer value for the field "+fieldName+": ");
		
		input = scanner.nextInt();
		return input;
	}
	
	public boolean askFieldValueBool(String fieldName)
	{
		boolean input = true;
		System.out.print("Please enter either true or false for the boolean field "+fieldName+": ");
		
		input = scanner.nextBoolean();
		return input;
	}
	
}
