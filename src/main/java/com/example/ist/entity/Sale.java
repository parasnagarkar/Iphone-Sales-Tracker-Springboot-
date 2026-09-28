package com.example.ist.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity 
@Table(name = "iphone_sales")
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @Column(nullable= false,length=100)
    public String customerName;

    @Column(nullable=false,length=50)
    public String phoneModel;

    @Column(nullable = false,length = 30)
    public String color;

    @Column(nullable=false)
    public Integer storageGb;

    @Column(nullable=false,precision=10,scale=2)
    public BigDecimal price;

    @Column(nullable = false)
    public LocalDate saleDate;

    @Column(nullable=false,length=100)
    public String storeLocation;

    @Column(nullable = false, updatable = false)
    public LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }
}
