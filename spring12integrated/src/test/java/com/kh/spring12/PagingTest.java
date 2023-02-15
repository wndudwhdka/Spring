package com.kh.spring12;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.dao.MemberDao;

//Spring TEST
//- 거대해지는 애플리케이션에서 단위 기능을 테스트하기 위해 사용하는 도구 
//- ex : DAO를 개발하고 테스트 하기 위해 컨트롤러까지 만드는게 맞자?
//- Junit이라는 원천기술을 Spring Test로 랩핑(Wrapping)하여 사용

@SpringBootTest
public class PagingTest {
	//JUnit에서는 테스트를 하기 위하여 만든 메소드에 @Test를 붙여야 한다
	// - 접근제한은 package 이상 ,반환형 void, 매개변수가 없어야 한다. 
	
	@Autowired
	private MemberDao memberDao; 
	
	@Test
	public void test() {
		System.out.println("Hello world");
		int count = memberDao.selectCount();
		System.out.println("count is = "+count);
	}
	
}
