package com.mySpring.springEx.courseTake.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

public interface CourseTakeDAO {
	public List selectAllApplyList() throws DataAccessException;

	// ��û����
	public int updateApplyConsent(CourseTakeVO courseTakeVO) throws Exception;

	// ������->����
	public int updateCompletion(CourseTakeVO courseTakeVO) throws Exception;

	// �׽�Ʈ
	public List selectAllCompleteList() throws DataAccessException;

}