package edu.sergradcapstone.groupseven.brewday.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@Entity
@Table(name = "recipe")
public class Recipe  {

    protected Recipe(){

    }

    public Recipe(String recipename,double batchSize, double ABV, String instructions, double IBU, Time boilTime, String brewType,String email,String access) {
        this.recipename = recipename;
        this.batchSize = batchSize;
        this.ABV = ABV;
        this.instructions = instructions;
        this.IBU = IBU;
        this.boilTime = boilTime;
        this.brewType = brewType;
        this.email = email;
        this.access = access;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TIMESTAMP)
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    @Temporal(TIMESTAMP)
    @Column(name="last_modified_date", nullable = false)
    private Date lastModifiedDate;

    @Column(name="recipe_name")
    private String recipename;

    @Column(name="batch_size")
    private double batchSize;

    @Column(name="ABV")
    private double ABV;

    @Column(name="instructions")
    private String instructions;

    @Column(name="IBU")
    @NotNull
    private double IBU;

    @Column(name="boil_time")
    private Time boilTime;

    @Column(name="brew_type")
    private String brewType;

    @Column(name="email")
    private String email;

    @Column(name="acess_level")
    private String access;

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

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(double batchSize) {
        this.batchSize = batchSize;
    }

    public double getABV() {
        return ABV;
    }
    public void setABV(double aBV) {
        ABV = aBV;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getIBU() {
        return IBU;
    }

    public void setIBU(double iBU) {
        IBU = iBU;
    }

    public Time getBoilTime() {
        return boilTime;
    }

    public void setBoilTime(Time boilTime) {
        this.boilTime = boilTime;
    }

    public String getBrewType() {
        return brewType;
    }

    public void setBrewType(String brewType) {
        this.brewType = brewType;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getRecipename() {
        return recipename;
    }

    public void setRecipename(String recipename) {
        this.recipename = recipename;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
