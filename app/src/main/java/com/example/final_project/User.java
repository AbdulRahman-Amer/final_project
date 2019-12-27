package com.example.final_project;

public class User {
    private String tid;
    private String FirstName;
    private String LastName;
    private String FavFood;

    public User(String ttid,String fName,String lName, String fFood){
        tid = ttid;
        FirstName = fName;
        LastName = lName;
        FavFood = fFood;
    }

    public String gettid() {
        return tid;
    }

    public void settid(String ttid) {
        tid = ttid;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFavFood() {
        return FavFood;
    }

    public void setFavFood(String favFood) {
        FavFood = favFood;
    }
}
