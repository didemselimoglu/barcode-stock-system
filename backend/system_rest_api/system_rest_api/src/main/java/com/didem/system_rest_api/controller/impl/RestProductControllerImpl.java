package com.didem.system_rest_api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.didem.system_rest_api.controller.IRestProductController;
import com.didem.system_rest_api.dto.DtoProduct;
import com.didem.system_rest_api.service.IProductService;


@RestController
@RequestMapping("/product")
public class RestProductControllerImpl implements IRestProductController {

	@Autowired
	private IProductService productService;

	@GetMapping("/{barcodeCode}")
	@Override
	public DtoProduct findByBarcodeCode(@PathVariable(value = "barcodeCode") String barcodeCode) {
		
		return productService.findProductByBarcodeCode(barcodeCode);
	}

}
