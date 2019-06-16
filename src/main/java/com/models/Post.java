package com.models;


import javax.persistence.*;

@Entity
@Table( name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer genre_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private String title;
    private String annotation;

    public Post()
    {}

    public Post(Integer genre_id, Integer user_id, User author, String title, String annotation)
    {
        this.genre_id = genre_id;
        this.author = author;
        this.title = title;
        this.annotation = annotation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
