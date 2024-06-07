package com.example.atm.controller;

import com.example.atm.entity.AtmEntity;
import com.example.atm.service.AtmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AtmController {
    private AtmServiceImpl atmService;

    @Autowired
    public AtmController(AtmServiceImpl atmservice) {
        this.atmService = atmservice;
    }

    @GetMapping("/atm")
    public String atm(Model model) {
        model.addAttribute("cash", atmService.getAtmEntities());
        model.addAttribute("currentBalance", atmService.getAllMoney());
        return "atm";
    }

    @PostMapping("/withdraw")
    public ResponseEntity<List<AtmEntity>> withdrawMoney(@RequestParam double amount, Model model) {
        try {
            List<AtmEntity> result = atmService.withdrawMoney(amount);
            model.addAttribute("message", "Retiro exitoso");
            model.addAttribute("currentBalance", atmService.getAllMoney());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
