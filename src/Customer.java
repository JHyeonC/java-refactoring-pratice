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
        Enumeration rentalEnumeration = rentals.elements();

        String result = getName() + "고객님의 대여 기록\n";

        while(rentalEnumeration.hasMoreElements()) {
            Rental each = (Rental) rentalEnumeration.nextElement();

            result  = "\t" + each.getMovie().getTitle() + "\t" +
                String.valueOf(each.getCharge()) + "\n";

            result += "누적 대여로: " + String.valueOf(getTotalCharge()) + "\n";
            result += "적립 포인트: " + String.valueOf(getTotalFrequentRenterPoints());
        }
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration rentalEnumeration = rentals.elements();
        while(rentalEnumeration.hasMoreElements()) {
            Rental each = (Rental) rentalEnumeration.nextElement();
            result += each.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration rentalEnumeration = rentals.elements();
        while(rentalEnumeration.hasMoreElements()) {
            Rental each = (Rental) rentalEnumeration.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

}
