package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;
    private int id;
    private int is_comp;


    public TodoItem(String category, String title, String desc, String due_date, int is_comp){
    	SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    	this.category=category;
        this.title=title;
        this.desc=desc;
        Date Date = new Date();
        this.current_date= date.format(Date);
        this.due_date=due_date;
        this.is_comp = is_comp;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String toSaveString() {
    	return category+"##"+title+"##"+desc+"##"+due_date+"##"+current_date+"\n";
    }
    
    public String getCate() {
        return category;
    }
    
    public void setCate(String category) {
        this.category = category;
    }
    
    public String getDue() {
        return due_date;
    }
    
    public void setDue(String due_date) {
        this.due_date = due_date;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getComp() {
        return is_comp;
    }
    
    public void setComp(int is_comp) {
        this.is_comp = is_comp;
    }
}
