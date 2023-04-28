package com.kh.spring19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.spring19.repo.CertRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private CertRepo certRepo;
	
	//이 기능이 언제 실행하면 좋은지 설정
	//@Scheduled(fixedRate = 1000L)//1초
	//@Scheduled(cron = "* * * * * *")//1초(매초 매분 매시 매일 매월 매요일)
	//@Scheduled(cron = "0 * * * * *")//0초 매분 매시 매일 매월 매요일
	@Scheduled(cron = "0 0 * * * *")//매시 정각
	//@Scheduled(cron = "0 0 0 * * *")//매일 자정
	//@Scheduled(cron = "0 0 9 * * *")//매일 아침 9시
	//@Scheduled(cron = "0 0 9,18 * * *")//매일 출/퇴근시각 (9 to 6)
	//@Scheduled(cron = "0 0 9-18 * * *")//매일 출근시각부터 퇴근시각까지 정각마다 (9시부터 6시까지)
	//@Scheduled(cron = "0 0 9-18 * * 1-5")//주중 출퇴근 시간 동안 정각마다
	//@Scheduled(cron = "0 0 9-18 * * mon-fri")//주중 출퇴근 시간 동안 정각마다
	//@Scheduled(cron = "0 0 15 25 * ?")//급여 지급 시각(매월 25일 오후 3시, 요일은 무관)
	//@Scheduled(cron = "0 0 7 */10 * ?")//열흘마다 아침 7시에
	//@Scheduled(cron = "* * * ? * thu")//목요일의 모든 시간
	//@Scheduled(cron = "* * * ? * 4#2")//2주차 목요일의 모든 시간
	//@Scheduled(cron = "* * * ? * thu#2")//2주차 목요일의 모든 시간
	//@Scheduled(cron = "0 0 18 ? * 4L")//매월 마지막 목요일 퇴근 시각(18시)
	//@Scheduled(cron = "0 0 18 ? * thuL")//매월 마지막 목요일 퇴근 시각(18시)
	@Override
	public void clearCertData() {
		log.debug("인증정보 중 5분이 지난 것을 삭제합니다");
		certRepo.clean();
	}
	
}
