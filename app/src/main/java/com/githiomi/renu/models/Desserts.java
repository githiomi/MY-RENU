package com.githiomi.renu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Desserts {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("categoryid")
    @Expose
    private Integer categoryid;

    /**
     * No args constructor for use in serialization
     *
     */
    public Desserts() {
    }

    /**
     *
     * @param price
     * @param name
     * @param description
     * @param id
     * @param type
     * @param categoryid
     */
    public Desserts(Integer id, String name, String description, String type, String price, Integer categoryid) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
        this.categoryid = categoryid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

}