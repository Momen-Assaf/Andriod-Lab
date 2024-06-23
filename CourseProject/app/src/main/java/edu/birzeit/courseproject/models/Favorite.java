package edu.birzeit.courseproject.models;

public class Favorite {
    private int id;
    private String pizzaName;
    private String userEmail;
    private long dateAdded;

    public Favorite(int id, String pizzaName, String userEmail, long dateAdded) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.userEmail = userEmail;
        this.dateAdded = dateAdded;
    }

    public Favorite(String pizzaName, String userEmail, long dateAdded) {
        this.pizzaName = pizzaName;
        this.userEmail = userEmail;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }
}
