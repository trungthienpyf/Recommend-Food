package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private boolean gender;
    private String age;
    private String tk;
    private String password;
    private int level;
    private String height;
    private String weight;
    private int status;



//    public void User(int id, String name, boolean gender, String age, String tk, String password, int level, String height, String weight, int status) {
//        this.id = id;
//        this.name = name;
//        this.gender = gender;
//        this.age = age;
//        this.tk = tk;
//        this.password = password;
//        this.level = level;
//        this.height = height;
//        this.weight = weight;
//        this.status = status;
//    }

    public User(String tk, String password) {
        this.tk = tk;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getTk() {
        return tk;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public int getStatus() {
        return status;
    }
}