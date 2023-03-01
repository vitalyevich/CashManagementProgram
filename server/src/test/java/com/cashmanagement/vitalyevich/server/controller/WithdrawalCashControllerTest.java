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
import com.cashmanagement.vitalyevich.server.model.WithdrawalCash;
import com.cashmanagement.vitalyevich.server.repository.AtmRepository;
import com.cashmanagement.vitalyevich.server.repository.PlanAtmRepository;
import com.cashmanagement.vitalyevich.server.repository.WithdrawalCashRepository;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WithdrawalCashController.class})
@ExtendWith(SpringExtension.class)
class WithdrawalCashControllerTest {
    @MockBean
    private AtmRepository atmRepository;

    @MockBean
    private PlanAtmRepository planAtmRepository;

    @Autowired
    private WithdrawalCashController withdrawalCashController;

    @MockBean
    private WithdrawalCashRepository withdrawalCashRepository;

    /**
     * Method under test: {@link WithdrawalCashController#withdrawalCashes()}
     */
    @Test
    void testWithdrawalCashes() {
        ArrayList<WithdrawalCash> withdrawalCashList = new ArrayList<>();
        when(this.withdrawalCashRepository.findAll()).thenReturn(withdrawalCashList);
        Iterable<WithdrawalCash> actualWithdrawalCashesResult = this.withdrawalCashController.withdrawalCashes();
        assertSame(withdrawalCashList, actualWithdrawalCashesResult);
        assertTrue(((Collection<WithdrawalCash>) actualWithdrawalCashesResult).isEmpty());
        verify(this.withdrawalCashRepository).findAll();
    }

    /**
     * Method under test: {@link WithdrawalCashController#withdrawalCash(Integer)}
     */
    @Test
    void testWithdrawalCash() {
        when(this.withdrawalCashRepository.findByAtm((Atm) any()))
                .thenReturn((Iterable<WithdrawalCash>) mock(Iterable.class));

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
        when(this.planAtmRepository.findPlanAtmByAtmId((Integer) any())).thenReturn(planAtm);
        this.withdrawalCashController.withdrawalCash(1);
        verify(this.withdrawalCashRepository).findByAtm((Atm) any());
        verify(this.planAtmRepository).findPlanAtmByAtmId((Integer) any());
    }
}

