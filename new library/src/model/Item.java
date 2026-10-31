package model;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String id;
    private String title;
    private ItemStatus status;

    private static final long serialVersionUID = 1L;

    public Item(String id,String title){
        this.id = id;
        this.title = title;
        this.status = ItemStatus.AVAILABLE;
    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public ItemStatus getStatus(){
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public abstract void displayInfo();

    public String toString(){
        return "id = " + id + " | " + "title = " + title + " | " + "status = " + status;
    }
}
