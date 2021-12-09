package com.studys.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseid;
    private String Title;
    private String Picture;
    private Long Creator;

    public Course(String title, String picture, Long creator) {
        this.courseid = courseid;
        Title = title;
        Picture = picture;
        Creator = creator;
    }

    public Course() {
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public Long getCreator() {
        return Creator;
    }

    public void setCreator(Long creator) {
        Creator = creator;
    }
}
