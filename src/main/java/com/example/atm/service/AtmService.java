package com.example.atm.service;


import com.example.atm.entity.AtmEntity;

import java.util.List;

public interface AtmService {
    public void StartATM();
    public List<AtmEntity> getAtmEntities();


}
