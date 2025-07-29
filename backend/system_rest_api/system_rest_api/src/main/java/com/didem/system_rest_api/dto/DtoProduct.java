package com.didem.system_rest_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoProduct {
	
	private String barcodeCode;
	
	private String productName;
	
	private String unit;
	
	private String quantity;
	
	private String location;
	
}
