-- 인증 정보 저장 테이블
create table cert (
email varchar2(100) not null,
secret char(6) not null,
limit date default sysdate not null,
primary key(email, secret)
);