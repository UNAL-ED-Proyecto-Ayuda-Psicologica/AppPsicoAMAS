package PsicObj;

import java.util.Date;


import DataStructures.*;
import DataStructures.Stack;

public class Publication implements Comparable {
    String content;
    int nUps;
    AVLTree<User> users;
    User user;
    Date publishDate;
    DynamicArray<Publication> comments;

    public Publication(String content, User user) {
        this.content = content;
        this.nUps = nUps;
        this.user = user;
        //this.publishDate = publishDate; //Fijar la fecha de publicación es tarea de la parte de control.
        this.comments=new DynamicArray<>();
        this.users=new AVLTree<>();
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
        comments.insert(new Publication(commentContent, user));
    }

    public DynamicArray<Publication> getComments() {
        return comments;
    }

    public void toggleAnUp(User user){
        if(!this.users.contains(user)) {
            this.users.insert(user);
            nUps++;
        }else {
            this.users.remove(user);
            nUps--;
        }


    }

    public void removeUp(){
        nUps--;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
