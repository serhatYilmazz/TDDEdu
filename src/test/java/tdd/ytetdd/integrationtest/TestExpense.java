package tdd.ytetdd.integrationtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.expense.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DirtiesContext
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TestExpense {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Test
    public void test() {
        List<Expense> expenses = expenseRepository.groupByDateSumOfExpense(LocalDate.now(), 1L);
    }
}
