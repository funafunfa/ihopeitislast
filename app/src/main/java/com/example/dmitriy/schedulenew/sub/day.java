package com.example.dmitriy.schedulenew.sub;

import java.util.ArrayList;


public class day {
    public  String first, second, third,fourth,fifth,sixth,seventh,day,date;

    public static ArrayList<day> daysA = new ArrayList<>();
    public static ArrayList<day> daysB = new ArrayList<>();

    public day(String day, String first, String second, String third, String fourth, String fifth, String sixth, String seventh, String date){
        this.day = day;
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
        this.sixth = sixth;
        this.seventh = seventh;
        this.date = date;
    }

    public String print(){
        return this.day + this.date + this.first + this.second + this.third + this.fourth + this.fifth + this.sixth + this.seventh;
    }

    public String getDay() {
        return day;
    }

    public String getFirst() {
        return first;
    }

    public String getDate() {
        return date;
    }

    public String getFifth() {
        return fifth;
    }

    public String getFourth() {
        return fourth;
    }

    public String getThird() {
        return third;
    }

    public String getSecond() {
        return second;
    }

    public String getSeventh() {
        return seventh;
    }

    public String getSixth() {
        return sixth;
    }
}
