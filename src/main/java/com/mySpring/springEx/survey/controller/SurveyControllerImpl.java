
package com.mySpring.springEx.survey.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.survey.service.SurveyService;
import com.mySpring.springEx.survey.vo.SurveyVO;

@Controller("surveyController")
public class SurveyControllerImpl implements SurveyController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	SurveyVO surveyVO;
	
	//����Ʈ
	@Override
	@RequestMapping(value = "/survey/listSurvey.do", method = RequestMethod.GET)
	public ModelAndView listSurvey(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		//�������� �� ������ �޾ƿ� surveyServiceImpl testTableCount()-pagination.xml�� testTableCount ������ ���� ���� surveyList�� ����(int��)
		int surveyList = surveyService.testTableCount();
		//Pagination�� request�� currentPage,cntPerPage,pageSize�� �Ķ���Ͱ����� �޴� ��ü�� ����
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		//�� ���ڵ� ���� ���� ������ ó�� method�� �������� �� ������ ����
		pagination.setTotalRecordCount(surveyList);
		ModelAndView mav = new ModelAndView(viewName);
		//�߰��� �׸��� �̸� ���ǿ� �����Ű�� ���� surveyList= insertList
		mav.addObject("insertSurvey",surveyService.SelectInsertList());
		//ó���� �κ��� ȭ�鿡 ����
		mav.addObject("pagination",pagination);
		mav.addObject("surveyList", surveyService.SelectAllList(pagination));
		/* mav.setViewName(viewName); */	
		return mav;
	}
	

	//�߰�
	@Override
	@RequestMapping(value = "/survey/addSurvey.do", method = RequestMethod.POST)
	public ModelAndView addSurvey(@ModelAttribute("survey") SurveyVO survey, // modelAttritbute�� ȸ������â���� ���� member������ //
																				// MemberVOŬ������ member��ü�� ����
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = surveyService.addSurvey(survey);
		ModelAndView mav = new ModelAndView("redirect:/survey/listSurvey.do");
		return mav;		
	}

	// �������� ����
	@Override
	@RequestMapping(value = "/survey/removeSurvey.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("survey_Id") String survey_Id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		surveyService.removeSurvey(survey_Id);
		ModelAndView mav = new ModelAndView("redirect:/survey/listSurvey.do");
		return mav;
	}

	
}
