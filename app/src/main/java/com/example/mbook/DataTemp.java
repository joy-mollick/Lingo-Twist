package com.example.mbook;

/// It is one kind of structure of data
public class DataTemp {

    private int id;
    private String name;
    private String day;
    private String Fb;
    private String Gmail;
    private String Identity;
    private String whatsapp;
    private String Instragam;
    /// It is constructor of this structure (class)
    public DataTemp(String n, String d,String f,String g,String i,String inst,String whp ){
        name = n;
        day = d;
        Fb=f;
        Gmail=g;
        Identity=i;
        whatsapp=whp;
        Instragam=inst;
    }

    /// rest of all are setter and getter methods
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFb(String Fb) { this.Fb = Fb; }

    public String getFb() {
        return Fb;
    }

    public void setGm(String Gmail) {this.Gmail=Gmail;}
    public String getGm() {return Gmail;}

    public void setIdentity(String Identity) {this.Identity=Identity;}
    public String getIdentity() {return Identity;}

    public void setInstragam(String Instragam) {this.Instragam=Instragam;}
    public String getInstragam() {return Instragam;}

    public void setwhatsapp(String whatsapp) {this.whatsapp=whatsapp;}
    public String getwhatsapp() {return whatsapp;}
}