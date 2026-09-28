package com.example.ist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ist.entity.Sale;
import com.example.ist.service.SaleService;

@RestController
@RequestMapping("/sales")
public class Controller {

    private final SaleService saleService;

    public Controller(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody Sale sale) {

        Sale newSale = saleService.createSale(sale);

        return ResponseEntity.ok(newSale);
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getSales(
            @RequestParam(required = false) String phone_model) {

        if (phone_model != null) {
            return ResponseEntity.ok(
                    saleService.getSalesByPhoneModel(phone_model)
            );
        }

        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {

        Sale sale = saleService.getSaleById(id);

        if (sale == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(
            @PathVariable Long id,
            @RequestBody Sale sale) {

        Sale updatedSale = saleService.updateSale(id, sale);

        if (updatedSale == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {

        boolean deleted = saleService.deleteSale(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {

        Map<String, Object> stats = new HashMap<>();

        stats.put("totalSales", saleService.getTotalSales());
        stats.put("totalRevenue", saleService.getTotalRevenue());
        stats.put("averagePrice", saleService.getAveragePrice());
        stats.put("mostSoldModel", saleService.getMostSoldModel());

        return ResponseEntity.ok(stats);
    }
}