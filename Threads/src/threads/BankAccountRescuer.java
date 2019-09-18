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
 * @author bros0167
 */
public class BankAccountRescuer extends BankAccountUser{
    
    public BankAccountRescuer(String name, BankAccount account, BankAccountUser[] userArr) {
        super(name, account, null);
        this.userArr = userArr;
        this.account = account;
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
            if(checkFinished(userArr[i])){
                allFinished = false;
            }
        }
        return allFinished;
    }
    
    private boolean allWaiting(){
        boolean allWaiting = true;
        for(int i=0;i<userArr.length;i++){
            if(!checkFinished(userArr[i])){
                if(!userArr[i].getWaiting()){
                    allWaiting = false;
                }
            }
        }
        return allWaiting;
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
