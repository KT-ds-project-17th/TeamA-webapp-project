package com.mySpring.springEx.member.dao;

import java.util.HashMap;
import java.util.List;

import com.mySpring.springEx.application.vo.ApplicationVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.partner.vo.PartnerVO;


@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	@Override
	public List selectAllRecruitList() throws DataAccessException {
		List<PartnerVO> partnerList = null;
		partnerList = sqlSession.selectList("mapper.member.selectAllRecruitList");
		return partnerList;
	}

	@Override
	public List selectAllApplicationList(String id) throws DataAccessException {
		List<HashMap<String, String>> applicationList = sqlSession.selectList("mapper.member.selectAllApplicationList", id);
//		for (HashMap<String, String> stringStringHashMap : applicationList) {
//			System.out.println("------------------------------------");
//			System.out.println(stringStringHashMap.get("partnerName"));
//			System.out.println(stringStringHashMap.get("partnerID"));
//			System.out.println(stringStringHashMap.get("partnerApplyDate"));
//			System.out.println(stringStringHashMap.get("partnerApplyState"));
//			System.out.println("------------------------------------");
//		}
		return applicationList;
	}

	public int userApplyPartner(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerApplyUserID", partnerApplyUserID);
		map.put("partnerApplyPartnerID", partnerApplyPartnerID);
		return sqlSession.insert("mapper.member.user_apply", map);
	}

	public int deleteApplication(String partnerApplyUserID, String partnerApplyPartnerID) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerApplyUserID", partnerApplyUserID);
		map.put("partnerApplyPartnerID", partnerApplyPartnerID);
		return sqlSession.delete("mapper.member.deleteApplication", map);
	}

	@Override
	public int deleteMember(String id) throws DataAccessException {
		int result = sqlSession.delete("mapper.member.deleteMember", id);
		return result;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);
		vo.setResume((String) sqlSession.selectOne("mapper.member.check_resume", vo.getUserId()));
		return vo;
	}
	//test��
	// ���̵� �ߺ� �˻�
	@Override
	public int check_id(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.check_id", id);
	}

	// �̸��� �ߺ� �˻�
	@Override
	public int check_email(String email) throws Exception {
		return sqlSession.selectOne("mapper.member.check_email", email);
	}

	// ȸ������
	@Transactional
	@Override
	public int join_member(MemberVO member) throws Exception {
		return sqlSession.insert("mapper.member.join_member", member);
	}

	// �̸��� ����
	@Transactional
	@Override
	public int approval_member(MemberVO member) throws Exception {
		return sqlSession.update("mapper.member.approval_member", member);
	}


	@Override
	public int insertMember(MemberVO memberVO) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
