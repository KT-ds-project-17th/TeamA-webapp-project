package com.mySpring.springEx.partner.controller;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.partner.service.PartnerService;
import com.mySpring.springEx.partner.vo.PartnerVO;

@Controller("partnerController")
public class PartnerContorollerImpl implements PartnerController {

	@Autowired
	private PartnerService partnerService;

	@Autowired
	PartnerVO partnervo;

	// ȸ�� ����Ʈ ���
	@Override
	@RequestMapping(value = "/partner/partnerList.do", method = RequestMethod.GET)
	public ModelAndView partnerList(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");

		int partnerList = partnerService.testTableCountPartner();

		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);

		pagination.setTotalRecordCount(partnerList);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("pagination", pagination);
		mav.addObject("partnerList", partnerService.SelectAllListPartner(pagination));

		List numPartner = partnerService.listNumPartner(); // ���»�, �����, ������, ���� ������ ���� count�ϴ� �޼ҵ带 ��Ʈ�� ���񽺿��� ȣ���Ͽ� ����Ʈ
															// ���·� ����

		mav.addObject("numCooperation", numPartner.get(0)); // ���»� count�� ���ε�
		mav.addObject("numConvention", numPartner.get(1)); // ����� count�� ���ε�
		mav.addObject("numIng", numPartner.get(2)); // ���� ������ count�� ���ε�
		mav.addObject("numNot", numPartner.get(3)); // ������ count�� ���ε�
		return mav;
	}

	// ȸ�� ���� �Է�
	@Override
	@RequestMapping(value = "/partner/addPartner.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addPartner(@ModelAttribute("partner") PartnerVO partner, RedirectAttributes rttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception { // partnerVO�� ��ü�� �޾Ƽ� db�� �����ϴ�
																							// �޼ҵ�
		request.setCharacterEncoding("utf-8");
		partnerService.addPartner(partner); // ��Ʈ�ʼ����� addPartner�� ȣ��
		rttr.addFlashAttribute("msg", "addSuccess");
		rttr.addFlashAttribute("partnerName", partner.getPartnerName());
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do"); // addpartner�� �� �� �ٽ� partnerList�� ���ư���
																					// ����
		return mav;
	}

	// ȸ�� ���� �Է� ��
	@Override
	@RequestMapping(value = "/partner/partnerForm.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception { // form���� �̵��ϴ�
																											// �޼ҵ�
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

	// ��� �� ����
	@Override
	@RequestMapping(value = "/partner/detailInfoPartner.do", method = RequestMethod.GET)
	public ModelAndView detailInfoPartner(@RequestParam("partnerLicenseNum") String partnerLicenseNum,
			HttpServletRequest request, HttpServletResponse response) throws Exception { // modform���� �̵��ϴ� �޼ҵ�
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		PartnerVO partnerVO;
		partnerVO = partnerService.partnerDetailInfo(partnerLicenseNum);
		mav.addObject("partnerVO", partnerVO);
		mav.setViewName(viewName);
		return mav;
	}

	// ȸ�� ���� ����
	@Override
	@RequestMapping(value = "/partner/modPartner.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modPartner(@ModelAttribute("partner") PartnerVO partner, RedirectAttributes rttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		partnerService.modPartner(partner);
		rttr.addFlashAttribute("msg", "modSuccess");
		rttr.addFlashAttribute("partnerName", partner.getPartnerName());
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");

		return mav;
	}

	// ��� ����
	@Override
	@RequestMapping(value = "/partner/deletePartner.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView removePartner(@RequestParam("partnerLicenseNum") String partnerLicenseNum,
			RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String partnerName = partnerService.removePartner(partnerLicenseNum);
		System.out.println("�̸�" + partnerName);
		rttr.addFlashAttribute("msg", "removeSuccess");
		rttr.addFlashAttribute("partnerName", partnerName);
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");
		return mav;
	}

	
	
	/* ===================================���»� ���� ����==============================*/
	@RequestMapping(value = "/partner/main.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView companyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/partner/main");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/partner/company/companyEmployee.do", method = RequestMethod.GET)
	public ModelAndView companyEmployee(
			@RequestParam("partnerLicenseNum") String partnerLicenseNum,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int employeeList = partnerService.companyEmployeeTableCount(partnerLicenseNum); // ��ü �������� ����
		int companyUser = partnerService.companyUserNum(partnerLicenseNum);	// �츮 ���»� ȸ�� ��
		int companyCourseUser = partnerService.companyCourseUserNum(); // �������� �츮ȸ�� ȸ�� ��
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		pagination.setTotalRecordCount(employeeList);
		ModelAndView mav = new ModelAndView("/partner/company/companyEmployee");
		mav.addObject("pagination", pagination);
		mav.addObject("userNum", companyUser);
		mav.addObject("courseUserNum", companyCourseUser);
		mav.addObject("pagination", pagination);
		mav.addObject("companyEmployeeList",partnerService.SelectAllListCompanyEmployee(pagination, partnerLicenseNum)); // �������� ȸ�� ����Ʈ������
		return mav;
	}

	@Override
	@RequestMapping(value="/partner/company/searchEmployee.do", method = RequestMethod.GET)
	public ModelAndView searchCompanyEmployee(String partnerLicenseNum, int currentPage, int cntPerPage, int pageSize,
			String userName, String syllabusName, String courseStartDate, String completionDate,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int searchList = partnerService.searchEmployeeTableCount(partnerLicenseNum, userName, syllabusName, courseStartDate, completionDate);
		int companyUser = partnerService.companyUserNum(partnerLicenseNum);
		int companyCourseUser = partnerService.companyCourseUserNum();
		
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		pagination.setTotalRecordCount(searchList);
		
		ModelAndView mav = new ModelAndView("/partner/company/searchEmployee");
		mav.addObject("pagination", pagination);
		mav.addObject("userNum", companyUser);
		mav.addObject("courseUserNum", companyCourseUser);
		mav.addObject("companyEmployeeList", partnerService.SearchListCompanyEmployee(pagination, partnerLicenseNum,userName, syllabusName, courseStartDate, completionDate)); //ã�� ���� ����Ʈ
		mav.addObject("userName",userName);
		mav.addObject("syllName",syllabusName);
		mav.addObject("startDate",courseStartDate);
		mav.addObject("p_num",partnerLicenseNum);
		mav.addObject("completionDate",completionDate);
		
		return mav;
	}

	@Override
	@RequestMapping(value="/partner/company/companyApplyManage.do", method = RequestMethod.GET)
	public ModelAndView companyApplyManage(String partnerLicenseNum,
			/*
			 * int currentPage, int cntPerPage, int pageSize, Map<String, Object> map,
			 */ HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/partner/company/companyApplyManage");
		
		return mav;
	}

	@Override
	@RequestMapping(value="/partner/company/infoGraph.do", method = RequestMethod.GET)
	
	public ModelAndView companyInfoGraph(@RequestParam("partnerLicenseNum") String partnerLicenseNum,HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mav = new ModelAndView();
		List list = partnerService.infoGraph(partnerLicenseNum);
		mav.addObject("datas",list);
		mav.setViewName("jsonView");
		return mav;
	}
	
	/* ===================================���»� ���� ��==============================*/
	
	
}
