import java.util.Scanner;

public class VendingMachineSim
{
    /**
     * Method main to start the class and enable user interaction, while also
     * enabling smooth interactions of multiple methods and objects with each
     * other. 
     */
    public static void main (String [] args)
    {
        Scanner sc = new Scanner(System.in);
        Display display = new Display();
        VendingMachine obj = new VendingMachine();
        
        // remember to give some introduction to the simulation
        display.show("Welcome to the Soda Shack!!");
        display.show("Hang on while I get set up...");
        display.show("In case of shortage of change, we will provide the closest possible change.");
        
        outer:
        while(true)
            {
                display.show("Do you want to buy a beverage? (yes or no)");
                String response = sc.next();

                if(obj.checkAllSoldOut())
                    {
                        display.allSoldOut();
                        break outer;
                    }
                
                if (response.toLowerCase().equals("yes"))
                    {
                        display.show("Current Selection: ");
                        obj.printMenu();

                        display.show("Enter the ID of the beverage you want:");
                        String choice = sc.next();

                        if(!(obj.isValidID(choice)))
                            {
                                display.error("Invalid ID.");
                                continue outer;
                            }
                            
                        if(!(obj.isAvailable(choice)))
                            {
                                display.soldOut();
                                continue outer; 
                            }

                        BrandInventory ch = obj.getAvailable(choice);
                        double deposited_amt = obj.getAmtDeposited();
                        double price = Math.round(ch.getPrice()*100.00)/100.00;
                        
                        while(ch.getPrice() > deposited_amt)
                            {
                                display.printPrice(price,deposited_amt);

                                display.show("Insert bill, quarter, dime, or nickle: ");
                                String paying = sc.next();

                                if(paying.equalsIgnoreCase("bill"))
                                    obj.acceptBill();

                                else if(paying.equalsIgnoreCase("quarter"))
                                    obj.acceptQuarter();

                                else if(paying.equalsIgnoreCase("dime"))
                                    obj.acceptDime();

                                else if(paying.equalsIgnoreCase("nickle"))
                                    obj.acceptNickle();

                                else
                                    display.error("Invalid response.");

                                deposited_amt = obj.getAmtDeposited();
                            }
                        
                        display.sold();
                        double give_user = deposited_amt - ch.getPrice();
                        give_user = Math.round(give_user*100.00)/100.00;
                        
                        display.menu("Change due: ", give_user);
                        obj.arrangeTransfer();
                        
                        if(give_user != 0)
                            {
                                if(!obj.makeChange(give_user))
                                    display.notExactChange();
                            }
                        
                        obj.completeSale(choice);
                    }  // if "yes"
                
                else if (response.toLowerCase().equals("no"))
                    {
                        display.show("Thank you for visiting the Soda Shack!");
                        break outer;
                    }

                else
                    {
                        display.error("Invalid response received.");
                    }
                
            }	// while


    } // main


} // class
