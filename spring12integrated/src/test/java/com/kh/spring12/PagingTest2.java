package com.kh.spring12;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.vo.PaginationVO;

// 목표
// 페이지 번호 , 크기 검색항목, 키워드, 데이터 수를 알려주면
// 자동으로 불러와야 하는 데이터의 행 번호와 하단에 표시될 블록번호를 계산
// 등록하지 않고 일회용으로 사용한다. 

@SpringBootTest
public class PagingTest2 {
	
	@Test
	public void test() { 
		PaginationVO vo =new PaginationVO(); 
		vo.setCount(1200);
		vo.setPage(1);
		vo.setSize(10); 
		
		System.out.println("시작행 = "+ vo.getBegin());
		System.out.println("종료행 = "+ vo.getEnd());
		
		System.out.println(vo.getBegin());
		System.out.println(vo.getEnd());
		System.out.println();
	}
}
