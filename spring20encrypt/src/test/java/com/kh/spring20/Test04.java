package com.kh.spring20;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

//목표 : 단방향 암호화에 대한 이해(해시 알고리즘)
//- 원본을 알 수 없도록 만드는 암호화 방식
//- 두 개 이상의 값이 동일한 결과가 나오면 안됨(충돌)
//- 동일한 값은 동일한 결과가 나와야 함
@Slf4j
@SpringBootTest
public class Test04 {
	@Test
	public void test( ) {
		//3학년 5반 10번 -> 학번 (030509)
		int grade = 3, group = 5, number = 9;
		
		DecimalFormat f = new DecimalFormat("00");
		
		String after = f.format(grade) + f.format(group) + f.format(number);
		log.debug("after = {}", after);
		
		//숫자일 경우 - 의도적으로 자리수를 엄청나게 키워서 잘라내서 사용
		int before = 7;
		
		before *= 12345;
		before += 12345;
		before *= 12345;
		before %= 100000000;
		
		log.debug("after = {}", before);
	}
}




