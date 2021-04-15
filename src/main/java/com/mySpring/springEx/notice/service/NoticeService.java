package com.mySpring.springEx.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.notice.vo.NoticeVO;


public interface NoticeService {
	/* public List listNotice() throws DataAccessException; */
	public List<Map<String, Object>> SelectAllList() throws Exception;

	public List<Map<String, Object>> SelectAllList(Pagination pagination) throws Exception;

	public int testTableCount() throws Exception;
	
	
	public void insertNotice(NoticeVO noticeVO) throws DataAccessException;
	public NoticeVO readNotice(int notice_no) throws DataAccessException;
	public void updateNotice(NoticeVO noticeVO) throws DataAccessException;
	public void deleteNotice(int notice_no) throws DataAccessException;
}