package tdd.ytetdd.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdd.ytetdd.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
