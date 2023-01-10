public class CoinBox
{
    /*
     * TODO: Define private fields that track the number of each coin.	
     */

    private int quarter; // 25 cents = 1 quarter
    private int dime;    // 10 cents = 1 dime
    private int nickle;  // 05 cents = 1 nickle
    private Display display;
    // no pennies or dollar coins in this implementation
  

    /*
     * TODO: Define a zero-parameter constructor 
     * that initializes the deposited coins.
     */
    /**
     * Constructor to initialize the instance variables 
     */
    public CoinBox()
    {
        quarter = 0;
        dime = 0;
        nickle = 0;
        display = new Display();
    }
	
    // TODO:
    // Define a method to get the current value of the deposited coins in dollar format
    // You may work with an integer if you prefer

    /**
     * Finding the amount stored in the current coin Box
     */
    public double currentAmt()
    {
        double amt = 0;

        amt += (nickle * 0.05);
        amt += (dime * 0.10);
        amt += (quarter * 0.25);

        return amt;
    }
  
    /*
     * TODO: Define methods for depositing coins.
     * Define a separate method for each coin type. 
     */
    /**
     * Depositing a Quarter
     */
    public void depositQuarter()
    {
        quarter++;
    }

    /**
     * Depositing a Dime
     */
    public void depositDime()
    {
        dime++;
    }

    /**
     * Depositing a Nickle
     */
    public void depositNickle()
    {
        nickle++;
    }

    /**
     * Removing one Quarter
     */
    public void removeOneQuarter()
    {
        quarter--;
    }

    /**
     * Removing one Dime
     */
    public void removeOneDime()
    {
        dime--;
    }

    /**
     * Removing one Nickle
     */
    public void removeOneNickle()
    {
        nickle--;
    }
    
    /*
     * TODO: Define methods for returning the deposited
     * coins. Define a separate method for each coin type.
     * @return deposited coins of a particular type
     */

    /**
     * Method to return the number of Quarters in the current Coin Box
     */
    public int getQuarterCount()
    {
        return quarter;
    }

    /**
     * Method to return the number of Dimes in the current Coin Box
     */
    public int getDimeCount()
    {
        return dime;
    }

    /**
     * Method to return the number of Nickles in the current Coin Box
     */
    public int getNickleCount()
    {
        return nickle;
    }

    /*
     * TODO: Define, for each coin type, a method that 
     * sets a new value for that coin type.
     * Useful when initializing the program or transferring coins between coin boxes
     * @param The new number of coins for a given coin type
     */
    /**
     * Assigning a new value to the Quarter Count
     */
    public void setQuarters(int num_coins)
    {
        if(num_coins < 0)
            {
                display.error("Invalid assignment for a quarter.");
                return;
            }
        
        quarter = num_coins;
    }

    /**
     * Assigning a new value to the Dime Count
     */
    public void setDimes(int num_coins)
    {
        if(num_coins < 0)
            {
                display.error("Invalid assignment for a dime.");
                return;
            }
        
        dime = num_coins;
    }

    /**
     * Assigning a new value to the Nickle Count
     */
    public void setNickles(int num_coins)
    {
        if(num_coins < 0)
            {
                display.error("Invalid assignment for a nickle.");
                return;
            }
        
        nickle = num_coins;
    }
 
    /*
     * TODO: Define a method for transferring coins from a
     * coin box to another coin box.
     * Note that one of the coin boxes receives the deposited coins 
     * from users. The other coin box provides change to users.
     * @param the CoinBox that accepts coins from user. 
     */
    /**
     * Method to transfer coins from the given Coin Box to the current Coin Box
     */
    public void transferCoinsFrom(CoinBox other)
    {
        // take from the other box and deposit in this box
        this.setQuarters(this.getQuarterCount() + other.getQuarterCount());
        this.setDimes(this.getDimeCount() + other.getDimeCount());
        this.setNickles(this.getNickleCount() + other.getNickleCount());
    }
  
    // Optional.  Reset the number of coins in the coin box to be all zeros
    /**
     * Method to reset the count of all the coins to zero.
     */
    public void resetBox()
    {
        setQuarters(0);
        setDimes(0);
        setNickles(0);
    }
} // end class
