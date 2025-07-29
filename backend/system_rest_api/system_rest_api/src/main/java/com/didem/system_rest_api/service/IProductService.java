package com.didem.system_rest_api.service;

import com.didem.system_rest_api.dto.DtoProduct;

public interface IProductService {
	
	DtoProduct findProductByBarcodeCode(String barcodeCode);
	
	

}
