package com.mySpring.springEx.common.pagination.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mySpring.springEx.common.pagination.Pagination;


public interface PageMapper {

	//������
	public List<Map<String, Object>> SelectAllList() throws Exception;

	// Paging ��ü �����Ϳ� ���� ����¡ ��ü�� �־� ����¡ ó���� �� �� Map�������� �޾Ƽ� List�� ����
	// ��üȭ�� ���ѵ����� ����, ������ ����¡ ó���� ���õ� �κ��� ��ü ���Ը����� ó���� �����ϵ��� ����
	public List<Map<String, Object>> SelectAllList(Pagination pagination) throws Exception;
	
	//������û����
	public List<Map<String, Object>> SelectAllCourseApplyList() throws Exception;
	public List<Map<String, Object>> selectAllCourseApplyList(Pagination pagination) throws Exception;
	public int testTableCountCT() throws Exception;
	
	// count ��ü ������ ������ Ȯ���ϱ� ���� �κ�����, ������ Ȯ���ϱ� ������ int�� ����
	public int testTableCount() throws Exception;
	
	/*----------------------------------------------------------*/
	/*-------------------��Ʈ��-----------------------------------*/
	/*----------------------------------------------------------*/
	
	public List<Map<String, Object>> SelectAllListPartner() throws Exception;

	public List<Map<String, Object>> SelectAllListPartner(Pagination pagination) throws Exception;
	
	public int testTableCountPartner() throws Exception;

	/*������� ����*/
	public List<Map<String, Object>> SelectAllListCompanyEmployee(@Param("firstRecordIndex") int firstRecordIndex,@Param("lastRecordIndex") int lastRecordIndex, @Param("partnerLicenseNum")String partnerLicenseNum);

	public int companyEmployeeTableCount(String partnerLicenseNum);
	public int searchEmployeeTableCount(@Param("partnerLicenseNum") String partnerLicenseNum, @Param("userName") String userName,
			@Param("syllabusName")String syllabusName, @Param("courseStartDate")String courseStartDate, @Param("completionDate")String completionDate);
	
	public List<Map<String, Object>> SearchListCompanyEmployee(@Param("firstRecordIndex") int firstRecordIndex,@Param("lastRecordIndex") int lastRecordIndex, @Param("partnerLicenseNum")String partnerLicenseNum,
			@Param("userName") String userName, @Param("syllabusName")String syllabusName, @Param("courseStartDate")String courseStartDate, @Param("completionDate")String completionDate);
	/*������� ��*/
	

}
