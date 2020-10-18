package PsicObj;

import java.util.Date;


import DataStructures.DynamicArray;
import DataStructures.Stack;

public class Publication {
    String content;
    int nUps;
    User user;
    Date publishDate;
    DynamicArray<Publication> comments;

    public Publication(String content, User user, Date publishDate) {
        this.content = content;
        this.nUps = nUps;
        this.user = user;
        this.publishDate = publishDate; //Fijar la fecha de publicación es tarea de la parte de control.
        this.comments=new DynamicArray<>();
    }

    //Edit content
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getnUps() {
        return nUps;
    }

    public User getUser() {
        return user;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void deletePost(){
        //Requiere acceso a la colección de la que es parte
    }

    public void addComment(String commentContent, User user, Date date){
        comments.insert(new Publication(commentContent, user,date));
    }

    public DynamicArray<Publication> getComments() {
        return comments;
    }

    public void giveAnUp(){
        nUps++;
    }

    public void removeUp(){
        nUps--;
    }
}
