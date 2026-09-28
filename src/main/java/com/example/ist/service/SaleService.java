package com.example.ist.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ist.entity.Sale;
import com.example.ist.repository.Repository;

@Service
public class SaleService {

    private final Repository repo;

    public SaleService(Repository repo) {
        this.repo = repo;
    }

    public Sale createSale(Sale sale) {
        return repo.save(sale);
    }

    public List<Sale> getAllSales() {
        return repo.findAll();
    }

    public Sale getSaleById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Sale updateSale(Long id, Sale sale) {

        Sale existingSale = repo.findById(id).orElse(null);

        if (existingSale == null) {
            return null;
        }

        existingSale.customerName = sale.customerName;
        existingSale.phoneModel = sale.phoneModel;
        existingSale.color = sale.color;
        existingSale.storageGb = sale.storageGb;
        existingSale.price = sale.price;
        existingSale.saleDate = sale.saleDate;
        existingSale.storeLocation = sale.storeLocation;

        return repo.save(existingSale);
    }

    public boolean deleteSale(Long id) {

        Sale sale = repo.findById(id).orElse(null);

        if (sale == null) {
            return false;
        }

        repo.deleteById(id);
        return true;
    }

    public List<Sale> getSalesByPhoneModel(String phoneModel) {
        return repo.findByPhoneModel(phoneModel);
    }


    public long getTotalSales() {
        return repo.getTotalSales();
    }

    public BigDecimal getTotalRevenue() {
        return repo.getTotalRevenue();
    }

    public Double getAveragePrice() {
        return repo.getAveragePrice();
    }

    public String getMostSoldModel() {

        List<String> models = repo.getMostSoldModel();

        if (models.size() == 0) {
            return "No sales available";
        }

        return models.get(0);
    }
}