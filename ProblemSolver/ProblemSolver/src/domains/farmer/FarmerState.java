/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.farmer;

import framework.problem.State;

/**
 *
 * @author Mike
 */
public class FarmerState implements State{
    
    FarmerState(String farmer, String wolf, String goat, String cabbage) {
        String[] objectSides = {farmer,wolf,goat,cabbage};
        
        for(int i=0;i<objects.length;i++){
            setSide(objectSides[i],i);
        }
    }
    

    @Override
    public boolean equals(Object other) {
        if(other instanceof FarmerState){
            FarmerState otherState = (FarmerState) other;
            return (otherState.west == this.west) && (otherState.east == this.east);
        }else{
            return false;
        }
        
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   |  |   \n");
        for(int i=0;i<objects.length;i++){
            if(west[i]==true){
                sb.append(objects[i]+"|  |   ");
            }else if(east[i]==true){
                sb.append("   |  |"+ objects[i]);
            }
            sb.append("\n");
        }
        
        sb.append("   |  |   ");
        return sb.toString();
    }
    
    
    private void setSide(String side, int index){
    
        if(side=="West"){
            west[index]=true;
        }else if(side=="East"){
            east[index]=true;
        }else{
            throw new Error("Problem initializing state");
        }
    }
    
    final Boolean[] west = {false,false,false,false};
    final Boolean[] east = {false,false,false,false};
    private final String[] objects = {" F "," W "," G "," C "};
}
