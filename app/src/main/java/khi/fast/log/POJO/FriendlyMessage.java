package khi.fast.log.POJO;

import java.util.UUID;

/**
 * Created by Hamza Ahmed on 25-Sep-17.
 */


public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;
    private String price;
    private boolean check;
    private String id;

    public FriendlyMessage() {

    }

    public FriendlyMessage(String text, String name, String photoUrl,String price,boolean check,String id) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.price=price;
        this.check=check;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}