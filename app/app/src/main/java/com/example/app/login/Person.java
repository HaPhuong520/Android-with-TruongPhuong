package com.example.app.login;

public class Person {
    private String Surname,Name,Username,Password,Age;

    public Person(String surname, String name, String username, String password, String age) {
        Surname = surname;
        Name = name;
        Username = username;
        Password = password;
        Age = age;

    }



    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
