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
		
		System.out.print("\nPLEASE SPECIFY THE GENERAL TYPE OF OBJECT YOU WOULD LIKE TO CREATE. \n"
					+ "  Enter 1 for a simple object with only primitives for instance variables. \n"
					+ "  Enter 2 for an object that contains references to other objects. \n"
					+ "  Enter 3 for an object that contains an array of primitives. \n"
					+ "  Enter 4 for an object that contains an array of object references. \n"
					+ "  Enter 5 for an object that uses an instance of one of Java’s collection classes to refer to several other objects. \n"
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

	public boolean askCreateAnother()
	{
		int input;
		System.out.print("Do you want to create another object? \n"
					+ "  Enter 1 if you do \n"
					+ "  Enter 0 if you are finished \n"
					+ "Your selection: ");
		
		input = scanner.nextInt();
		if (input == 1)
			return true;
		else 
			return false;
	}
	
	public int askFieldValueInt(String fieldName) 
	{	
		int input = 0;
		System.out.print("Please set the integer value for field, "+fieldName+". Enter any integer: ");
		
		input = scanner.nextInt();
		return input;
	}
	
	public boolean askFieldValueBool(String fieldName)
	{
		boolean input;
		System.out.print("Please set the boolean value for field, "+fieldName+". Enter either 'true' or 'false': ");
		
		input = scanner.nextBoolean();
		return input;
	}
	
	public int askFieldValueRef(String fieldName)
	{
		int input;
		System.out.print("Please set the object value for the field "+fieldName+".\n"
					+ "  Enter the ID number of another object if you know it, \n"
					+ "  Enter -1 if you would like to create a new object for this field, \n"
					+ "  Enter -2 if you would like to initialize this field value to null. \n"
					+ "Your selection: ");
		
		input = scanner.nextInt();
		return input;
	}
	public int askFieldValueRef()
	{
		int input;
		System.out.print("  Enter the ID number of another object if you know it, \n"
					+ "  Enter -1 if you would like to create a new object for this field, \n"
					+ "  Enter -2 if you would like to initialize this field value to null. \n"
					+ "Your selection: ");
		
		input = scanner.nextInt();
		return input;
	}
	
	public int[] askFieldValueArrayInts(String fieldName, int length)
	{
		int[] array = new int[length];
		int input;

		System.out.print("Please set " +length+" integer elements for the array value of the field, "+fieldName+".\n");
		for (int i=0; i<length; i++)
		{			
			System.out.print("  Enter an int value for element "+i+": ");			
			input = scanner.nextInt();
			array[i] = input;
		}
		
		return array;
	}

}
