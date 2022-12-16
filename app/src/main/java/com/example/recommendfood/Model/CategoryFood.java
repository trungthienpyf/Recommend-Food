package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "category_foods")
public class CategoryFood {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public CategoryFood( String name) {

        this.name = name;
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
}
