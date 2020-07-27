package tdd.ytetdd.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tdd.ytetdd.category.Category;
import tdd.ytetdd.common.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.function.Predicate;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractEntity {

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private Category category;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "STOCK")
    private Integer stock;

    public Product(Long id, String name, Category category, BigDecimal value, Integer stock) {
        this(name, category, value, stock);
        this.setId(id);
    }

    public static Predicate<Product> isStockEmpty() {
        return p -> p.getStock() == 0;
    }
}
