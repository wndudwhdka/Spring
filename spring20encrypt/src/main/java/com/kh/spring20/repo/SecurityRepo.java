package com.kh.spring20.repo;

import com.kh.spring20.dto.SecurityDto;

public interface SecurityRepo {
	void insert(SecurityDto securityDto);
	boolean login(SecurityDto securityDto);
}
