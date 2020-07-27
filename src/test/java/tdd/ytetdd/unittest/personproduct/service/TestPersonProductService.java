package tdd.ytetdd.unittest.personproduct.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tdd.ytetdd.configuration.SecurityContextFacade;
import tdd.ytetdd.expense.entity.Expense;
import tdd.ytetdd.expense.repository.ExpenseRepository;
import tdd.ytetdd.person.entity.Person;
import tdd.ytetdd.person.repository.PersonRepository;
import tdd.ytetdd.expense.service.PersonProductService;
import tdd.ytetdd.expense.service.PersonProductServiceImpl;
import tdd.ytetdd.product.entity.Product;
import tdd.ytetdd.product.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static tdd.ytetdd.unittest.personproduct.service.EntityBuilder.*;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonProductService {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SecurityContextFacade securityContextFacade;

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private PersonProductService personProductService = new PersonProductServiceImpl(personRepository, productRepository, securityContextFacade, expenseRepository);

    @Test
    public void testPersonCanAddProducts() {
        List<Product> productList = createProduct();
        Person person = EntityBuilder.createPerson();
        List<Expense> expenses = createExpenses(person, productList);

        Assert.assertNotNull(productList);
        Assert.assertNotNull(person);
        assertEquals(0, person.getExpenseSet().size());

        person.addExpense(expenses);
        assertEquals(2, person.getExpenseSet().size());

    }

    @Test
    public void testPersonBuysProduct() {
        List<Product> productList = createProduct();
        Person person = EntityBuilder.createPerson();
        List<Expense> expenses = createExpenses(person, productList);

        Mockito.when(securityContextFacade.getAuthentication()).thenReturn(person.getUsername());
        Mockito.when(productRepository.findByIdIn(Arrays.asList(1L, 2L))).thenReturn(productList);
        Mockito.when(personRepository.findByUsername(person.getUsername())).thenReturn(person);
        //Mockito.when(expenseRepository.saveAll(expenses)).thenReturn();

        personProductService.buy(Arrays.asList(1L, 2L));

        //Assert.assertEquals(2, person.getExpenseSet().size());
        Assert.assertEquals(new BigDecimal(14000), person.getMoney());
        for (Product product : productList) {
            if (product.getId().equals(1L)) {
                Assert.assertEquals(Integer.valueOf(19), product.getStock());
            } else if (product.getId().equals(2L)) {
                Assert.assertEquals(Integer.valueOf(119), product.getStock());
            }
        }

        Mockito.verify(securityContextFacade).getAuthentication();
        Mockito.verify(productRepository).findByIdIn(Arrays.asList(1L, 2L));
        Mockito.verify(personRepository).findByUsername(person.getUsername());
        Mockito.verify(personRepository).save(person);
        Mockito.verify(productRepository).saveAll(productList);

        Mockito.verifyNoMoreInteractions(securityContextFacade, personRepository, productRepository);
    }

    @Test
    public void testPersonCannotBuysProduct() {
        List<Product> productList = EntityBuilder.createProductWithZeroStock();
        Person person = EntityBuilder.createPerson();

        Mockito.when(securityContextFacade.getAuthentication()).thenReturn(person.getUsername());
        Mockito.when(productRepository.findByIdIn(Arrays.asList(1L, 2L))).thenReturn(productList);
        Mockito.when(personRepository.findByUsername(person.getUsername())).thenReturn(person);

        personProductService.buy(Arrays.asList(1L, 2L));

        Assert.assertEquals(0, person.getExpenseSet().size());

        Mockito.verify(securityContextFacade).getAuthentication();
        Mockito.verify(productRepository).findByIdIn(Arrays.asList(1L, 2L));
        Mockito.verify(personRepository).findByUsername(person.getUsername());
        Mockito.verify(personRepository).save(person);

        Mockito.verifyNoMoreInteractions(securityContextFacade, personRepository, productRepository);
    }

    @Test
    public void getRemainingMoney() {
        List<Product> productList = EntityBuilder.createProductWithZeroStock();
        Person person = EntityBuilder.createPerson();
        BigDecimal remainingMoney = person.getRemainingMoney(productList);

        Assert.assertEquals(new BigDecimal(13975), remainingMoney);
    }

    @Test
    public void testIsStock() {
        Product product = createProduct().get(0);
        boolean result = Product.isStockEmpty().test(product);

        assertFalse(result);

        Product productWithZeroStock = createProductWithZeroStock().get(0);
        boolean resultForZero = Product.isStockEmpty().test(productWithZeroStock);

        assertTrue(resultForZero);
    }

    @Test
    public void testFavouriteProductInit() {

        personProductService.buy(Arrays.asList(1L, 2L));
    }

}
