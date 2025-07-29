package com.didem.system_rest_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"barcodeCode"})})
@Getter 
@Setter 
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	
	
	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	
	@Column(name = "barcode_code", nullable = false, unique = true)
	private String barcodeCode;
	
	@Column(name = "product_name", nullable = false)
	private String productName;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "location")
	private String location;
	
}