package de.htwberlin.productService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID id;

    @NotNull
    @Size(max = 100)
    @Getter
    private String name;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal price;


    private String imageLink;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Material material;

    private Color color;

    private String size;
    @Setter @Getter
    private int amount;

    public Product(String name, String description, BigDecimal price, String imageLink, Category category, Material material, Color color, String size, int amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.category = category;
        this.material = material;
        this.color = color;
        this.size = size;
        this.amount = amount;
    }

    public Product() {
    }


    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, description=%s, price=%s, picture=%s]",
                id.toString(), name, description, price, imageLink);
    }
}
