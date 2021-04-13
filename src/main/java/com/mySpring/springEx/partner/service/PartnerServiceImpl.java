package com.mySpring.springEx.partner.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mySpring.springEx.partner.dao.PartnerDAO;
import com.mySpring.springEx.partner.vo.PartnerVO;

@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {
	
	
	@Autowired
	private PartnerDAO partnerDAO;
	
	
	//ȸ�� ����Ʈ ���
	@Override
	public List listPartner() throws DataAccessException {
		List partnerList = null;
		partnerList = partnerDAO.selectAllPartner();
		return partnerList;
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
}
