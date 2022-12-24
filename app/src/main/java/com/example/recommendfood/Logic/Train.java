package com.example.recommendfood.Logic;

import android.util.Log;
import android.widget.TextView;

import com.example.recommendfood.Model.CategoryFood;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Train {

    Node node;
    //CategoryFood categoryFood;
//    List<CategoryFood> mListcategory = new ArrayList<CategoryFood>();
    List<CategoryFood> mListcategoryleaf = new ArrayList<CategoryFood>();
    List<Node> mListnode =new ArrayList<Node>();
    TextView txtTest;
    //claass tre
    Logic logic;

    //mangcauhoi
    HashMap<String,String> mCauhoil;


    public String logicfunc(List<CategoryFood> mListcategory,List<String> listAnswer)
    {
       // mListcategory = new ArrayList<CategoryFood>();
        mListnode = new ArrayList<Node>();
//        mListcategory = AppDatabase.getInstance(this).categoryFoodDao().getAllCategory();
        node = new Node(0,mListcategory,"Do tuoi");
        mListnode.add(node);

        //node tang 1 thieu nien
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for (CategoryFood food : mListcategory) {
            if(food.getName().compareTo("Mon kem")==0 || food.getName().compareTo("Mon banh bong lan")==0 ||
                    food.getName().compareTo("Mon ga")==0){
                mListcategoryleaf.add(food);
            };
        }
        node  = new Node(1,mListcategoryleaf,"Do tuoi","thieunien");
        mListnode.add(node);



        //node tang 1 thanh nien
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for (CategoryFood food : mListcategory) {
            if(food.getName().compareTo("Mon kem")!=0){
                mListcategoryleaf.add(food);
            };
        }
        node  = new Node(2,mListcategoryleaf,"Do tuoi","thanhnien");
        mListnode.add(node);


        mListnode.get(0).AddList(mListnode.get(1));
        mListnode.get(0).AddList(mListnode.get(2));





        //cay thieu nien tuc gian
        //node 3 tre con voi tuc gian
        //node
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(1).getmListCa()){
            if(food.getName().compareTo("Mon kem")==0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(3,mListcategoryleaf,"Cam xuc","tucgian");
        mListnode.add(node);
        mListnode.get(1).AddNodeNext(node);





        //node 4 tre con voi vui ve
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(1).getmListCa()){

            mListcategoryleaf.add(food);

        }
        node = new Node(4,mListcategoryleaf,"Cam xuc","vuive");
        mListnode.add(node);
        mListnode.get(1).AddNodeNext(node);


        //node 5 tr con buon ba
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(1).getmListCa()){
            if(food.getName().compareTo("Mon banh bong lan")==0||food.getName().compareTo("Mon ga")==0)
                mListcategoryleaf.add(food);

        }
        node = new Node(5,mListcategoryleaf,"Cam xuc","buon");
        mListnode.add(node);
        mListnode.get(1).AddNodeNext(node);




        //node 6 thanh nien vui ve
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(2).getmListCa()){
            if(food.getName().compareTo("Mon ca")!=0  && food.getName().compareTo("Mon banh bong lan")!=0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(6,mListcategoryleaf,"Cam xuc","vuive");
        mListnode.add(node);
        mListnode.get(2).AddNodeNext(node);

        //node 7 thanh nien buon
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(2).getmListCa()){
            if(food.getName().compareTo("Mon ca")==0  || food.getName().compareTo("Mon hat")==0
                    ||food.getName().compareTo("Mon chocolate")==0
                    || food.getName().compareTo("Mon banh bong lan")==0
                    ||food.getName().compareTo("Mon matcha")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(7,mListcategoryleaf,"Cam xuc","buon");
        mListnode.add(node);
        mListnode.get(2).AddNodeNext(node);

        //node 8 thanh nien tuc gian
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(2).getmListCa()){
            if(food.getName().compareTo("Mon ga")==0 ||food.getName().compareTo("Mon chocolate")==0
                    || food.getName().compareTo("Mon banh muffin")==0
                    || food.getName().compareTo("Mon matcha")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(8,mListcategoryleaf,"Cam xuc","tucgian");
        mListnode.add(node);
        mListnode.get(2).AddNodeNext(node);

        //node 9 thanh nien tuc gian nam
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(8).getmListCa()){
            if(food.getName().compareTo("Mon ga")==0 ||food.getName().compareTo("Mon chocolate")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(9,mListcategoryleaf,"Gioi tinh","nam");
        mListnode.add(node);
        mListnode.get(8).AddNodeNext(node);


        //node 10 thanh nien tuc gian nu
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(8).getmListCa()){
            if(food.getName().compareTo("Mon ga")==0
                    ||food.getName().compareTo("Mon matcha")==0
                    ||food.getName().compareTo("Mon chocolate")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(10,mListcategoryleaf,"Gioi tinh","nu");
        mListnode.add(node);
        mListnode.get(8).AddNodeNext(node);


        //node 11 thanh nien vui ve mau sang
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(6).getmListCa()){
            if(food.getName().compareTo("Mon matcha")==0
                    ||food.getName().compareTo("Mon ga")==0
                    ||food.getName().compareTo("Mon banh man")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(11,mListcategoryleaf,"Mau","sang");
        mListnode.add(node);
        mListnode.get(6).AddNodeNext(node);


        //node 12 thanh nien vui ve mau toi
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(6).getmListCa()){
            if(food.getName().compareTo("Mon matcha")==0
                    &&food.getName().compareTo("Mon ga")==0
                    &&food.getName().compareTo("Mon banh man")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(12,mListcategoryleaf,"Mau","toi");
        mListnode.add(node);
        mListnode.get(6).AddNodeNext(node);

        //node 13 thanh nien buon ve nam
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(7).getmListCa()){
            if( food.getName().compareTo("Mon banh bong lan")!=0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(13,mListcategoryleaf,"Gioi tinh","nam");
        mListnode.add(node);
        mListnode.get(7).AddNodeNext(node);


        //node 14thanh nien buon nu

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(7).getmListCa()){
            if( food.getName().compareTo("Mon chocolate")==0
                    ||food.getName().compareTo("Mon banh bong lan")==0
                    ||food.getName().compareTo("Mon matcha")==0  ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(14,mListcategoryleaf,"Gioi tinh","nu");
        mListnode.add(node);
        mListnode.get(7).AddNodeNext(node);
        //node 15thanh nien buon nam man

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(13).getmListCa()){
            if( food.getName().compareTo("Mon banh chocolate")!=0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(15,mListcategoryleaf,"Vi giac","man");
        mListnode.add(node);
        mListnode.get(13).AddNodeNext(node);

        //node 16thanh nien buon nam ngot

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(13).getmListCa()){
            if( food.getName().compareTo("Mon banh chocolate")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(16,mListcategoryleaf,"Vi giac","ngot");
        mListnode.add(node);
        mListnode.get(13).AddNodeNext(node);

        //node 17 thanh nien buon nam man  sangs

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(15).getmListCa()){
            if( food.getName().compareTo("Mon hat")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(17,mListcategoryleaf,"Mau","sang");
        mListnode.add(node);
        mListnode.get(15).AddNodeNext(node);

        //node 18 thanh nien buon nam man  toi

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(15).getmListCa()){
            if( food.getName().compareTo("Mon ca")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(18,mListcategoryleaf,"Mau","toi");
        mListnode.add(node);
        mListnode.get(15).AddNodeNext(node);


        //node 19 thanh nien tuc gian nu man
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(10).getmListCa()){
            if(food.getName().compareTo("Mon ga")==0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(19,mListcategoryleaf,"Vi giac","man");
        mListnode.add(node);
        mListnode.get(10).AddNodeNext(node);

        //node 20 thanh nien tuc gian nu ngot
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(10).getmListCa()){
            if(food.getName().compareTo("Mon ga")!=0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(20,mListcategoryleaf,"Vi giac","ngot");
        mListnode.add(node);
        mListnode.get(10).AddNodeNext(node);

        //node 21 thanh nien tuc gian nu ngot sang
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(20).getmListCa()){
            if(food.getName().compareTo("Mon chocolate")!=0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(21,mListcategoryleaf,"Mau","sang");
        mListnode.add(node);
        mListnode.get(20).AddNodeNext(node);

        //node 22 thanh nien tuc gian nu ngot toi
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(20).getmListCa()){
            if(food.getName().compareTo("Mon chocolate")==0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(22,mListcategoryleaf,"Mau","toi");
        mListnode.add(node);
        mListnode.get(20).AddNodeNext(node);

        //node 23 thanh nien buon nu sang

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(14).getmListCa()){
            if( food.getName().compareTo("Mon banh bong lan")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(23,mListcategoryleaf,"Mau","sang");
        mListnode.add(node);
        mListnode.get(14).AddNodeNext(node);

        //node 24 thanh nien buon nu toi

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(14).getmListCa()){
            if( food.getName().compareTo("Mon chocolate")==0 ){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(24,mListcategoryleaf,"Mau","toi");
        mListnode.add(node);
        mListnode.get(14).AddNodeNext(node);

        //node 23 tr con buon ba man
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(5).getmListCa()){
            if(food.getName().compareTo("Mon ga")==0)
                mListcategoryleaf.add(food);

        }
        node = new Node(23,mListcategoryleaf,"Vi giac","man");
        mListnode.add(node);
        mListnode.get(5).AddNodeNext(node);

        //node 24 tr con buon ba ngot
        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(5).getmListCa()){
            if(food.getName().compareTo("Mon banh bong lan")==0)
                mListcategoryleaf.add(food);

        }
        node = new Node(24,mListcategoryleaf,"Vi giac","ngot");
        mListnode.add(node);
        mListnode.get(5).AddNodeNext(node);

        //node 25 thanh nien buon nu sang

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(14).getmListCa()){
            if( food.getName().compareTo("Mon chocolate")!=0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(25,mListcategoryleaf,"Mau","sang");
        mListnode.add(node);
        mListnode.get(14).AddNodeNext(node);

        //node 26 thanh nien buon nu sang

        mListcategoryleaf = new ArrayList<CategoryFood>();
        for(CategoryFood food: mListnode.get(14).getmListCa()){
            if( food.getName().compareTo("Mon chocolate")==0){
                mListcategoryleaf.add(food);
            }
        }
        node = new Node(25,mListcategoryleaf,"Mau","toi");
        mListnode.add(node);
        mListnode.get(14).AddNodeNext(node);

        mCauhoil = new HashMap<String,String>();


        mCauhoil = new HashMap<String,String>();


        List<String> key=  Arrays.asList("Do tuoi", "Cam xuc", "Mau",  "Vi giac","Gioi tinh");

        for(int i=0; i<key.size();i++){

            mCauhoil.put(key.get(i),listAnswer.get(i));
            Log.d("test", key.get(i)+" "+listAnswer.get(i));
        }







//
//
//            logic = new Logic(mListnode,mCauhoil);
//            Node nodeend= new Node();
//            nodeend = logic.duyet(logic.getListNode().get(0));
//
//            String tesst = mCauhoil.get(mListnode.get(1).getThuoctinh());
//            txtTest.setText(tesst);

        String finaltest= "";
        Node nodeEnd  = duyet(mListnode.get(0),mCauhoil);
        List<CategoryFood> mlistend= new ArrayList<CategoryFood>();
        mlistend = randomFood(nodeEnd);



        for (CategoryFood food : mlistend) {
            finaltest += " "+food.getName();
        }
       // txtTest.setText(finaltest);
        return finaltest;
//            if(logic.duyet(logic.getListNode().get(0)) == null){
//                txtTest.setText("NULL");
//
//            }
//            else {
//                for (CategoryFood food : nodeend.getmListCa()) {
//                    finaltest += food.getName();
//                }
//                txtTest.setText(finaltest);
    }



    //    }
    public Node duyet(Node node, HashMap<String,String> mCauhoil) {

        if (node.getListNodenext().size() == 0) {

            return node;

        }
        else
        {
            for (int i = 0; i < node.getListNodenext().size(); i++) {
                if (mCauhoil.get(node.getListNodenext().get(i).getThuoctinh())
                        .compareTo(node.getListNodenext().get(i).getDieukien())==0) {
                    return duyet(node.getListNodenext().get(i),mCauhoil);
                }
            }

            return null;
        }

    }


    public List<CategoryFood> randomFood(Node node) {
        List<CategoryFood> listCate = new ArrayList<CategoryFood>();
        CategoryFood categoryFood;
        int min = 0;
        int max = node.getmListCa().size() - 1;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        listCate.add(node.getmListCa().get(random_int));
        random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);
        listCate.add(node.getmListCa().get(random_int));
        return listCate;

    }


    public CategoryFood randomCate(List<CategoryFood> categoryFoods) {
        CategoryFood categoryFood;
        int min = 0;
        int max = categoryFoods.size() - 1;
        int random_int = (int) Math.floor(Math.random() * (max - min + 1) + min);

        categoryFood = categoryFoods.get(random_int);
        return categoryFood;

    }
}
