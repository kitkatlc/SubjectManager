package kitkat.com.subjectmanager.database.table;

import java.util.Date;

public class SubjectEntity {
    int weekday;
    String coursename;
    Date startweek;
    Date  endweek;
    String room;
    int lesson;
    int year;
    int month;
    int day;
    String WEEK;

    public void setWEEK(String WEEK) {
        this.WEEK = WEEK;
    }

    public String getWEEK() {
        return WEEK;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getLesson() {
        return lesson;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCourse(String coursename) {
        this.coursename = coursename;
    }

    public Date getStartweek() {
        return startweek;
    }

    public void setStartweek(Date startweek) {
        this.startweek = startweek;
    }

    public Date getEndweek() {
        return endweek;
    }

    public void setEndweek(Date endweek) {
        this.endweek = endweek;
    }
}
