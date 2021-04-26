package com.mySpring.springEx.partner.dao;

import java.util.List;

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

	//graph information ajax
	@Override
	public List getInfoGraph(String partnerLicenseNum) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.partner.companyGraphInfo", partnerLicenseNum);
	}


	@Override
	public List SelectAllListCompanyEmployee(String partnerLicenseNum) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.partner.companyEmployeeList",partnerLicenseNum);
	}
}
