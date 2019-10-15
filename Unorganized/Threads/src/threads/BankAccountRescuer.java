/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Bros
 */
public class BankAccountRescuer extends BankAccountUser{
    
    public BankAccountRescuer(String name, BankAccount account, BankAccountUser[] userArr) {
        super(name, account, null);
        this.userArr = userArr;
        this.account = account;
        System.out.println("LOL RESCUE ME");
    }
    
    @Override
    public void run(){
        
        if(allFinished()){
            System.exit(0);
        }
        
        if(allWaiting()){
            account.deposit(100, this);
        }
        try {
            Thread.sleep((int)(Math.random() * 100));
        } catch (InterruptedException ex) {
            Logger.getLogger(BankAccountRescuer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean allFinished(){
    boolean allFinished = true;
        for(int i=0;i<userArr.length;i++){
            if(!checkFinished(userArr[i])){
                allFinished = false;
            }
        }
        return allFinished;
    }
    
    private boolean allWaiting(){
        for(int i=0;i<userArr.length;i++){
            if(!checkFinished(userArr[i]) && !userArr[i].getWaiting()){
                return false;
            }
        }
        return true;
    }
    
    private boolean checkFinished(BankAccountUser user) {
        boolean isFinished = false;
        if (user.getTransactionsRemaining() == 1) {
            isFinished = true;
        }
        return isFinished;
    }
    
    private BankAccountUser[] userArr;
    private BankAccount account;
}
