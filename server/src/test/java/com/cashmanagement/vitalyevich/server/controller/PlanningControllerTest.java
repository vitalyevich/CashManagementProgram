package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.PlanAtm;
import com.cashmanagement.vitalyevich.server.model.User;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PlanningController.class})
@ExtendWith(SpringExtension.class)
class PlanningControllerTest {
    @MockBean
    private PlanAtmRepository planAtmRepository;

    @Autowired
    private PlanningController planningController;

    /**
     * Method under test: {@link PlanningController#plans()}
     */
    @Test
    void testPlans() {
        when(this.planAtmRepository.findByOrderByIdAscCassettesAsc()).thenReturn((Iterable<PlanAtm>) mock(Iterable.class));
        this.planningController.plans();
        verify(this.planAtmRepository).findByOrderByIdAscCassettesAsc();
    }

    /**
     * Method under test: {@link PlanningController#plan(Integer)}
     */
    @Test
    void testPlan() {
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
        Optional<PlanAtm> ofResult = Optional.of(planAtm);
        when(this.planAtmRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<PlanAtm> actualPlanResult = this.planningController.plan(1);
        assertSame(ofResult, actualPlanResult);
        assertTrue(actualPlanResult.isPresent());
        verify(this.planAtmRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PlanningController#updatePlan(PlanAtm, Atm, User)}
     */
    @Test
    void testUpdatePlan() {
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
        when(this.planAtmRepository.save((PlanAtm) any())).thenReturn(planAtm);

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

        User user1 = new User();
        user1.setCompanies(new HashSet<>());
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(1);
        user1.setLastName("Doe");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());

        PlanAtm planAtm1 = new PlanAtm();
        planAtm1.setAtm(atm1);
        planAtm1.setCassettes(new HashSet<>());
        planAtm1.setId(1);
        planAtm1.setPlanMethod("Plan Method");
        planAtm1.setPlanPeriod(1);
        planAtm1.setStatus("Status");
        planAtm1.setUser(user1);

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

        User user2 = new User();
        user2.setCompanies(new HashSet<>());
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(1);
        user2.setLastName("Doe");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());
        assertSame(planAtm, this.planningController.updatePlan(planAtm1, atm2, user2));
        verify(this.planAtmRepository).save((PlanAtm) any());
        assertSame(atm2, planAtm1.getAtm());
        assertSame(user2, planAtm1.getUser());
    }
}

