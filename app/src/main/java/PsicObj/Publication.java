package PsicObj;

import java.util.Date;

import DataStructures.List;

public class Publication {
    String content;
    int nUps;
    User user;
    Date publishDate;
    List<Publication> comments;

    public Publication(String content, User user, Date publishDate) {
        this.content = content;
        this.nUps = nUps;
        this.user = user;
        this.publishDate = publishDate; //Fijar la fecha de publicación es tarea de la parte de control.
    }

    public Publication() {

    }

    //Edit content
    public void setContent(String content) {
        this.content = content;
    }

    public void deletePost(){
        //Requiere acceso a la colección de la que es parte
    }

    public void addComment(String commentContent, User user, Date date){
        comments.insert(new Publication());
    }

    public void giveAnUp(){
        nUps++;
    }

    public void removeUp(){
        nUps--;
    }
}
