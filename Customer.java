import java.util.Vector;
import java.util.Enumeration;

public class Customer
{
    private String _name;
    private Vector _rentals = new Vector();
    
    public Customer(String name){
        _name = name;
    }
    
    public void addRental (Rental arg){
        _rentals.addElement(arg);
    }
    public String getName(){
        return _name;
    }
    public String statement(){
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while(rentals.hasMoreElements()){
            double thisAmount = 0; 
            Rental each = (Rental) rentals.nextElement();

            thisAmount = each.getCharge();

            //add frequent renter points
            frequentRenterPoints ++;
            
            //add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints ++;
                
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        
        return result;
    }

    public static void main(String[] arg){
        Customer customer = new Customer("Johel");
        Movie movie0 = new Movie("Monty", 1);
        Movie movie01 = new Movie("Ran", 2);
        Movie movie1 = new Movie("Star Wars", 2);
        Movie movie2 = new Movie("Star Trek", 2);
        Movie movie3 = new Movie("Wallace and Gromit", 1);
        Rental rental = new Rental(movie1, 6);
        Rental rental1 = new Rental(movie0, 1);
        Rental rental2 = new Rental(movie01, 2);
        Rental rental3 = new Rental(movie2, 6);
        Rental rental4 = new Rental(movie3, 6);
        
        customer.addRental(rental);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);
        customer.addRental(rental4);

        String res = customer.statement();
        System.out.println(res);
    }
}
