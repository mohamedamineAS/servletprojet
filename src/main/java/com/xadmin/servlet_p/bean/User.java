package com.xadmin.servlet_p.bean;


public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String number;

    // Constructor
    public User(int id, String name, String password, String email, String number) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.number = number;
    }
  

    public User(String name2, String password2, String email2, String number2) {
    	 this.name = name2;
         this.password = password2;
         this.email = email2;
         this.number = number2;
	}
	// Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}