package com.example.ist.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ist.entity.Sale;

public interface Repository extends JpaRepository<Sale, Long> {

    List<Sale> findByPhoneModel(String phoneModel);

    @Query("SELECT COUNT(s) FROM Sale s")
    long getTotalSales();

    @Query("SELECT SUM(s.price) FROM Sale s")
    BigDecimal getTotalRevenue();

    @Query("SELECT AVG(s.price) FROM Sale s")
    Double getAveragePrice();

    @Query("""
        SELECT s.phoneModel
        FROM Sale s
        GROUP BY s.phoneModel
        ORDER BY COUNT(s.phoneModel) DESC
        """)
    List<String> getMostSoldModel();
}