package com.example.scandaloussales;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_ITEM_NAME = "itemName";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "userId";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_PRICE = "itemPrice";
    public static final String KEY_UPC = "upc";

    public Post() {
    }

    public String getItemName(){
        return getString(KEY_ITEM_NAME);
    }

    public void setItemName(String itemName){
        put(KEY_ITEM_NAME, itemName);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE,parseFile);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public Date getCreatedAt(){ return getDate(KEY_CREATED_KEY); }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public int getPrice() {
        return getInt(KEY_PRICE);
    }

    public void setPrice(int price){ put(KEY_PRICE,price);}

    public int getUpc() {
        return getInt(KEY_UPC);
    }

    public void setUpc(long upc){put(KEY_UPC, upc);}
}
