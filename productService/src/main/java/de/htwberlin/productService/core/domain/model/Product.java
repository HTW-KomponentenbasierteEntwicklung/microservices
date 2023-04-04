package de.htwberlin.productService.core.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @NotNull
    @Size(max = 100)
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

    @Override
    public String toString() {
        return String.format("Product[id=%s, name=%s, description=%s, price=%s, picture=%s]",
                productId.toString(), name, description, price, imageLink);
    }
}
