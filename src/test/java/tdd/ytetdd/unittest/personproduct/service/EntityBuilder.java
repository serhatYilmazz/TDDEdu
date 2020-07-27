package tdd.ytetdd.unittest.personproduct.service;

import tdd.ytetdd.category.Category;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.person.entity.Person;
import tdd.ytetdd.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityBuilder {

    static LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 1, 0, 0);

    public static Person createPerson() {
        return new Person("avenged", "as@seven.com", "1234", new BigDecimal(15000));
    }

    public static List<Product> createProduct() {
        Product product1 = new Product(1L, "ESP Les Paul", Category.ELECTRONIC, new BigDecimal(1000), 20);
        Product product2 = new Product(2L, "Hat", Category.WATCH_ACCESSORY, new BigDecimal(25), 120);

        return Arrays.asList(product1, product2);
    }

    public static List<Product> createProductWithMoney(BigDecimal money) {
        Product product1 = new Product(1L, "ESP Les Paul", Category.ELECTRONIC, money, 20);
        Product product2 = new Product(2L, "Hat", Category.WATCH_ACCESSORY, money, 120);

        return Arrays.asList(product1, product2);
    }

    public static List<Expense> createExpenses(Person person, List<Product> productList) {
        List<Expense> expenses = new ArrayList<>();
        long id = 1L;
        for (Product product : productList) {
            Expense e1 = new Expense(id++, localDateTime, person, product);
            expenses.add(e1);
        }
        return expenses;
    }

    public static List<Expense> createExpenseListWithoutId(Person person, List<Product> productList) {
        List<Expense> expenses = new ArrayList<>();
        for (Product product : productList) {
            Expense e1 = new Expense(localDateTime, person, product);
            expenses.add(e1);
        }
        return expenses;
    }

    public static List<Product> createProductWithZeroStock() {
        Product product1 = new Product(1L, "ESP Les Paul", Category.ELECTRONIC, new BigDecimal(1000), 0);
        Product product2 = new Product(2L, "Hat", Category.WATCH_ACCESSORY, new BigDecimal(25), 0);

        return Arrays.asList(product1, product2);
    }
}
