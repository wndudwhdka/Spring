package com.kh.spring22.dto;

import lombok.Data;

@Data
public class ItemDto {
	private int itemNo;
	private String itemName;
	private int itemPrice, itemDiscount;
}