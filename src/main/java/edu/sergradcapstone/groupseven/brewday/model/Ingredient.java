package edu.sergradcapstone.groupseven.brewday.model;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name="ingredientList")
public class Ingredient {

    protected Ingredient(){
    }

    public Ingredient(String type, double quantity, String name,String username) {
        this.type = type;
        this.quantity = quantity;
        this.name = name;
        this.username = username;
    }
    //foreign key
    //user id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TIMESTAMP)
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    @Temporal(TIMESTAMP)
    @Column(name="last_modified_date", nullable = false)
    private Date lastModifiedDate;


    @Column(name="name")
    private String name;

    @Column(name="Ingredientstype")
    private String type;

    @Column(name="quantity")
    private double quantity;

    @Column(name="username")
    private String username;

    @PrePersist
    protected void onCreate() {
        lastModifiedDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModifiedDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}