package com.example.atm.service;

import com.example.atm.entity.AtmEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtmServiceImpl implements AtmService{

    private ArrayList atmEntities;

    public AtmServiceImpl() {
        atmEntities = new ArrayList<>();
        StartATM();
    }

    public void StartATM() {
        Object[][] values = {
                {2, 1000.0, "Billete"},
                {5, 500.0, "Billete"},
                {10, 200.0, "Billete"},
                {20, 100.0, "Billete"},
                {30, 50.0, "Billete"},
                {40, 20.0, "Billete"},
                {50, 10.0, "Moneda"},
                {100, 5.0, "Moneda"},
                {200, 2.0, "Moneda"},
                {300, 1.0, "Moneda"},
                {100, 0.50, "Moneda"},
        };
        atmEntities = new ArrayList<>();
        for (Object[] value : values) {
            int quantity = (int) value[0];
            double denomination = (double) value[1];
            String type = (String) value[2];
            atmEntities.add(new AtmEntity(quantity, denomination, type));
        }
    }

    public ArrayList getAtmEntities() {
        return atmEntities;
    }
    public double getAllMoney() {
        double total = 0;
        for (Object atmEntity : atmEntities) {
            AtmEntity entity = (AtmEntity) atmEntity;
            total += entity.getQuantity() * entity.getDenomination();
        }
        return total;
    }

    public List<AtmEntity> withdrawMoney(double amount) throws Exception {
        double total = getAllMoney();
        if (amount > total) {
            throw new Exception("No hay suficiente dinero en el cajero");
        }
        List<AtmEntity> withdrawnMoney = new ArrayList<>();
        for (Object atmEntity : atmEntities) {
            AtmEntity entity = (AtmEntity) atmEntity;
            int quantity = entity.getQuantity();
            double denomination = entity.getDenomination();
            int amountToWithdraw = (int) (amount / denomination);
            if (amountToWithdraw > quantity) {
                amountToWithdraw = quantity;
            }
            amount -= amountToWithdraw * denomination;
            entity.setQuantity(quantity - amountToWithdraw);
            if (amountToWithdraw > 0) {
                withdrawnMoney.add(new AtmEntity(amountToWithdraw, denomination, entity.getType()));
            }
        }
        return withdrawnMoney;
    }
}
