package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "foods")
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String img;
    private String calo;
    private String session;

    public Food(int id, String name, String img, String calo, String session) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.calo = calo;
        this.session = session;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCalo(String calo) {
        this.calo = calo;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getCalo() {
        return calo;
    }

    public String getSession() {
        return session;
    }
}
