package edu.birzeit.courseproject.models;

public class Order {
    private int id;
    private String pizzaName;
    private String userEmail;
    private long date;

    public Order(int id, String pizzaName, String userEmail, long date) {
        this.id = id;
        this.pizzaName = pizzaName;
        this.userEmail = userEmail;
        this.date = date;
    }

    public Order(String pizzaName, String userEmail, long date) {
        this.pizzaName = pizzaName;
        this.userEmail = userEmail;
        this.date = date;
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

    public long getDate() {
        return date;
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

    public void setDate(long date) {
        this.date = date;
    }
}
