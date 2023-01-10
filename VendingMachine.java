import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.lang.NumberFormatException;

/**
 * Class to create a Vending Machine, which stores all the items, dollar bills, 
 * number of coins, and handles the transactions with the user.
 */
public class VendingMachine
{
    // this could also be an array and assume i < 10
    // this array or ArrayList stores the information for all of the
    // brands of soda for sale in the vending machine.

    
    private ArrayList<BrandInventory> inventory;

    private CoinBox change;
    private CoinBox depositCoins;
    private BillBox dollars;

    Display disp;

    
    // TODO: create other private data fields you need

    // TODO: create the constructor.
    // It will need to read the config file "config.dat" and create the inventory
    // In eclipse, the file needs to be at the same level as the bin and src folders
    // so you do not need the full path

    // Make sure you handle possible errors and close the file before
    // you end the constructor!

    /**
     * Constructor to read the file containing information about the items
     * and also to initialize the objects to be used.
     */
    public VendingMachine()
    {
        inventory = new ArrayList<BrandInventory>();
        disp = new Display();
        
        try
            {
                File infile = new File("config.dat");
                FileReader istream = new FileReader(infile);
                BufferedReader br = new BufferedReader(istream);

                String s;
                String w = "";
                int counter = 0;
                        
                // Read the file and create the ArrayList with
                // the information about the sodas in stock
                while((s=br.readLine()) != null)
                    {
                        String id = "";
                        float price = 0;
                        int numItems = 0;

                        counter = 0;
                        s += " ";
                        w="";
                
                        for(int i=0; i<s.length(); i++)
                            {
                                char ch =  s.charAt(i);

                                if(ch == ' ')
                                    {
                                        if(counter == 0)
                                            id = w;

                                        else if(counter == 1)
                                            price = Float.valueOf(w);

                                        else
                                            numItems = Integer.parseInt(w);

                                        counter++;
                                        w="";
                                    }

                                else
                                    w+=ch;
                            }
                
                        BrandInventory obj = new BrandInventory(id, price, numItems);
                        inventory.add(obj);        
                    }
                
                br.close();
            }

        catch(FileNotFoundException e)
            {
                disp.error("The file is not found.");
            }

        catch(IOException e)
            {
                disp.error("IO Exception encountered.");
            }

        
        // load up initial change in the change box so that you can make change
        // for early transactions ... 10 coins each as a starting point
        
	change = new CoinBox();
        depositCoins = new CoinBox();

        change.setQuarters(10);
        change.setDimes(10);
        change.setNickles(10);


        dollars = new BillBox();
        
    } // end constructor
    
    /* TODO: check that the selected ID is valid and is not sold out
     * May be split into two methods		 
     */

    /**
     * Method to validate the ID entered by the user.
     */
    public boolean isValidID(String purchaseID)
    {
        for(BrandInventory s : inventory)
            {
                if(s.getID().equals(purchaseID))
                    {
                        return true;
                    }
            }

        return false;
    }

    /**
     * Method to check for the stock availibilty of the given ID in the 
     * Vending Machine.
     */
    public boolean isAvailable(String purchaseID)
    {
	// TODO: you will need several methods to handle the purchase
	// I have a few below that you might consider.   Remember that this
	// class must handle the workings of the Vending Machine and not share
	// private information with the user interface of VendingMachineSim

        for(BrandInventory s : inventory)
            {
                if(s.getID().equals(purchaseID))
                    {
                        if(s.isSoldOut())
                            return false;

                        else
                            return true;
                    }
            }

        return false;
    }

    /**
     * Method to return the BrandInventory object which has the ID inputted by
     * the user
     */
    public BrandInventory getAvailable(String purchaseID)
    {
	// TODO: you will need several methods to handle the purchase
	// I have a few below that you might consider.   Remember that this
	// class must handle the workings of the Vending Machine and not share
	// private information with the user interface of VendingMachineSim

        for(BrandInventory s : inventory)
            {
                if(s.getID().equals(purchaseID))
                    {
                        return s;
                    }
            }

        return null;
    }
    
    // this would be displayed for the user to see in many machines so that they know
    // how much more to deposit

    /**
     * Method to return the total amount deposited by the user.
     */
    public double getAmtDeposited()
    {
        return depositCoins.currentAmt() + dollars.getCurrentBills();
	
    }
	
