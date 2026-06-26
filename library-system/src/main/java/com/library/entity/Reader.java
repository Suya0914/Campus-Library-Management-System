package com.library.entity;
import java.util.ArrayList;
import java.util.List;
public class Reader {
    private String name; private String cardNumber;
    private List<String> borrowedBooksIsbn = new ArrayList<>();
    private int borrowedBooksCount;
    public Reader() {}
    public Reader(String n,String c) { name=n; cardNumber=c; }
    public String getName(){return name;} public void setName(String n){name=n;}
    public String getCardNumber(){return cardNumber;} public void setCardNumber(String c){cardNumber=c;}
    public List<String> getBorrowedBooksIsbn(){return borrowedBooksIsbn;} public void setBorrowedBooksIsbn(List<String>b){borrowedBooksIsbn=b;}
    public int getBorrowedBooksCount(){return borrowedBooksCount;} public void setBorrowedBooksCount(int c){borrowedBooksCount=c;}
}
