package com.studys.web.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lessonid;
    private int Number;
    private Long courseid;
    private String Title;
    private String Text;
    private String HomeWork;

    public Lesson(int number, Long courseid, String title, String text, String homeWork) {
        Number = number;
        this.courseid = courseid;
        Title = title;
        Text = text;
        HomeWork = homeWork;
    }

    public Lesson() {
    }

    public Long getLessonid() {
        return lessonid;
    }

    public void setLessonid(Long lessonid) {
        this.lessonid = lessonid;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getHomeWork() {
        return HomeWork;
    }

    public void setHomeWork(String homeWork) {
        HomeWork = homeWork;
    }
}
