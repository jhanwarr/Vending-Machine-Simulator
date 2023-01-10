public class BillBox
{
    /* private data fields  */
    /**
     *To store the count of bills entered
     */
    private int bill_count;

    /**
     * constructor to initialize the bill count to 0 
     */
    public BillBox()
    {
        
        bill_count = 0;
    }

    /**
     * For accepting the Dollar Bill inputted
     */
    public void depositBill()
    {
        
        bill_count++;
    }

    	
    /* 
     * TODO: Define a method that resets the deposited bill in the current transaction
     */
    /**
     * For resetting the count of dollar bills inputted
     */
    public void resetBills()
    {
        
        bill_count = 0;
    }
    
    /*
     * TODO: Define a method that returns the deposited bills
     * @return the number of recently deposited bills 
     */
    /**
     * For returning the Dollar Bills inputted
     */
    public int getCurrentBills()
    {
        
        return bill_count;
    }	
}
