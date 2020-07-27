package tdd.ytetdd.expense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdd.ytetdd.configuration.SecurityContextFacade;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.expense.repository.ExpenseRepository;
import tdd.ytetdd.person.entity.Person;
import tdd.ytetdd.person.repository.PersonRepository;
import tdd.ytetdd.product.entity.Product;
import tdd.ytetdd.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class PersonProductServiceImpl implements PersonProductService {

    private PersonRepository personRepository;

    private ProductRepository productRepository;

    private SecurityContextFacade securityContextFacade;

    private ExpenseRepository expenseRepository;

    @Autowired
    public PersonProductServiceImpl(PersonRepository personRepository, ProductRepository productRepository, SecurityContextFacade securityContextFacade,
                                    ExpenseRepository expenseRepository) {
        this.personRepository = personRepository;
        this.productRepository = productRepository;
        this.securityContextFacade = securityContextFacade;
        this.expenseRepository = expenseRepository;
    }

    @Override
    @Transactional
    public void buy(Collection<Long> productIds) {
        String username = securityContextFacade.getAuthentication();
        List<Product> productListDB = productRepository.findByIdIn(productIds);
        Person userDB = personRepository.findByUsername(username);
        BigDecimal remainingMoney = userDB.getRemainingMoney(productListDB);
        if (productListDB.stream().noneMatch(Product.isStockEmpty())
            && remainingMoney.compareTo(new BigDecimal(0)) > 0) {
            createExpenses(userDB, productListDB);
            setUserRemainingMoney(userDB, remainingMoney);
            decreaseStockNumber(productListDB);
        }
    }

    private void decreaseStockNumber(List<Product> productListDB) {
        productListDB.forEach(p -> p.setStock(p.getStock() - 1));
        productRepository.saveAll(productListDB);
    }

    private void setUserRemainingMoney(Person userDB, BigDecimal remainingMoney) {
        userDB.setMoney(remainingMoney);
        personRepository.save(userDB);
    }

    private void createExpenses(Person userDB, List<Product> productListDB) {
        List<Expense> expenseList = new ArrayList<>();
        for (Product product : productListDB) {
            expenseList.add(new Expense(LocalDateTime.now(), userDB, product));
        }
        List<Expense> expenses = expenseRepository.saveAll(expenseList);
        //userDB.addExpense(new HashSet<>(expenses));
    }

}
