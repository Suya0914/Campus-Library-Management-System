package com.library.dto;
public class BookAddDTO {
    private String title; private String author; private String publisher; private String isbn; private int stockQuantity;
    public String getTitle(){return title;} public void setTitle(String t){title=t;}
    public String getAuthor(){return author;} public void setAuthor(String a){author=a;}
    public String getPublisher(){return publisher;} public void setPublisher(String p){publisher=p;}
    public String getIsbn(){return isbn;} public void setIsbn(String i){isbn=i;}
    public int getStockQuantity(){return stockQuantity;} public void setStockQuantity(int s){stockQuantity=s;}
}
