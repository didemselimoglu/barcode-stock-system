package com.didem.system_rest_api.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didem.system_rest_api.dto.DtoProduct;
import com.didem.system_rest_api.model.Product;
import com.didem.system_rest_api.repository.ProductRepository;
import com.didem.system_rest_api.service.IProductService;

@Service
public class ProductImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	
	//gelen barkod numarasına göre ürün bilgilerini çekme
	@Override
	public DtoProduct findProductByBarcodeCode(String barcodeCode) {
		
		DtoProduct dtoProduct = new DtoProduct();
		
		Optional<Product> optional =  productRepository.findByBarcodeCode(barcodeCode);
		if(optional.isEmpty()) {
			throw new RuntimeException("Barkod kodu '" + barcodeCode + "' ile eşleşen ürün bulunamadı");		
			}
		
		Product product = optional.get();
		BeanUtils.copyProperties(product, dtoProduct);
		
		return dtoProduct;
	}
	
	

}
