package com.kh.spring22.repo;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring22.dto.PaymentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PaymentRepoImpl implements PaymentRepo {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("payment.sequence");
	}
	
	@Override
	public void save(PaymentDto dto) {
		sqlSession.insert("payment.save", dto);
	}
	
	@Override
	public List<PaymentDto> selectAll() {
		return sqlSession.selectList("payment.selectAll");
	}
	
	@Override
	public List<PaymentDto> selectByMember(String memberId) {
		return sqlSession.selectList("payment.selectByMember", memberId);
	}
	
	@Override
	public PaymentDto find(int paymentNo) {
		return sqlSession.selectOne("payment.find", paymentNo);
	}
	
	@Override
	public void cancelRemain(int paymentNo) {
		sqlSession.update("payment.cancelRemain", paymentNo);
	}
	
}