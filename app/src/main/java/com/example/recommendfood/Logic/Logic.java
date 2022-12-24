package com.example.recommendfood.Logic;

import java.util.HashMap;
import java.util.List;

public class Logic {
    private List<Node>  listNode;

    private HashMap<String,String> cauhoi = new HashMap<String,String>();
    private Node nEnd ;

    public Logic(List<Node> listNode, HashMap<String, String> cauhoi) {
        this.listNode = listNode;
        this.cauhoi = cauhoi;
    }

    public void setListNode(List<Node> listNode) {
        this.listNode = listNode;
    }

    public void setCauhoi(HashMap<String, String> cauhoi) {
        this.cauhoi = cauhoi;
    }

    public void setnEnd(Node nEnd) {
        this.nEnd = nEnd;
    }

    public List<Node> getListNode() {
        return listNode;
    }

    public HashMap<String, String> getCauhoi() {
        return cauhoi;
    }

    public Node getnEnd() {
        return nEnd;
    }

    public Node duyet(Node node) {
        if (node.getListNodenext() == null) {
            return this.nEnd = node;

        }
        else
        {
            for (int i = 0; i < node.getListNodenext().size(); i++) {
                    if (cauhoi.get(node.getListNodenext().get(i).getThuoctinh().toString())
                            .compareTo(node.getListNodenext().get(i).getDieukien().toString())==0) {
                        return duyet(node.getListNodenext().get(i));
                    }
                }

            return null;
        }

    }






}
