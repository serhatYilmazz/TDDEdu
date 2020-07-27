package tdd.ytetdd.person.entity;

import lombok.*;
import tdd.ytetdd.common.AbstractEntity;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.product.entity.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username", callSuper = false)
public class Person extends AbstractEntity {

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "EMAIL")
    private String eMail;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "MONEY")
    private BigDecimal money;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "NOTE_ID")
//    private Set<Note> note = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private Set<Expense> expenseSet = new HashSet<>();

    public Person(String userName, String eMail, String password, BigDecimal money) {
        this.eMail = eMail;
        this.password = password;
        this.username = userName;
        this.money = money;
    }

    public void addExpense(Collection<Expense> expenses) {
        this.expenseSet.addAll(expenses);
    }

    public void addProducts(Collection<Product> products) {
        for (Product product : products) {
            expenseSet.add(new Expense(LocalDateTime.now(), this, product));
        }
    }

    public BigDecimal getRemainingMoney(Collection<Product> products) {
        BigDecimal cost = products.stream().map(Product::getValue).reduce(new BigDecimal(0), BigDecimal::add);
        return money.subtract(cost);
    }

}
