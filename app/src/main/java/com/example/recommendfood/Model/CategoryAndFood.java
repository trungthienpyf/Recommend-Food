package com.example.recommendfood.Model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryAndFood {

        @Embedded
        public Food food;


    @Relation(entity = CategoryFood.class,parentColumn = "category_id", entityColumn = "id")
    public
    List<CategoryFood> categoryFoodListList;


}
