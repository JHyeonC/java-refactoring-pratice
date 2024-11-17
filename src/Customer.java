import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String name;
    private Vector rentals = new Vector();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        this.rentals.addElement(arg);
    }

    public String getName() {
        return this.name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentalEnumeration = rentals.elements();

        String result = getName() + "고객님의 대여 기록\n";

        while(rentalEnumeration.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentalEnumeration.nextElement();

            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if(each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if(each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }

            frequentRenterPoints ++;
            if((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
                each.getDaysRented() > 1) frequentRenterPoints ++;

            result  = "\t" + each.getMovie().getTitle() + "\t" +
                String.valueOf(thisAmount) + "\n";

            totalAmount += thisAmount;

            result += "누적 대여로: " + String.valueOf(totalAmount) + "\n";
            result += "적립 포인트: " + String.valueOf(frequentRenterPoints);
        }
        return result;
    }

}
