package khi.fast.log.model;

/**
 * Created by Hamza Ahmed on 27-Sep-17.
 */

public class TeamsPicture {
    private String photoUrl;
    private String updatedDate;

    public TeamsPicture(){

    }

    public TeamsPicture(String url,String date){
        photoUrl=url;
        updatedDate=date;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
