package com.example.micro_service_payement.Repository;

import com.example.micro_service_payement.Model.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayementRepository extends JpaRepository<Payement, Long> {

}
