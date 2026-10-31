package model;

public class Book extends Item{
    private String author;
    private int pages;

    public Book(String id,String title,String author,int pages){
        super(id, title);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public void displayInfo() {
        System.out.println("id = " + getId() + " | " + "title = " + getTitle() + " | " + "status = " + getStatus());
        System.out.println("author: " + author + " pages = " + pages);
    }
}
