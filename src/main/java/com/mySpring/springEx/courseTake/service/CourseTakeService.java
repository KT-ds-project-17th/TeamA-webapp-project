package com.mySpring.springEx.courseTake.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

public interface CourseTakeService {

	// ������û���� ���������̼�
	public List<Map<String, Object>> SelectAllCourseApplyList() throws Exception;

	public List<Map<String, Object>> selectAllCourseApplyList(Pagination pagination) throws Exception;

	public int testTableCountCT() throws Exception;

	// �׽�Ʈ������
	public List courseCompleteList() throws DataAccessException;

}