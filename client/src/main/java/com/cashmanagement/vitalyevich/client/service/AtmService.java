package com.cashmanagement.vitalyevich.client.service;

import com.cashmanagement.vitalyevich.client.model.Atm;
import com.cashmanagement.vitalyevich.client.model.Cassette;
import com.cashmanagement.vitalyevich.client.model.User;

import java.util.List;
import java.util.Optional;

public interface AtmService {

    Iterable<Atm> getAtms();

    Atm getAtm(Integer id);

    List<Cassette> saveCassettes(List<Cassette> cassetteList);
    Iterable<Cassette> updateCassettes(List<Cassette> cassetteList);

}
