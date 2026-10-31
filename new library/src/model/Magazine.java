package model;

public class Magazine extends Item{
    private int issueNumber;

    public Magazine(String id,String title, int issueNumber){
        super(id,title);
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("id = " + getId() + " | " + "title = " + getTitle() + " | " + "status = " + getStatus());
        System.out.println("issue number = " + issueNumber);
    }
}
