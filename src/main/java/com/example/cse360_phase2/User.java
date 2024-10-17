package com.example.cse360_project1;

import javafx.scene.Scene;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private String userType;
    private String password;
    private ArrayList<Book> books;
    public User(int id, String name, String userType, String password) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.password = password;
        this.books = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }
    public Map<String, Integer> getCategoriesSold() {
        Map<String, Integer> categoryCount = new HashMap<>();

        // Count occurrences of each category
        for (Book book : books) {
            for (String category : book.getCategories()) {
                categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
            }
        }

        return categoryCount;
    }
    public Map<String, Integer> getConditionsSold() {
        Map<String, Integer> conditionsCount = new HashMap<>();

        // Count occurrences of each category
        for (Book book : books) {
            String condition = book.getCondition();
            conditionsCount.put(condition, conditionsCount.getOrDefault(condition, 0) + 1);
        }

        return conditionsCount;
    }

    public void registerUser() {
        try {
            Connection connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_schema", "root", "!!mqsqlhubbard2024");
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("INSERT INTO users (username, password) VALUES ('" + name + "', '" + password + "')");
            System.out.println(results);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return id + " | " + name + " " + userType;
    }
}
