package com.mySpring.springEx.partner.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.partner.vo.PartnerVO;

public interface PartnerService {

	public List<Map<String, Object>> SelectAllListPartner() throws Exception;

	public List<Map<String, Object>> SelectAllListPartner(Pagination pagination) throws Exception;

	public int testTableCountPartner() throws Exception;

	public List listNumPartner() throws DataAccessException;

	public void addPartner(PartnerVO partner) throws DataAccessException;

	public void modPartner(PartnerVO partner) throws DataAccessException;

	public PartnerVO partnerDetailInfo(String partnerLicenseNum) throws DataAccessException;

	public String removePartner(String partnerLicenseNum) throws DataAccessException;

	/* ������� */

	public List<Map<String, Object>> SelectAllListCompanyEmployee(Pagination pagination, String partnerLicenseNum)
			throws Exception;

	public int companyEmployeeTableCount(String partnerLicenseNum) throws Exception;

	// ����ã��

	public List<Map<String, Object>> SearchListCompanyEmployee(Pagination pagination, String partnerLicenseNum,String userName,
										String syllabusName, String courseStartDate, String completionDate)	throws Exception;

	public int searchEmployeeTableCount(String partnerLicenseNum, String userName, String syllabusName,
			String courseStartDate, String completionDate) throws Exception;

	// ���� �� ���
	public int companyUserNum(String partnerLicenseNum) throws Exception;

	public int companyCourseUserNum() throws Exception;

	public List<Map<String, Object>> infoGraph(String partnerLicenseNum) throws Exception;
	
	/* ������� �� */
}
