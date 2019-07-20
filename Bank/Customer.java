
package Bank;

public class Customer extends Thread {

    ManualBank bank;
    int amount;
    public Customer(int amount, ManualBank bank){
        this.amount = amount;
        this.bank = bank;
    }

    @Override
    public void run(){
        bank.withDraw(amount);
    }
}