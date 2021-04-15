package com.mySpring.springEx.partner.vo;


import org.springframework.stereotype.Component;

@Component("partnerVO")
public class PartnerVO {
	private String partnerLicenseNum;           /*���»� ����ڹ�ȣ*/
	private String partnerState;              /*���� ����*/
	private String partnerName;                   /*���»� ��*/
	private String partnerInformation;            /*������ ȸ�� ����*/
	private String partnerAddress;                /*���»� �ּ�*/
	private String partnerPhoneNumber;            /*���»� ��ȭ��ȣ*/
	private String partnerEmail;                  /*���»� �̸���*/
	private String partnerCEO;                    /*���»� ��ǥ��*/
	private int partnerHeadCount;                 /*�ο� �Ը�*/
	private String partnerApplyYN;              /*���� ���� ����*/
	private String partnerApplyFinishDate;          /*���� ���� ��¥*/
	private String partnerURL;                    /*ȸ�� Ȩ������*/
	private String partnerRegisterDate;           /*��ϳ�¥*/


	public PartnerVO(){

	}

	public PartnerVO(String partnerLicenseNum, String partnerState, String partnerName, String partnerInformation,String partnerAddress,String partnerPhoneNumber,String partnerEmail,String partnerCEO,int partnerHeadCount,String partnerApplyYN,String partnerApplyFinishDate,String partnerURL, String partnerRegisterDate){
		this.partnerLicenseNum = partnerLicenseNum;
		this.partnerName = partnerName;
		this.partnerInformation = partnerInformation;
		this.partnerAddress = partnerAddress;
		this.partnerPhoneNumber   = partnerPhoneNumber;
		this.partnerEmail = partnerEmail;
		this.partnerCEO = partnerCEO;
		this.partnerHeadCount = partnerHeadCount;
		this.partnerApplyYN = partnerApplyYN;
		this.partnerURL = partnerURL;
		this.partnerApplyFinishDate = partnerApplyFinishDate;
		this.partnerRegisterDate = partnerRegisterDate;


	}

	public String getPartnerApplyFinishDate() {
		return partnerApplyFinishDate;
	}

	public void setPartnerApplyFinishDate(String partnerApplyFinishDate) {
		this.partnerApplyFinishDate = partnerApplyFinishDate;
	}

	public String getPartnerLicenseNum() {
		return partnerLicenseNum;
	}

	public void setPartnerLicenseNum(String partnerLicenseNum) {
		this.partnerLicenseNum = partnerLicenseNum;
	}

	public String getPartnerState() {
		return partnerState;
	}

	public void setPartnerState(String partnerState) {
		this.partnerState = partnerState;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartnerInformation() {
		return partnerInformation;
	}

	public void setPartnerInformation(String partnerInformation) {
		this.partnerInformation = partnerInformation;
	}

	public String getPartnerAddress() {
		return partnerAddress;
	}

	public void setPartnerAddress(String partnerAddress) {
		this.partnerAddress = partnerAddress;
	}

	public String getPartnerPhoneNumber() {
		return partnerPhoneNumber;
	}

	public void setPartnerPhoneNumber(String partnerPhoneNumber) {
		this.partnerPhoneNumber = partnerPhoneNumber;
	}

	public String getPartnerEmail() {
		return partnerEmail;
	}

	public void setPartnerEmail(String partnerEmail) {
		this.partnerEmail = partnerEmail;
	}

	public String getPartnerCEO() {
		return partnerCEO;
	}

	public void setPartnerCEO(String partnerCEO) {
		this.partnerCEO = partnerCEO;
	}

	public int getPartnerHeadCount() {
		return partnerHeadCount;
	}

	public void setPartnerHeadCount(int partnerHeadCount) {
		this.partnerHeadCount = partnerHeadCount;
	}
	public String getPartnerApplyYN() {
		return partnerApplyYN;
	}

	public void setPartnerApplyYN(String partnerApplyYN) {
		this.partnerApplyYN = partnerApplyYN;
	}

	public String getPartnerURL() {
		return partnerURL;
	}

	public void setPartnerURL(String partnerURL) {
		this.partnerURL = partnerURL;
	}

	public String getPartnerRegisterDate() {
		return partnerRegisterDate;
	}

	public void setPartnerRegisterDate(String partnerRegisterDate) {
		this.partnerRegisterDate = partnerRegisterDate;
	}


}