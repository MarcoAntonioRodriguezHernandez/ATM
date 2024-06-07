package com.example.atm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AtmEntity {
    private int quantity;
    private double denomination;
    private String type;

}
