package com.kh.spring19.repo;

import com.kh.spring19.dto.CertDto;

public interface CertRepo {
	void insert(CertDto certDto);
	boolean exist(CertDto certDto);
	void delete(CertDto certDto);
	void clean();
}
