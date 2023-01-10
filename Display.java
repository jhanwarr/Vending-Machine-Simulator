import java.util.*;

public class Display
{
    // possible private data fields	
    private boolean notExactChange;

    /**
     * Constructor to initialize the instance variable to switch on 
     * or off the light depending on the availability of change
     */
    public Display()
    {
        notExactChange = true;
    }
    
    // TODO:
    // displays the message passed in from calling method
    // currently only prints to the screen, but it could be used with a GUI 
    // or with a PrintWriter or medium

    /**
     * Method to print to the message provided to the function
     */
    public void show(String message)
    {
        System.out.println(message);
    }
	
    // TODO: message that informs user of an error
    /**
     * Method to print the error which might have been encountered due to 
     * user error
     * exceptions, etc;
     */
    public void error(String message)
    {
        System.out.println("Error: " + message);
    }

    /**
     * Method to display the price of the item in the Dollar format
     */
    public void printPrice(double price, double deposited_amt)
    {
        System.out.printf("Price $%.2f Deposited $%.2f\n", price, deposited_amt);
    }

    // TODO:  inform the user of change returned
    /**
     * Method to print the change the user has to take
     */
    public void takeChange(int q, int d, int n)
    {
        System.out.println("Please take your change:");
        System.out.println(q + " quarters " + d + " dimes " + n + " nickels.");
    }
	
    // TODO: create messages for events that happen in multiple parts of the program
    // so that you avoid retyping.  
    // These are examples - create what you need
    /**
     * Method to check whether a product has been sold out or not 
     */
    public void soldOut()
    {
        System.out.println("Sorry, that brand is sold out.");
    }

    /**
     * Method to print the lines of the menu of the Vending Machine
     */
    public void menu(String message, double price)
    {
        System.out.print(message);
        System.out.printf("$%.2f\n", price);
    }

    /**
     * Method to print the message when all the items are sold out
     */
    public void allSoldOut()
    {
        System.out.println("Sorry, everything is sold out.");
    }
	
    /**
     * Method to print the message when an item is sold successfully
     */
    public void sold()
    {
        System.out.println("Sale successful. Please take your beverage.");
    }

    /**
     * Method to turn the machine light on when exact change is not available and 
     * inform the user the same.
     */
    public void notExactChange()
    {
        notExactChange = true;
        System.out.println("Sorry, cannot make exact change!");
    }
}
