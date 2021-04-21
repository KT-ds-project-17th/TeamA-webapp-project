package com.mySpring.springEx.partner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mySpring.springEx.partner.vo.PartnerVO;

@Repository("partnerDAO")
public class PartnerDAOImpl implements PartnerDAO{
	
	@Autowired
	private SqlSession sqlSession;

	
	//ȸ�� ����Ʈ ���
	@Override
	public List selectAllPartner() throws DataAccessException { 
		List <PartnerVO> partnerList = null;
		partnerList = sqlSession.selectList("mapper.partner.selectAllPartnerList"); //mapper.partner�� selectAllPartnerList�� ȣ���Ͽ� partner����Ʈ�� ����
		return partnerList;
		
	}

	
	//���»� count
	public int selectCooperationPartner() throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.selectCooperationPartner");  //mapper.partner�� selectCooperationPartner�� ȣ���Ͽ� ���� ����
		
	}
	
	//����� count
	public int selectConventionPartner() throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.selectConventionPartner");   //mapper.partner�� selectConventionPartner�� ȣ���Ͽ� ���� ����
		
	}
	
	//���� ������ count
	public int selectIngPartner() throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.selectIngPartner");      //mapper.partner�� selectIngPartner�� ȣ���Ͽ� ���� ����
		
	}
	
	//������ count
	public int selectNotPartner() throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.selectNotPartner");      //mapper.partner�� selectNotPartner�� ȣ���Ͽ� ���� ����
		
	}
	
	//ȸ�� ���� �Է�
	public int addPartner(PartnerVO partner) throws DataAccessException{
		int result = sqlSession.insert("mapper.partner.addPartner",partner);    //mapper.partner�� addPartner�� partner ��ü�� ���� �־��־� ��ü �� insert
		return result;
	}
	
	public int updatePartner(PartnerVO partner) throws DataAccessException{
		int result = sqlSession.update("mapper.partner.updatePartner",partner);   
		return result;
	}
	

	public PartnerVO selectDetailPartner(String partnerLicenseNum) throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.selectDetailPartner",partnerLicenseNum);      
			
		}
	
	public void deletePartner(String partnerLicenseNum) throws DataAccessException{
		sqlSession.delete("mapper.partner.deletePartner",partnerLicenseNum);
	}
	
	public String partnerName(String partnerLicenseNum) throws DataAccessException{
		System.out.println("dao�̸�"+sqlSession.selectOne("mapper.partner.partnerName",partnerLicenseNum));
		return sqlSession.selectOne("mapper.partner.partnerName",partnerLicenseNum);
	}

	/* ������� �Լ� */
	@Override
	public PartnerVO getCompanyInformation(String partnerLicenseNum) throws DataAccessException {
		return sqlSession.selectOne("mapper.partner.getCompanyInfo",partnerLicenseNum);
	}

	public int postJobOpening(String partnerLicenseNum, String date) throws DataAccessException{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("partnerLicenseNum", partnerLicenseNum);
		map.put("date", date);
		int result = sqlSession.update("mapper.partner.postJobOpening", map);
		return result;
	}

	public int deleteJobOpening(String partnerLicenseNum) throws DataAccessException{
		int result = sqlSession.update("mapper.partner.deleteJobOpening", partnerLicenseNum);
		return result;
	}

	public List<Map<String, Object>> selectPartnerApplyN() throws DataAccessException {
//		List<HashMap<String, Object>> applicationList = sqlSession.selectList("mapper.member.selectAllApplicationList", id);
		return sqlSession.selectList("mapper.partner.selectPartnerApplyN");
	}

	public List<Map<String, Object>> selectJobOpeningList() throws DataAccessException {
		return sqlSession.selectList("mapper.partner.selectJobOpeningList");
	}


	//ȸ�� ȸ�� ��
	@Override
	public int companyUserNumber(String partnerLicenseNum) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.partner.companyUserNumber",partnerLicenseNum);
	}

	//ȸ�� ���� ȸ�� ��
	@Override
	public int companyCourseUserNumber() throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.partner.companyCourseUserNumber");
	}
	/* ������� �Լ� ��*/
}
