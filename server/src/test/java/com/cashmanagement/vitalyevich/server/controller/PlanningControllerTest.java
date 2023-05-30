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
}

