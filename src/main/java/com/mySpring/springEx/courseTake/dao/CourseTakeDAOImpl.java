package com.mySpring.springEx.courseTake.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.courseTake.dao.CourseTakeDAO;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

@Repository("courseTakeDAO")
public class CourseTakeDAOImpl implements CourseTakeDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllApplyList() throws DataAccessException {
		List<CourseTakeVO> applyList = null;
		applyList = sqlSession.selectList("mapper.courseTake.selectAllApplyList");
		return applyList;
	}

	@Override
	public int updateApplyConsent(CourseTakeVO courseTakeVO) throws Exception {
		return sqlSession.update("mapper.courseTake.updateApplyConsent", courseTakeVO);
	}
	
	@Override
	   public int updateCompletion(CourseTakeVO courseTakeVO) throws Exception {
	      return sqlSession.update("mapper.courseTake.updateCompletion", courseTakeVO);
	   }

	//�׽�Ʈ
	@Override
	public List selectAllCompleteList() throws DataAccessException {
		List<CourseTakeVO> completeList = null;
		completeList = sqlSession.selectList("mapper.courseTake.selectAllCompleteList");
		return completeList;
	}

	@Override
	public int insertCourseTake(CourseTakeVO courseTakeVO) throws DataAccessException {
		System.out.println(courseTakeVO.getUserID());
		return sqlSession.insert("mapper.courseTake.insertCourseTake", courseTakeVO);
	}

}