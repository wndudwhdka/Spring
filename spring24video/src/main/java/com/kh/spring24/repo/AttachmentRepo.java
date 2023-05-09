package com.kh.spring24.repo;

import com.kh.spring24.dto.AttachmentDto;

public interface AttachmentRepo {
    int sequence();
    void insert(AttachmentDto attachmentDto);
    AttachmentDto selectOne(int attachmentNo);
}
