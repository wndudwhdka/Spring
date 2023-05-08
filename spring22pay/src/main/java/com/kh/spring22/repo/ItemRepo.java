package com.kh.spring22.repo;

import java.util.List;

import com.kh.spring22.dto.ItemDto;

public interface ItemRepo {
	List<ItemDto> list();
	ItemDto find(int itemNo);
}