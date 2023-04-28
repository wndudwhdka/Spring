# ORM Framework

ORM(Object-Relational Mapping, 객체 연관 매핑) 프레임워크란 
데이터베이스를 프로그래밍 입장에서 사용하도록 고안한 도구

`RowMapper`와 같은 도구들은 프로그래밍스럽지 못한 클래스이다.  
자동변환, 자동관리 등 데이터베이스를 프로그래밍에서 통제할 수 있는 여러 장치들을 제공

# ORM Framework의 종류

다음 두 가지 컨셉의 ORM Framework가 존재한다.

1. myBatis(구 iBatis) - 코드와 SQL 구문을 분리하여 작성하는 형태를 추구
2. JPA - SQL구문을 자동생성하는 형태를 추구

# myBatis의 적용

myBatis는 스프링 뿐 아니라 자바 전체에서 사용 가능한 기술이므로
스프링은 어댑터 라이브러리를 이용하여 스프링화 시켜서 사용한다

```
	[스프링] <--- [mybatis-spring] <--- [mybatis]
```








