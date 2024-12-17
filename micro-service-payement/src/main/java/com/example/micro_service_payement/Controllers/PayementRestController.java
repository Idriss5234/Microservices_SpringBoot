package com.example.micro_service_payement.Controllers;

import com.example.micro_service_payement.Model.Payement;
import com.example.micro_service_payement.Repository.PayementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PayementRestController {

    private final PayementRepository payementRepository;

    @Autowired
    public PayementRestController(PayementRepository payementRepository) {
        this.payementRepository = payementRepository;
    }


    @GetMapping("/Payements")
    public List<Payement> getPayements() {
        return payementRepository.findAll();
    }

    @GetMapping("/Payements/{id}")
    public Payement getPayements(@PathVariable("id") long id) {
        return payementRepository.findById(id).orElse(null);
    }

    @PostMapping("/Payements")
    public Payement addPayements(@RequestBody Payement c) {
        return payementRepository.save(c);
    }
}

