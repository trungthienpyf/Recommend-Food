package com.example.recommendfood.Logic;

import com.example.recommendfood.Model.CategoryFood;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int idNode;
    private List<CategoryFood> mListCa;
    private List<Node> ListNodenext = new ArrayList<Node>();
    private String nameNode;
    private String thuoctinh;
    private String dieukien;

    public Node(int idNode, List<CategoryFood> mListCa, List<Node> listNodenext, String nameNode, String thuoctinh) {
        this.idNode = idNode;
        this.mListCa = mListCa;
        ListNodenext = listNodenext;
        this.nameNode = nameNode;
        this.thuoctinh = thuoctinh;
    }

    public void AddList(Node node){
        this.getListNodenext().add(node);
    }

    public Node(int idNode, List<CategoryFood> mListCa, String thuoctinh, String dieukien) {
        this.idNode = idNode;
        this.mListCa = mListCa;
        this.thuoctinh = thuoctinh;
        this.dieukien = dieukien;
    }

    public Node() {

    }

    public String getThuoctinh() {
        return thuoctinh;
    }

    public String getDieukien() {
        return dieukien;
    }

    public List<Node> getListNodenext() {
        return ListNodenext;
    }
    public void AddNodeNext(Node node){
        this.ListNodenext.add(node);
    }
    public Node(int idNode, List<CategoryFood> mListCa, String nameNode) {
        this.idNode = idNode;
        this.mListCa = mListCa;
        this.nameNode = nameNode;
    }

    public void setIdNode(int idNode) {
        this.idNode = idNode;
    }

    public void setmListCa(List<CategoryFood> mListCa) {
        this.mListCa = mListCa;
    }

    public void setNameNode(String nameNode) {
        this.nameNode = nameNode;
    }

    public int getIdNode() {
        return idNode;
    }

    public List<CategoryFood> getmListCa() {
        return mListCa;
    }

    public String getNameNode() {
        return nameNode;
    }
}
