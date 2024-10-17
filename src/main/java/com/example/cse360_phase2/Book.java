package com.example.cse360_project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Book {
    private int id;
    private String name;
    private String author;
    private String condition;
    private ArrayList<String> categories;
    private int collectionID;
    private double price;

    public Book(int id, String name, String author, String condition, String categoiesJSON, int collectionID) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.condition = condition;
        this.categories = parseJSON(categoiesJSON);
        this.collectionID = collectionID;

        switch (condition) {
            case "New" -> price = 10.50 * 2;
            case "Used" -> price = 10.50 * 1.5;
            case "Heavily Used" -> price = 10.50;
        }
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
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public ArrayList<String> getCategories() {
        return categories;
    }
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
    public void addCategory(String category) {
        categories.add(category);
    }
    public void removeCategory(String category) {
        categories.remove(category);
    }
    public String stringCategories(ArrayList<String> categories) {
        String categoriesString = "Categories: ";
        for (String category : categories) {
            categoriesString += category + ", ";
        }
        if (categoriesString.equals("Categories: ")) categoriesString = "Categories: None";
        return categoriesString;
    }

    public int getCollectionID() {
        return collectionID;
    }
    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }
    public ArrayList<String> parseJSON(String json) {
        String trimmed = json.substring(1, json.length() - 1);
        String[] items = trimmed.split(",\\s*");

        // Remove quotes around each item and add to the list
        ArrayList<String> list = new ArrayList<>();
        for (String item : items) {
            list.add(item.substring(1, item.length() - 1));
        }
        return list;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", $" + price + " " + author + " " + condition + " " + stringCategories(categories);
    }

}
