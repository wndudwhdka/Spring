# 첨부파일 테이블

```sql
create table attachment (
attachment_no number primary key,
attachment_name varchar2(256) not null,
attachment_type varchar2(60) not null,
attachment_size number not null
);

create sequence attachment_seq;
```

# 포켓몬 이미지(연결) 테이블

```sql
create table pocketmon_image (
pocketmon_no not null references pocketmon(no) on delete cascade,
attachment_no not null references attachment(attachment_no) on delete cascade,
primary key(pocketmon_no, attachment_no)
);
```









