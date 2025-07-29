package com.didem.system_rest_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.didem.system_rest_api.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findByBarcodeCode(String barcodeCode);

}
