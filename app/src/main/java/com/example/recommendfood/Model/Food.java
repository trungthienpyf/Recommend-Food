package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "foods", foreignKeys = {
        @ForeignKey(
                entity = CategoryFood.class,
                parentColumns = {"id"},
                childColumns = {"category_id"},
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),

})
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    private String calo;
    private String session;
    private  int category_id;

    public Food(String name, String calo, String session, String category_id) {
        this.name = name;
        this.calo = calo;
        this.session = session;
        this.category_id = Integer.parseInt(category_id);
    }

    public Food() {

    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setImg(String img) {
//        this.img = img;
//    }

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

//    public String getImg() {
//        return img;
//    }

    public String getCalo() {
        return calo;
    }

    public String getSession() {
        return session;
    }
}
