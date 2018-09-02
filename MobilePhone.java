import java.util.*;
/**
 * MobilePhone
 */
public class MobilePhone {
    private int id;
    private boolean status;
    Exchange parent;//to assign parent to the phone 
    MobilePhone(int number){
        //constructor to create a mobile phone. Unique identifier for a mobile phone is an integer.
        status = true;
        id = number;
        parent = null;
    }
    public int number(){
        //returns the id of the mobile phone.
        return id;
    }
    public boolean status(){
        // returns the status of the phone, i.e.switched on or switched off.
        return status;
    }
    public void switchOn(){
        status = true;
    }
    public void switchOff(){
        status = false;
    }
    public Exchange location(){
    // returns the base station with which the phone is registered if the phone is switched on and an excep-tion  if  the  phone  is  off.   The  class Exchange will  be  described next. 
        if(this.status())
            return parent;
        else
            System.out.println("Phone is Switched Off");
        return null;
    }
}