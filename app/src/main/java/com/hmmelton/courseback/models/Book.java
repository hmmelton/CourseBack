package com.hmmelton.courseback.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by harrisonmelton on 5/8/16.
 */
public class Book {

    private String title;
    private double isbn;
    private int edition;
    private String condition;
    private String annotations;
    private String userId;
    private Date dateAdded;
    private String bookId;

    /**
     * This constructor is called when adding a new book to the database.
     */
    public Book() {}

    /**
     * This constructor is called when retrieving a book from the database
     * @param info
     */
    public Book(Map<String, Object> info) {
        title = (String) info.get("title");
        isbn = (double) info.get("isbn");
        edition = (int) info.get("edition");
        condition = (String) info.get("condition");
        annotations = (String) info.get("annotations");
        userId = (String) info.get("userId");
        dateAdded = (Date) info.get("dateAdded");
        bookId = (String) info.get("id");
    }

    /**
     * This method gets the book's title.
     * @return title of book
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method sets the book's title.
     * @param title title of book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method gets the book's ISBN number.
     * @return ISBN number of book
     */
    public double getIsbn() {
        return isbn;
    }

    /**
     * This method sets the book's ISBN number
     * @param isbn ISBN number of book
     */
    public void setIsbn(double isbn) {
        this.isbn = isbn;
    }

    /**
     * This method gets the book's edition.
     * @return edition of book
     */
    public int getEdition() {
        return edition;
    }

    /**
     * This method sets the book's edition.
     * @param edition edition of book
     */
    public void setEdition(int edition) {
        this.edition = edition;
    }

    /**
     * This method gets the book's condition.
     * @return condition of book
     */
    public String getCondition() {
        return condition;
    }

    /**
     * This method sets the book's condition.
     * @param condition condition of book
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * This method gets the book's level of annotation.
     * @return level of annotation in the book
     */
    public String getAnnotations() {
        return annotations;
    }

    /**
     * This method sets the book's level of annotation.
     * @param annotations level of annotation in the book
     */
    public void setAnnotations(String annotations) {
        this.annotations = annotations;
    }

    /**
     * This method gets the user ID associated with this book.
     * @return user ID associated with book
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method sets the user ID associated with this book.
     * @param userId user ID associated with book
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method gets the date the book was added to the database.
     * @return date book was added
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * This method sets the date the book was added to the database.
     * @param dateAdded date book was added to database
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * This method gets the book's ID number.
     * @return book's ID number
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * This method sets the book's ID number.
     * @param bookId book's ID number
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * This method returns a Map of the book's information.
     * @return Map of all book's information
     */
    public Map<String, Object> getInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("title", title);
        info.put("isbn", isbn);
        info.put("edition", edition);
        info.put("condition", condition);
        info.put("annotations", annotations);
        info.put("userId", userId);
        info.put("dateAdded", dateAdded);
        info.put("id", bookId);

        return info;
    }

}
