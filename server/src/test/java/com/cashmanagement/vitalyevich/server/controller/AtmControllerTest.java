package com.cashmanagement.vitalyevich.server.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cashmanagement.vitalyevich.server.model.Atm;
import com.cashmanagement.vitalyevich.server.model.Cassette;
import com.cashmanagement.vitalyevich.server.repository.AtmRepository;
import com.cashmanagement.vitalyevich.server.repository.CassetteRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AtmController.class})
@ExtendWith(SpringExtension.class)
class AtmControllerTest {
    @Autowired
    private AtmController atmController;

    @MockBean
    private AtmRepository atmRepository;

    @MockBean
    private CassetteRepository cassetteRepository;

    /**
     * Method under test: {@link AtmController#atms()}
     */
    @Test
    void testAtms() {
        when(this.atmRepository.findByOrderById()).thenReturn((Iterable<Atm>) mock(Iterable.class));
        this.atmController.atms();
        verify(this.atmRepository).findByOrderById();
    }

    /**
     * Method under test: {@link AtmController#atm(Integer)}
     */
    @Test
    void testAtm() {
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
        Optional<Atm> ofResult = Optional.of(atm);
        when(this.atmRepository.findAllById((Integer) any())).thenReturn(ofResult);
        Optional<Atm> actualAtmResult = this.atmController.atm(1);
        assertSame(ofResult, actualAtmResult);
        assertTrue(actualAtmResult.isPresent());
        verify(this.atmRepository).findAllById((Integer) any());
    }

    /**
     * Method under test: {@link AtmController#cassettes()}
     */
    @Test
    void testCassettes() {
        when(this.cassetteRepository.findByOrderByCassetteNumAsc()).thenReturn((Iterable<Cassette>) mock(Iterable.class));
        this.atmController.cassettes();
        verify(this.cassetteRepository).findByOrderByCassetteNumAsc();
    }
}

