package tdd.ytetdd.expense.entity;

import lombok.*;
import tdd.ytetdd.common.AbstractEntity;
import tdd.ytetdd.person.entity.Person;
import tdd.ytetdd.product.entity.Product;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "EXPENSE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense extends AbstractEntity {

    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private Product product;

    public Expense(Long id, LocalDateTime dateTime, Person person, Product product) {
        this(dateTime, person, product);
        this.setId(id);
    }


}
