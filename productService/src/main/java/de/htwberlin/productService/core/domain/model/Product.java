package de.htwberlin.productService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal oldPrice;

    private String imageLink;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Material material;

    private Color color;

    private String size;

    private boolean isReducedPrice;

    private int amount;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price, BigDecimal oldPrice, String imageLink, Category category, Material material, Color color , String size, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.oldPrice = oldPrice;
        this.imageLink = imageLink;
        this.category = category;
        this.material = material;
        this.color = color;
        this.size = size;
        this.isReducedPrice = oldPrice != price;
        this.amount = amount;
    }

    public Product(String name, String description, BigDecimal price, String imageLink, Category category, Material material, Color color , String size, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.oldPrice = price;
        this.imageLink = imageLink;
        this.category = category;
        this.material = material;
        this.color = color;
        this.size = size;
        this.price = price;
        this.oldPrice = this.price;
        this.isReducedPrice = false;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getPicture() {
        return imageLink;
    }

    public void setPicture(String picture) {
        this.imageLink = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isReducedPrice() {
        return isReducedPrice;
    }

    public void setReducedPrice(boolean isReducedPrice) {
        this.isReducedPrice = isReducedPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, description=%s, price=%s, picture=%s]",
                id.toString(), name, description, price, imageLink);
    }
}
