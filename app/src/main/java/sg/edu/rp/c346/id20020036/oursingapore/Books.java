package sg.edu.rp.c346.id20020036.oursingapore;

import java.io.Serializable;

public class Books implements Serializable {

    private int id;
    private String title;
    private String author;
    private int num;
    private int stars;

    public Books(int id, String title, String author, int num, int stars) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.num = num;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Books setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Books setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Books setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getNum() {
        return String.valueOf(num);
    }

    public Books setNum(int num) {
        this.num = num;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Books setStars(int stars) {
        this.stars = stars;
        return this;
    }

    /*@NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return starsString;

    }*/
}
