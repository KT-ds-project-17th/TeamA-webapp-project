package com.mySpring.springEx.partner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.common.pagination.mapper.PageMapper;
import com.mySpring.springEx.partner.dao.PartnerDAO;
import com.mySpring.springEx.partner.vo.PartnerVO;

@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {
	
	
	@Autowired
	private PartnerDAO partnerDAO;

	@Autowired
	public PageMapper pageMapper;
	
	
	//ȸ�� ����Ʈ ���
	@Override
    public List<Map<String, Object>> SelectAllListPartner() throws Exception {
        // TODO Auto-generated method stub
        return pageMapper.SelectAllListPartner();
    }
 
    @Override
    public List<Map<String, Object>> SelectAllListPartner(Pagination pagination) throws Exception {
        // TODO Auto-generated method stub
        return pageMapper.SelectAllListPartner(pagination);
    }
 
    @Override
    public int testTableCountPartner() throws Exception {
        // TODO Auto-generated method stub
        return pageMapper.testTableCountPartner();
    }
	
	
	//ȸ�� ���º� count ���
	@Override
	public List listNumPartner() throws DataAccessException {
		List<Integer> listNumPartner = new ArrayList<Integer>();;
		listNumPartner.add(partnerDAO.selectCooperationPartner());  //���»� count
		listNumPartner.add(partnerDAO.selectConventionPartner());   //����� count
		listNumPartner.add(partnerDAO.selectIngPartner());			//���� ������ count
		listNumPartner.add(partnerDAO.selectNotPartner());			//������ count
		return listNumPartner;
	}
	
	//ȸ�� ���� �Է�
	@Override
	public void addPartner(PartnerVO partner) throws DataAccessException {
		partnerDAO.addPartner(partner);
	}
	
	
	//��� ����
	@Override
	public void modPartner(PartnerVO partner) throws DataAccessException {
		partnerDAO.updatePartner(partner);
	}
	
	
	//��� ���� �� �� 
	@Override
	public PartnerVO partnerDetailInfo(String partnerLicenseNum) throws DataAccessException {
		return partnerDAO.selectDetailPartner(partnerLicenseNum);
	}
	
	//��� ���� 
	@Override
	public String removePartner(String partnerLicenseNum) throws DataAccessException{
		String partnerName = partnerDAO.partnerName(partnerLicenseNum);
		partnerDAO.deletePartner(partnerLicenseNum);
		return partnerName;
	}

	
	/* =================================��� ���� ����======================= */
	@Override						 
	public List<Map<String, Object>> SelectAllListCompanyEmployee(Pagination pagination, String partnerLicenseNum) throws Exception {
		// TODO Auto-generated method stub
		List<Map<String, Object>>list = pageMapper.SelectAllListCompanyEmployee(pagination.getFirstRecordIndex(),pagination.getLastRecordIndex(),partnerLicenseNum);
		return list;}

	@Override
	public int companyEmployeeTableCount(String partnerLicenseNum) throws Exception {
		return pageMapper.companyEmployeeTableCount(partnerLicenseNum);
	}

	@Override
	public int companyUserNum(String partnerLicenseNum) throws Exception {
		return partnerDAO.companyUserNumber(partnerLicenseNum);
	}

	@Override
	public int companyCourseUserNum() throws Exception {
		return partnerDAO.companyCourseUserNumber();
	}

	@Override
	public int searchEmployeeTableCount(String partnerLicenseNum, String userName, String syllabusName,
			String courseStartDate, String completionDate) throws Exception {
		System.out.println(courseStartDate+"����==================�̰ǵ�����"+completionDate);
		return pageMapper.searchEmployeeTableCount(partnerLicenseNum, userName, syllabusName, courseStartDate, completionDate);
	}

	@Override
	public List<Map<String, Object>> SearchListCompanyEmployee(Pagination pagination, String partnerLicenseNum,
			String userName, String syllabusName, String courseStartDate, String completionDate) throws Exception {
		return pageMapper.SearchListCompanyEmployee(pagination.getFirstRecordIndex(),pagination.getLastRecordIndex(), partnerLicenseNum, userName, syllabusName, courseStartDate, completionDate);
	}

	//graph information ajax
	@Override
	public List<Map<String, Object>> infoGraph(String partnerLicenseNum) throws Exception {
		// TODO Auto-generated method stub
		return partnerDAO.getInfoGraph(partnerLicenseNum);
	}
	
	

	/*
	 * @Override public PartnerVO getCompanyInfo(String partnerLicenseNum) throws
	 * DataAccessException { // TODO Auto-generated method stub return
	 * partnerDAO.getCompanyInformation(partnerLicenseNum); }
	 */
	
	
	/* =================================��� ���� ��======================= */
	
}
