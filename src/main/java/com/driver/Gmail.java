package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    LinkedHashSet<String> Order=new LinkedHashSet<>();
    HashMap<String,Mail> Inbox=new HashMap<>();
    HashMap<String,Mail> Trash=new HashMap<>();
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
    }
    public Gmail(String emailId) {
        super(emailId);
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        Mail m=new Mail(message,sender,date);
        if(Inbox.size()<inboxCapacity){
            Order.add(message);
            Inbox.put(message,m);
        }
        else{
            String msg=Order.getFirst();
            Trash.put(msg,Inbox.get(msg));
            Order.remove(msg);
            Inbox.remove(msg);
            Order.add(message);
            Inbox.put(message,m);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        if(Order.contains(message)){
            Trash.put(message,Inbox.get(message));
            Order.remove(message);
            Inbox.remove(message);
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.isEmpty())
            return null;
        return Order.getLast();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.isEmpty())
            return null;
        return Order.getFirst();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int ct=0;
        for(Mail m:Inbox.values()){
            if(m.date.after(start) && m.date.before(end))
                ct++;
        }
        return ct;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}

class Mail{
    String message;
    String sender;
    Date date;
    Mail(String message,String sender,Date date){
        this.date=date;
        this.sender=sender;
        this.message=message;
    }
}
