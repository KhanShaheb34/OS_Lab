
package Bank;

public class Demo {

    public static void main(String[] args) {
        ManualBank bank = new ManualBank();
        Customer customer1 = new Customer(1000,bank);
        Customer customer2 = new Customer(1500,bank);
        Customer customer3 = new Customer(500,bank);
        Customer customer4 = new Customer(2000,bank);

        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
    }

}