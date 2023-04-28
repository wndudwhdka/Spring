package com.kh.spring19.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;

public interface EmailService {
	//인증메일 보내기
	void sendCert(String email) throws MessagingException, FileNotFoundException, IOException;
}
