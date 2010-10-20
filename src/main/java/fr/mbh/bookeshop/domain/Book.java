/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.mbh.bookeshop.domain;

/**
 *
 * @author Mahmoud
 */
public class Book {

    private String isbn;

    private String title;

    private String author;

    private int year;

    private double price;

    private int quantity;

    private int category;

    public Book() {
    }

    public Book(String isbn, String title, String author, int year, int category, double price,int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "fr.mbh.entities.Book[isbn=" + isbn + "]";
    }

}
