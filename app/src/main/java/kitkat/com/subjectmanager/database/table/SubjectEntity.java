package kitkat.com.subjectmanager.database.table;

import java.util.Date;

public class SubjectEntity {
    int weekday;
    String coursenum;
    String coursename;
    Date startweek;
    Date  endweek;
    String room;
    int year1;
    int month1;
    int day1;
    int lesson;
    int year;
    int month;
    int day;
    String WEEK;

    public void setCoursenum(String coursenum) {
        this.coursenum = coursenum;
    }

    public String getCoursenum() {
        return coursenum;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getYear1() {
        return year1;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public int getMonth1() {
        return month1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay1() {
        return day1;
    }
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
