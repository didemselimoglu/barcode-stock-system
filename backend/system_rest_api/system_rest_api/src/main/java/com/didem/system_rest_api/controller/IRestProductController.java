package com.didem.system_rest_api.controller;

import com.didem.system_rest_api.dto.DtoProduct;

public interface IRestProductController {
	
	public DtoProduct findByBarcodeCode(String barcodeCode);

}
