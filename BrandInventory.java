//import java.util.*;

public class BrandInventory
{
    // this class keeps track of the information for each separate brand stocked
    // in the vending machine. The machine will keep an ArrayList of these objects
	
    //  private data fields
    private String id;
    private float price;
    private int items;

    /**
     *Constructor to initializa the instance variables
     */
    public BrandInventory(String id, float price, int numItems)
    {
        this.id = id;
        this.price = price;
        this.items = numItems;
    }
	
    // TODO: create accessors for each of the private fields
    /**
     * returning the ID
     */
    public String getID()
    {
        return id;
    }

    /**
     * returning the price
     */
    public float getPrice()
    {
        return price;
    }

    /**
     * returning the number of items of ID in stock
     */
    public int getCount()
    {
        return items;
    }
	
    /*
     *  TODO: Define a method that updates the available 
     *  number of sodas after a successful purchase.
     */
    /**
     *Constructor to initializa the instance variables
     */
    public void reduceOnHand(int num_bought)
    {
        items -= num_bought;
    }
	
    /* TODO: Define a method that returns true if 
     * the number on hand for this brand has dropped to 0
     */
    /**
     * checking whether the item is sold out or not
     */
    public boolean isSoldOut()
    {
        if(items == 0)
            return true;

        else
            return false;
    }
	
    /* TODO: Define a method that returns a string containing
     * the ID of the brand, the formatted price of each soda
     */
    /**
     * returning the details of ID in one string
     */
    public String toString()
    {
        return ""+id+" $"+(Math.round(price*100.00)/100.00)+" "+items;
    }
}