    // TODO:  methods that accept bills and coins
    /**
     * Method to accept a dollar bill from the user
     */
    public void acceptBill()
    {
        dollars.depositBill();
    }

    /**
     * Method to accept a quarter from the user
     */
    public void acceptQuarter()
    {
        depositCoins.depositQuarter();
    }

    /**
     * Method to accept a dime from the user
     */
    public void acceptDime()
    {
        depositCoins.depositDime();
    }

    /**
     * Method to accept a nickle from the user
     */
    public void acceptNickle()
    {
        depositCoins.depositNickle();
    }

    /**
     * Method to check whether the Vending Machine has any item left or not
     */
    public boolean checkAllSoldOut()
    {
        int count = 0;
        
        for(BrandInventory s : inventory)
            {
                count += s.getCount();
            }

        if(count == 0)
            return true;
        
        return false;
    }
   
    // you may use a naive algorithm rather than trying to find an elegant algorithm
    // this assumes that you know the amount that needs to be returned to the user
    // after the purchase
    // returns true if it can make exact change and false otherwise

    /**
     * Method to generate change for the user in terms of quarters, dimes, and
     * nickles. 
     */
    public boolean makeChange(double amt)
    {
        int rq = 0;
        int rd = 0;
        int rn = 0;

        amt += 0.01;
        
        rq = (int)(amt/0.25);
        amt %= 0.25;

        if(rq > change.getQuarterCount())
            {
                amt = amt + ((rq - change.getQuarterCount()) * 0.25);
                rq = change.getQuarterCount();
                change.setQuarters(0);
            }

        else
            {
                change.setQuarters(change.getQuarterCount() - rq);
            }

        rd = (int)(amt/0.10);
        amt %= 0.10;

        if(rd > change.getDimeCount())
            {
                amt = amt + ((rd - change.getDimeCount()) * 0.10);
                rd = change.getDimeCount();
                change.setDimes(0);
            }

        else
            {
                change.setDimes(change.getDimeCount() - rd);
            }

        rn = (int)(amt/0.05);
        amt %= 0.05;

        if(rn > change.getNickleCount())
            {
                amt = amt + ((rn - change.getNickleCount()) * 0.05);
                rn = change.getNickleCount();
                change.setNickles(0);
            }

        else
            {
                change.setNickles(change.getNickleCount() - rn);
            }

        disp.takeChange(rq, rd, rn);
        amt -= 0.01;

        if(( (rq*0.25) + (rd*0.10) + (rn*0.05) ) == amt)
            {
                return false;
            }

        else
            {
                return true;
            }
    }
	
    // this is mostly a diagnostic method to check the stock
    /**
     * Method to print the ID's of itmes in the Vending Machine along with 
     * their stock.
     */
    public void printInventory()
    {
        disp.show("Choices - Stock\n\n");

        for(BrandInventory s : inventory)
            {
                disp.show(s.getID()+" - "+s.getCount());


            }
    }
	
    // this is used to let the user what their valid choices are
    /**
     * Method to print all the ID's of the items in the Vending machine.
     */
    public void printChoices()
    {
        disp.show("Choices - \n\n");
        
        for(BrandInventory s : inventory)
            {
                disp.show(s.getID());
            }
    }

    /**
     * Method to print the menu of Vedning Machine.
     */
    public void printMenu()
    {
        for(BrandInventory s : inventory)
            {
                disp.menu(("Brand ID: "+s.getID()+" Price: "), s.getPrice());
            }
    }

    /**
     * method to initiate the process of generating the change for the user.
     */
    public void arrangeTransfer()
    {
        change.transferCoinsFrom(depositCoins);
    }
	
    // use a method to handle clean up and transfer coins from the deposited coin box
    // to the change box and reset the bill counter
    /**
     * Method to initiate all the processes required to complete the 
     * transaction process with the user.
     */
    public void completeSale(String purchaseID)
    {
        depositCoins.resetBox();
        dollars.resetBills();

        for(BrandInventory s : inventory)
            {
                if(s.getID().equals(purchaseID))
                    {
                        s.reduceOnHand(1);
                        break;
                    }
            }
    }

} // end of class
    
