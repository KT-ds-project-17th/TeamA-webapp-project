package com.mySpring.springEx.partner.controller;

import java.io.PrintWriter;
import java.util.List;

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

import com.mySpring.springEx.partner.service.PartnerService;
import com.mySpring.springEx.partner.vo.PartnerVO;


@Controller("partnerController")
public class PartnerContorollerImpl implements PartnerController{
	
	
	@Autowired
	private PartnerService partnerService;
	
	@Autowired
	PartnerVO partnervo;
	
	//ȸ�� ����Ʈ ���
	@Override
	@RequestMapping(value = "/partner/partnerList.do", method = RequestMethod.GET)
	public ModelAndView partnerList(HttpServletRequest request, HttpServletResponse response)throws Exception{ 
		String viewName = (String)request.getAttribute("viewName");
		List partnerList = partnerService.listPartner(); // ��� ����Ʈ�� select�ϴ� �޼ҵ带 ��Ʈ�ʼ��񽺿��� ȣ���Ͽ� ����Ʈ ���·� ����
		List numPartner = partnerService.listNumPartner(); //���»�, �����, ������, ���� ������ ���� count�ϴ� �޼ҵ带 ��Ʈ�� ���񽺿��� ȣ���Ͽ� ����Ʈ ���·� ����
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("partnerList",partnerList);			//select�� ��� ȸ�� ����Ʈ�� ���ε�
		mav.addObject("numCooperation",numPartner.get(0)); //���»� count�� ���ε�
		mav.addObject("numConvention",numPartner.get(1));  //����� count�� ���ε�
		mav.addObject("numIng",numPartner.get(2));			//���� ������ count�� ���ε�
		mav.addObject("numNot",numPartner.get(3));			//������ count�� ���ε�
		return mav;
	}
	
	
	//ȸ�� ���� �Է�
	@Override
	@RequestMapping(value = "/partner/addPartner.do",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView addPartner(@ModelAttribute("partner")PartnerVO partner,RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response)throws Exception{  //partnerVO�� ��ü�� �޾Ƽ� db�� �����ϴ� �޼ҵ�
		request.setCharacterEncoding("utf-8");
		partnerService.addPartner(partner); //��Ʈ�ʼ����� addPartner�� ȣ��
		rttr.addFlashAttribute("msg", "addSuccess");
		rttr.addFlashAttribute("partnerName",partner.getPartnerName());
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");  //addpartner�� �� �� �ٽ� partnerList�� ���ư��� ����
		return mav;
	}
	
	
	//ȸ�� ���� �Է� ��
	@Override
	@RequestMapping(value = "/partner/partnerForm.do", method = RequestMethod.GET)
	public ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception { //form���� �̵��ϴ� �޼ҵ�
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	
	//��� �� ���� 
	@Override
	@RequestMapping(value = "/partner/detailInfoPartner.do", method = RequestMethod.GET)
	public ModelAndView detailInfoPartner(@RequestParam("partnerLicenseNum") String partnerLicenseNum, HttpServletRequest request, HttpServletResponse response) throws Exception { //modform���� �̵��ϴ� �޼ҵ�
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		PartnerVO partnerVO;
		partnerVO = partnerService.partnerDetailInfo(partnerLicenseNum);
		mav.addObject("partnerVO",partnerVO);
		mav.setViewName(viewName);
		return mav;
	}
	
	//ȸ�� ���� ����
	@Override
	@RequestMapping(value = "/partner/modPartner.do",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView modPartner(@ModelAttribute("partner")PartnerVO partner,RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response)throws Exception{  
		request.setCharacterEncoding("utf-8");
		partnerService.modPartner(partner);
		rttr.addFlashAttribute("msg", "modSuccess");
		rttr.addFlashAttribute("partnerName",partner.getPartnerName());
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");
		
		
		return mav;
	}
	
	
	//��� ����
	@Override
	@RequestMapping(value = "/partner/deletePartner.do",method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView removePartner(@RequestParam("partnerLicenseNum") String partnerLicenseNum, RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response)throws Exception{  
		request.setCharacterEncoding("utf-8");
		String partnerName= partnerService.removePartner(partnerLicenseNum);
		System.out.println("�̸�"+partnerName);
		rttr.addFlashAttribute("msg", "removeSuccess");
		rttr.addFlashAttribute("partnerName",partnerName);
		ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");  
		return mav;
	}
	
	
	

}
