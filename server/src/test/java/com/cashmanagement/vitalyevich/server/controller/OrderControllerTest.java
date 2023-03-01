package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.Brigade;
import com.cashmanagement.vitalyevich.server.model.BrigadeOrder;
import com.cashmanagement.vitalyevich.server.model.Company;
import com.cashmanagement.vitalyevich.server.model.Order;
import com.cashmanagement.vitalyevich.server.model.OrderStage;
import com.cashmanagement.vitalyevich.server.model.OrderStageId;
import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import com.cashmanagement.vitalyevich.server.model.StorageOrder;
import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.repository.BrigadeOrderRepository;
import com.cashmanagement.vitalyevich.server.repository.BrigadeRepository;
import com.cashmanagement.vitalyevich.server.repository.OrderRepository;
import com.cashmanagement.vitalyevich.server.repository.OrderStageRepository;
import com.cashmanagement.vitalyevich.server.repository.StorageOrderRepository;
import com.cashmanagement.vitalyevich.server.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @MockBean
    private BrigadeOrderRepository brigadeOrderRepository;

    @MockBean
    private BrigadeRepository brigadeRepository;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderStageRepository orderStageRepository;

    @MockBean
    private StorageOrderRepository storageOrderRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link OrderController#orders()}
     */
    @Test
    void testOrders() {
        when(this.orderRepository.findByOrderByIdAsc()).thenReturn((Iterable<Order>) mock(Iterable.class));
        this.orderController.orders();
        verify(this.orderRepository).findByOrderByIdAsc();
    }

    /**
     * Method under test: {@link OrderController#brigadeOrders()}
     */
    @Test
    void testBrigadeOrders() {
        when(this.brigadeOrderRepository.findByOrderByIdAsc()).thenReturn((Iterable<BrigadeOrder>) mock(Iterable.class));
        this.orderController.brigadeOrders();
        verify(this.brigadeOrderRepository).findByOrderByIdAsc();
    }

    /**
     * Method under test: {@link OrderController#storageOrders()}
     */
    @Test
    void testStorageOrders() {
        ArrayList<StorageOrder> storageOrderList = new ArrayList<>();
        when(this.storageOrderRepository.findAll()).thenReturn(storageOrderList);
        Iterable<StorageOrder> actualStorageOrdersResult = this.orderController.storageOrders();
        assertSame(storageOrderList, actualStorageOrdersResult);
        assertTrue(((Collection<StorageOrder>) actualStorageOrdersResult).isEmpty());
        verify(this.storageOrderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderController#orderStages()}
     */
    @Test
    void testOrderStages() {
        ArrayList<OrderStage> orderStageList = new ArrayList<>();
        when(this.orderStageRepository.findAll()).thenReturn(orderStageList);
        Iterable<OrderStage> actualOrderStagesResult = this.orderController.orderStages();
        assertSame(orderStageList, actualOrderStagesResult);
        assertTrue(((Collection<OrderStage>) actualOrderStagesResult).isEmpty());
        verify(this.orderStageRepository).findAll();
    }

    /**
     * Method under test: {@link OrderController#order(Integer)}
     */
    @Test
    void testOrder() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);
        Optional<Order> ofResult = Optional.of(order);
        when(this.orderRepository.findByPlanId((Integer) any())).thenReturn(ofResult);
        Optional<Order> actualOrderResult = this.orderController.order(1);
        assertSame(ofResult, actualOrderResult);
        assertTrue(actualOrderResult.isPresent());
        verify(this.orderRepository).findByPlanId((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#orderById(Integer)}
     */
    @Test
    void testOrderById() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);
        Optional<Order> ofResult = Optional.of(order);
        when(this.orderRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Order> actualOrderByIdResult = this.orderController.orderById(1);
        assertSame(ofResult, actualOrderByIdResult);
        assertTrue(actualOrderByIdResult.isPresent());
        verify(this.orderRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#orderStage(Integer, Integer)}
     */
    @Test
    void testOrderStage() {
        OrderStageId orderStageId = new OrderStageId();
        orderStageId.setOrderId(123);
        orderStageId.setStageId(123);

        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);

        OrderStage orderStage = new OrderStage();
        orderStage.setId(orderStageId);
        orderStage.setOrder(order);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        orderStage.setStageDate(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        Optional<OrderStage> ofResult = Optional.of(orderStage);
        when(this.orderStageRepository.findById((OrderStageId) any())).thenReturn(ofResult);
        Optional<OrderStage> actualOrderStageResult = this.orderController.orderStage(123, 123);
        assertSame(ofResult, actualOrderStageResult);
        assertTrue(actualOrderStageResult.isPresent());
        verify(this.orderStageRepository).findById((OrderStageId) any());
    }

    /**
     * Method under test: {@link OrderController#brigadeOrder(Integer)}
     */
    @Test
    void testBrigadeOrder() {
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);

        Brigade brigade = new Brigade();
        brigade.setActive(true);
        brigade.setBrigadeName("Brigade Name");
        brigade.setCompany(company);
        brigade.setId(1);
        brigade.setUsers(new HashSet<>());

        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        BrigadeOrder brigadeOrder = new BrigadeOrder();
        brigadeOrder.setBrigade(brigade);
        brigadeOrder.setId(1);
        brigadeOrder.setOrder(order);
        brigadeOrder.setOrderDate(LocalDate.ofEpochDay(1L));
        brigadeOrder.setUser(user2);
        Optional<BrigadeOrder> ofResult = Optional.of(brigadeOrder);
        when(this.brigadeOrderRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<BrigadeOrder> actualBrigadeOrderResult = this.orderController.brigadeOrder(1);
        assertSame(ofResult, actualBrigadeOrderResult);
        assertTrue(actualBrigadeOrderResult.isPresent());
        verify(this.brigadeOrderRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#storageOrder(Integer)}
     */
    @Test
    void testStorageOrder() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        StorageOrder storageOrder = new StorageOrder();
        storageOrder.setId(1);
        storageOrder.setOrder(order);
        storageOrder.setOrderDate(LocalDate.ofEpochDay(1L));
        storageOrder.setUser(user2);
        Optional<StorageOrder> ofResult = Optional.of(storageOrder);
        when(this.storageOrderRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<StorageOrder> actualStorageOrderResult = this.orderController.storageOrder(1);
        assertSame(ofResult, actualStorageOrderResult);
        assertTrue(actualStorageOrderResult.isPresent());
        verify(this.storageOrderRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#updateOrder(Order, PlanAtm, User)}
     */
    @Test
    void testUpdateOrder() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Atm atm1 = new Atm();
        atm1.setAddress("42 Main St");
        atm1.setAtmState("Atm State");
        atm1.setAtmUid("1234");
        atm1.setCashState("Cash State");
        atm1.setCassettes(new HashSet<>());
        atm1.setCities(new HashSet<>());
        atm1.setCompanies(new HashSet<>());
        atm1.setHomeNum(10);
        atm1.setId(1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        PlanAtm planAtm1 = new PlanAtm();
        planAtm1.setAtm(atm1);
        planAtm1.setCassettes(new HashSet<>());
        planAtm1.setId(1);
        planAtm1.setPlanMethod("Plan Method");
        planAtm1.setPlanPeriod(1);
        planAtm1.setStatus("Status");
        planAtm1.setUser(user2);

        User user3 = new User();
        user3.setCompanies(new HashSet<>());
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(1);
        user3.setLastName("Doe");
        user3.setPhone("4105551212");
        user3.setRoles(new HashSet<>());

        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStages(new HashSet<>());
        order1.setPlan(planAtm1);
        order1.setStage("Stage");
        order1.setStatus("Status");
        order1.setUser(user3);

        Atm atm2 = new Atm();
        atm2.setAddress("42 Main St");
        atm2.setAtmState("Atm State");
        atm2.setAtmUid("1234");
        atm2.setCashState("Cash State");
        atm2.setCassettes(new HashSet<>());
        atm2.setCities(new HashSet<>());
        atm2.setCompanies(new HashSet<>());
        atm2.setHomeNum(10);
        atm2.setId(1);

        User user4 = new User();
        user4.setCompanies(new HashSet<>());
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(1);
        user4.setLastName("Doe");
        user4.setPhone("4105551212");
        user4.setRoles(new HashSet<>());

        PlanAtm planAtm2 = new PlanAtm();
        planAtm2.setAtm(atm2);
        planAtm2.setCassettes(new HashSet<>());
        planAtm2.setId(1);
        planAtm2.setPlanMethod("Plan Method");
        planAtm2.setPlanPeriod(1);
        planAtm2.setStatus("Status");
        planAtm2.setUser(user4);

        User user5 = new User();
        user5.setCompanies(new HashSet<>());
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(1);
        user5.setLastName("Doe");
        user5.setPhone("4105551212");
        user5.setRoles(new HashSet<>());
        assertSame(order, this.orderController.updateOrder(order1, planAtm2, user5));
        verify(this.orderRepository).save((Order) any());
    }

    /**
     * Method under test: {@link OrderController#createOrder(Order, PlanAtm, User)}
     */
    @Test
    void testCreateOrder() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Atm atm1 = new Atm();
        atm1.setAddress("42 Main St");
        atm1.setAtmState("Atm State");
        atm1.setAtmUid("1234");
        atm1.setCashState("Cash State");
        atm1.setCassettes(new HashSet<>());
        atm1.setCities(new HashSet<>());
        atm1.setCompanies(new HashSet<>());
        atm1.setHomeNum(10);
        atm1.setId(1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        PlanAtm planAtm1 = new PlanAtm();
        planAtm1.setAtm(atm1);
        planAtm1.setCassettes(new HashSet<>());
        planAtm1.setId(1);
        planAtm1.setPlanMethod("Plan Method");
        planAtm1.setPlanPeriod(1);
        planAtm1.setStatus("Status");
        planAtm1.setUser(user2);

        User user3 = new User();
        user3.setCompanies(new HashSet<>());
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(1);
        user3.setLastName("Doe");
        user3.setPhone("4105551212");
        user3.setRoles(new HashSet<>());

        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStages(new HashSet<>());
        order1.setPlan(planAtm1);
        order1.setStage("Stage");
        order1.setStatus("Status");
        order1.setUser(user3);

        Atm atm2 = new Atm();
        atm2.setAddress("42 Main St");
        atm2.setAtmState("Atm State");
        atm2.setAtmUid("1234");
        atm2.setCashState("Cash State");
        atm2.setCassettes(new HashSet<>());
        atm2.setCities(new HashSet<>());
        atm2.setCompanies(new HashSet<>());
        atm2.setHomeNum(10);
        atm2.setId(1);

        User user4 = new User();
        user4.setCompanies(new HashSet<>());
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(1);
        user4.setLastName("Doe");
        user4.setPhone("4105551212");
        user4.setRoles(new HashSet<>());

        PlanAtm planAtm2 = new PlanAtm();
        planAtm2.setAtm(atm2);
        planAtm2.setCassettes(new HashSet<>());
        planAtm2.setId(1);
        planAtm2.setPlanMethod("Plan Method");
        planAtm2.setPlanPeriod(1);
        planAtm2.setStatus("Status");
        planAtm2.setUser(user4);

        User user5 = new User();
        user5.setCompanies(new HashSet<>());
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(1);
        user5.setLastName("Doe");
        user5.setPhone("4105551212");
        user5.setRoles(new HashSet<>());
        assertSame(order, this.orderController.createOrder(order1, planAtm2, user5));
        verify(this.orderRepository).save((Order) any());
    }

    /**
     * Method under test: {@link OrderController#deleteOrder(Integer)}
     */
    @Test
    void testDeleteOrder() {
        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        StorageOrder storageOrder = new StorageOrder();
        storageOrder.setId(1);
        storageOrder.setOrder(order);
        storageOrder.setOrderDate(LocalDate.ofEpochDay(1L));
        storageOrder.setUser(user2);
        doNothing().when(this.storageOrderRepository).deleteById((Integer) any());
        when(this.storageOrderRepository.findStorageOrderByOrderId((Integer) any())).thenReturn(storageOrder);
        doNothing().when(this.orderStageRepository)
                .deleteById((com.cashmanagement.vitalyevich.server.model.OrderStageId) any());
        doNothing().when(this.orderRepository).deleteById((Integer) any());
        this.orderController.deleteOrder(1);
        verify(this.storageOrderRepository).findStorageOrderByOrderId((Integer) any());
        verify(this.storageOrderRepository).deleteById((Integer) any());
        verify(this.orderStageRepository, atLeast(1))
                .deleteById((com.cashmanagement.vitalyevich.server.model.OrderStageId) any());
        verify(this.orderRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#deleteByOrder(Integer)}
     */
    @Test
    void testDeleteByOrder() {
        doNothing().when(this.orderRepository).deleteById((Integer) any());
        this.orderController.deleteByOrder(1);
        verify(this.orderRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link OrderController#updateBrigadeOrder(BrigadeOrder, Integer, Integer, Integer)}
     */
    @Test
    void testUpdateBrigadeOrder() {
        Company company = new Company();
        company.setAddress("42 Main St");
        company.setCities(new HashSet<>());
        company.setCompanyName("Company Name");
        company.setHomeNum(10);
        company.setId(1);

        Brigade brigade = new Brigade();
        brigade.setActive(true);
        brigade.setBrigadeName("Brigade Name");
        brigade.setCompany(company);
        brigade.setId(1);
        brigade.setUsers(new HashSet<>());
        Optional<Brigade> ofResult = Optional.of(brigade);
        when(this.brigadeRepository.findById((Integer) any())).thenReturn(ofResult);

        Company company1 = new Company();
        company1.setAddress("42 Main St");
        company1.setCities(new HashSet<>());
        company1.setCompanyName("Company Name");
        company1.setHomeNum(10);
        company1.setId(1);

        Brigade brigade1 = new Brigade();
        brigade1.setActive(true);
        brigade1.setBrigadeName("Brigade Name");
        brigade1.setCompany(company1);
        brigade1.setId(1);
        brigade1.setUsers(new HashSet<>());

        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        BrigadeOrder brigadeOrder = new BrigadeOrder();
        brigadeOrder.setBrigade(brigade1);
        brigadeOrder.setId(1);
        brigadeOrder.setOrder(order);
        brigadeOrder.setOrderDate(LocalDate.ofEpochDay(1L));
        brigadeOrder.setUser(user2);
        when(this.brigadeOrderRepository.save((BrigadeOrder) any())).thenReturn(brigadeOrder);

        Company company2 = new Company();
        company2.setAddress("42 Main St");
        company2.setCities(new HashSet<>());
        company2.setCompanyName("Company Name");
        company2.setHomeNum(10);
        company2.setId(1);

        Brigade brigade2 = new Brigade();
        brigade2.setActive(true);
        brigade2.setBrigadeName("Brigade Name");
        brigade2.setCompany(company2);
        brigade2.setId(1);
        brigade2.setUsers(new HashSet<>());

        Atm atm1 = new Atm();
        atm1.setAddress("42 Main St");
        atm1.setAtmState("Atm State");
        atm1.setAtmUid("1234");
        atm1.setCashState("Cash State");
        atm1.setCassettes(new HashSet<>());
        atm1.setCities(new HashSet<>());
        atm1.setCompanies(new HashSet<>());
        atm1.setHomeNum(10);
        atm1.setId(1);

        User user3 = new User();
        user3.setCompanies(new HashSet<>());
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(1);
        user3.setLastName("Doe");
        user3.setPhone("4105551212");
        user3.setRoles(new HashSet<>());

        PlanAtm planAtm1 = new PlanAtm();
        planAtm1.setAtm(atm1);
        planAtm1.setCassettes(new HashSet<>());
        planAtm1.setId(1);
        planAtm1.setPlanMethod("Plan Method");
        planAtm1.setPlanPeriod(1);
        planAtm1.setStatus("Status");
        planAtm1.setUser(user3);

        User user4 = new User();
        user4.setCompanies(new HashSet<>());
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(1);
        user4.setLastName("Doe");
        user4.setPhone("4105551212");
        user4.setRoles(new HashSet<>());

        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStages(new HashSet<>());
        order1.setPlan(planAtm1);
        order1.setStage("Stage");
        order1.setStatus("Status");
        order1.setUser(user4);

        User user5 = new User();
        user5.setCompanies(new HashSet<>());
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(1);
        user5.setLastName("Doe");
        user5.setPhone("4105551212");
        user5.setRoles(new HashSet<>());

        BrigadeOrder brigadeOrder1 = new BrigadeOrder();
        brigadeOrder1.setBrigade(brigade2);
        brigadeOrder1.setId(1);
        brigadeOrder1.setOrder(order1);
        brigadeOrder1.setOrderDate(LocalDate.ofEpochDay(1L));
        brigadeOrder1.setUser(user5);
        assertSame(brigadeOrder, this.orderController.updateBrigadeOrder(brigadeOrder1, 123, 123, 123));
        verify(this.brigadeRepository).findById((Integer) any());
        verify(this.brigadeOrderRepository).save((BrigadeOrder) any());
    }

    /**
     * Method under test: {@link OrderController#updateStorageOrder(StorageOrder, Integer, Integer)}
     */
    @Test
    void testUpdateStorageOrder() {
        User user = new User();
        user.setCompanies(new HashSet<>());
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(1);
        user.setLastName("Doe");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());
        when(this.userRepository.findUserById((Integer) any())).thenReturn(user);

        Atm atm = new Atm();
        atm.setAddress("42 Main St");
        atm.setAtmState("Atm State");
        atm.setAtmUid("1234");
        atm.setCashState("Cash State");
        atm.setCassettes(new HashSet<>());
        atm.setCities(new HashSet<>());
        atm.setCompanies(new HashSet<>());
        atm.setHomeNum(10);
        atm.setId(1);

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        PlanAtm planAtm = new PlanAtm();
        planAtm.setAtm(atm);
        planAtm.setCassettes(new HashSet<>());
        planAtm.setId(1);
        planAtm.setPlanMethod("Plan Method");
        planAtm.setPlanPeriod(1);
        planAtm.setStatus("Status");
        planAtm.setUser(user1);

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());

        Order order = new Order();
        order.setId(1);
        order.setOrderDate(LocalDate.ofEpochDay(1L));
        order.setOrderStages(new HashSet<>());
        order.setPlan(planAtm);
        order.setStage("Stage");
        order.setStatus("Status");
        order.setUser(user2);

        User user3 = new User();
        user3.setCompanies(new HashSet<>());
        user3.setEmail("jane.doe@example.org");
        user3.setFirstName("Jane");
        user3.setId(1);
        user3.setLastName("Doe");
        user3.setPhone("4105551212");
        user3.setRoles(new HashSet<>());

        StorageOrder storageOrder = new StorageOrder();
        storageOrder.setId(1);
        storageOrder.setOrder(order);
        storageOrder.setOrderDate(LocalDate.ofEpochDay(1L));
        storageOrder.setUser(user3);
        when(this.storageOrderRepository.save((StorageOrder) any())).thenReturn(storageOrder);

        Atm atm1 = new Atm();
        atm1.setAddress("42 Main St");
        atm1.setAtmState("Atm State");
        atm1.setAtmUid("1234");
        atm1.setCashState("Cash State");
        atm1.setCassettes(new HashSet<>());
        atm1.setCities(new HashSet<>());
        atm1.setCompanies(new HashSet<>());
        atm1.setHomeNum(10);
        atm1.setId(1);

        User user4 = new User();
        user4.setCompanies(new HashSet<>());
        user4.setEmail("jane.doe@example.org");
        user4.setFirstName("Jane");
        user4.setId(1);
        user4.setLastName("Doe");
        user4.setPhone("4105551212");
        user4.setRoles(new HashSet<>());

        PlanAtm planAtm1 = new PlanAtm();
        planAtm1.setAtm(atm1);
        planAtm1.setCassettes(new HashSet<>());
        planAtm1.setId(1);
        planAtm1.setPlanMethod("Plan Method");
        planAtm1.setPlanPeriod(1);
        planAtm1.setStatus("Status");
        planAtm1.setUser(user4);

        User user5 = new User();
        user5.setCompanies(new HashSet<>());
        user5.setEmail("jane.doe@example.org");
        user5.setFirstName("Jane");
        user5.setId(1);
        user5.setLastName("Doe");
        user5.setPhone("4105551212");
        user5.setRoles(new HashSet<>());

        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderDate(LocalDate.ofEpochDay(1L));
        order1.setOrderStages(new HashSet<>());
        order1.setPlan(planAtm1);
        order1.setStage("Stage");
        order1.setStatus("Status");
        order1.setUser(user5);
        when(this.orderRepository.findOrderById((Integer) any())).thenReturn(order1);

        Atm atm2 = new Atm();
        atm2.setAddress("42 Main St");
        atm2.setAtmState("Atm State");
        atm2.setAtmUid("1234");
        atm2.setCashState("Cash State");
        atm2.setCassettes(new HashSet<>());
        atm2.setCities(new HashSet<>());
        atm2.setCompanies(new HashSet<>());
        atm2.setHomeNum(10);
        atm2.setId(1);

        User user6 = new User();
        user6.setCompanies(new HashSet<>());
        user6.setEmail("jane.doe@example.org");
        user6.setFirstName("Jane");
        user6.setId(1);
        user6.setLastName("Doe");
        user6.setPhone("4105551212");
        user6.setRoles(new HashSet<>());

        PlanAtm planAtm2 = new PlanAtm();
        planAtm2.setAtm(atm2);
        planAtm2.setCassettes(new HashSet<>());
        planAtm2.setId(1);
        planAtm2.setPlanMethod("Plan Method");
        planAtm2.setPlanPeriod(1);
        planAtm2.setStatus("Status");
        planAtm2.setUser(user6);

        User user7 = new User();
        user7.setCompanies(new HashSet<>());
        user7.setEmail("jane.doe@example.org");
        user7.setFirstName("Jane");
        user7.setId(1);
        user7.setLastName("Doe");
        user7.setPhone("4105551212");
        user7.setRoles(new HashSet<>());

        Order order2 = new Order();
        order2.setId(1);
        order2.setOrderDate(LocalDate.ofEpochDay(1L));
        order2.setOrderStages(new HashSet<>());
        order2.setPlan(planAtm2);
        order2.setStage("Stage");
        order2.setStatus("Status");
        order2.setUser(user7);

        User user8 = new User();
        user8.setCompanies(new HashSet<>());
        user8.setEmail("jane.doe@example.org");
        user8.setFirstName("Jane");
        user8.setId(1);
        user8.setLastName("Doe");
        user8.setPhone("4105551212");
        user8.setRoles(new HashSet<>());

        StorageOrder storageOrder1 = new StorageOrder();
        storageOrder1.setId(1);
        storageOrder1.setOrder(order2);
        storageOrder1.setOrderDate(LocalDate.ofEpochDay(1L));
        storageOrder1.setUser(user8);
        assertSame(storageOrder, this.orderController.updateStorageOrder(storageOrder1, 123, 123));
        verify(this.userRepository).findUserById((Integer) any());
        verify(this.storageOrderRepository).save((StorageOrder) any());
        verify(this.orderRepository).findOrderById((Integer) any());
    }
}

