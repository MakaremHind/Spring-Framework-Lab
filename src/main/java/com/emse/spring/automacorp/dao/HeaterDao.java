package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.HeaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaterDao extends JpaRepository<HeaterEntity, Long> {
    // Add custom query methods here if needed
}
