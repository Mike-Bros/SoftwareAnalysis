package threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class representing a bank account user.
 * @author tcolburn
 */
public class BankAccountUser extends Thread{

    /**
     * Creates a new bank account user.
     * @param name the user's name
     * @param account the account used
     * @param transactions an array of integers representing deposits (positive)
     * and withdrawals (negative)
     */
    public BankAccountUser(String name, BankAccount account, int[] transactions) {
        super(name);
        this.account = account;
        this.transactions = transactions;
        if(transactions == null){
            transactionsRemaining = 0;
        }else{
        transactionsRemaining = transactions.length;
        }
    }

    /**
     * Getter for the number of transactions remaining for this user.
     * @return the number of transactions remaining
     */
    public int getTransactionsRemaining() {
        return transactionsRemaining;
    }
    
    /**
     * Runs the transactions in a loop.
     * When finished, a message is logged.
     */
    public void run(){
        for (int amount : transactions) {
            if (amount > 0) {
                account.deposit(amount, this);
            }
            else if (amount < 0) {
                try {
                    account.withdraw(Math.abs(amount), this);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BankAccountUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                // amount will not be zero
            }
            transactionsRemaining--;
        }
        try {
            Thread.sleep((int)(Math.random() * 100));
        } catch (InterruptedException ex) {
            Logger.getLogger(BankAccountUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public BankAccount getAccount(){
        return account;
    }
    
    public void setWaiting(boolean waiting){
        this.waiting = waiting;
    }
    
    public boolean getWaiting(){
        return waiting;
    }
    
    private final BankAccount account;
    private final int[] transactions;
    private int transactionsRemaining;
    private boolean waiting = false;
}
