package PsicObj;

import java.util.Date;


import DataStructures.Stack;

public class Publication {
    String content;
    int nUps;
    User user;
    Date publishDate;
    Stack<Publication> comments;
    Stack<Publication> auxcomments;

    public Publication(String content, User user, Date publishDate) {
        this.content = content;
        this.nUps = nUps;
        this.user = user;
        this.publishDate = publishDate; //Fijar la fecha de publicación es tarea de la parte de control.
        this.comments=new Stack<>();
        this.auxcomments=new Stack<>();
    }

    //Edit content
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void deletePost(){
        //Requiere acceso a la colección de la que es parte
    }

    public void addComment(String commentContent, User user, Date date){
        comments.push(new Publication(commentContent, user,date));
    }

    public Stack<Publication> getComments() {
        return comments;
    }

    public Stack<Publication> getAuxcomments() {return auxcomments;  }

    public void giveAnUp(){
        nUps++;
    }

    public void removeUp(){
        nUps--;
    }
}
