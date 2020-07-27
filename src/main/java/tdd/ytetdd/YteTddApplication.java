package tdd.ytetdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import tdd.ytetdd.category.Category;
import tdd.ytetdd.expense.repository.ExpenseRepository;
import tdd.ytetdd.person.repository.PersonRepository;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.product.repository.ProductRepository;
import tdd.ytetdd.person.entity.Person;
import tdd.ytetdd.product.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class YteTddApplication {

	public static void main(String[] args) {
		ApplicationContext run = SpringApplication.run(YteTddApplication.class, args);
		PersonRepository personRepository = run.getBean(PersonRepository.class);
		ProductRepository productRepository = run.getBean(ProductRepository.class);
		ExpenseRepository expenseRepository = run.getBean(ExpenseRepository.class);

		Product product1 = new Product(1L, "ESP Les Paul", Category.ELECTRONIC, new BigDecimal(1000), 20);
		Product product2 = new Product(2L, "Hat", Category.WATCH_ACCESSORY, new BigDecimal(25), 120);
		productRepository.saveAll(Arrays.asList(product1, product2));

		Person person1 = new Person("avenged", "as@seven.com", "sevenfold", new BigDecimal(15000));
		Expense expense1 = new Expense(1L, LocalDateTime.now(), person1, product1);
		Expense expense2 = new Expense(1L, LocalDateTime.now(), person1, product2);


//		Person person2 = new Person("machine", "mh@head.com", "head");
//		Person person3 = new Person("iron", "im@maiden.com", "maiden");

		personRepository.saveAll(Arrays.asList(person1));
		expenseRepository.saveAll(Arrays.asList(expense1, expense2));
	}

}
