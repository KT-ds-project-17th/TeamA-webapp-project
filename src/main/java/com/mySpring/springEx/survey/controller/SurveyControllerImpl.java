
package com.mySpring.springEx.survey.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.course.vo.CourseVO;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;
import com.mySpring.springEx.member.vo.MemberVO;
import com.mySpring.springEx.survey.service.SurveyService;
import com.mySpring.springEx.survey.vo.DetailVO;
import com.mySpring.springEx.survey.vo.SurveyVO;
import com.mySpring.springEx.syllabus.vo.SyllabusVO;

@Controller("surveyController")
public class SurveyControllerImpl implements SurveyController {

	@Autowired
	private SurveyService surveyService;

	@Autowired
	SurveyVO surveyVO;

	@Autowired
	MemberVO memberVO;

	@Autowired
	CourseTakeVO courseTakeVO;

	@Autowired
	CourseVO courseVO;

	@Autowired
	DetailVO detailVO;

	@Autowired
	SyllabusVO syllabusVO;

	// ����Ʈ
	@Override
	@RequestMapping(value = "/survey/listSurvey.do", method = RequestMethod.GET)
	public ModelAndView listSurvey(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// �������� �� ������ �޾ƿ� surveyServiceImpl testTableCount()-pagination.xml��
		// testTableCount ������ ���� ���� surveyList�� ����(int��)
		int surveyList = surveyService.testTableCount();
		// Pagination�� request�� currentPage,cntPerPage,pageSize�� �Ķ���Ͱ����� �޴� ��ü�� ����
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		// �� ���ڵ� ���� ���� ������ ó�� method�� �������� �� ������ ����
		pagination.setTotalRecordCount(surveyList);
		ModelAndView mav = new ModelAndView(viewName);
		// �߰��� �׸��� �̸� ���ǿ� �����Ű�� ���� surveyList= insertList
		/* mav.addObject("insertSurvey", surveyService.SelectInsertList()); */
		// ó���� �κ��� ȭ�鿡 ����
		mav.addObject("pagination", pagination);
		mav.addObject("surveyList", surveyService.SelectAllList(pagination));
		/* mav.setViewName(viewName); */
		return mav;
	}

	// �߰�
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

	// ������ �ۼ�
	@RequestMapping(value = "/survey/surveyWriteForm.do", method = RequestMethod.GET)
	public ModelAndView surveyWriteForm(@SessionAttribute("member") MemberVO member,
			@RequestParam("userID") String userID, @RequestParam("courseID") String courseID,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		SurveyVO surveyVO = surveyService.getQuestion(courseID);
		CourseTakeVO courseTakeVO = surveyService.selectAllSurveylist(userID, courseID);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("myInfo", member);
		mav.addObject("allSurveyList", courseTakeVO);
		mav.addObject("getQuestion",surveyVO);
		HttpSession session = request.getSession();
		session.setAttribute("allSurveyList", courseTakeVO);
		return mav;
	}

	// �������ۼ��Ϸ�
	@RequestMapping(value = "/survey/insertSurvey.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView insertSurvey(@ModelAttribute DetailVO detailVO, @RequestParam("userId") String userId,
			RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		surveyService.insertSurvey(detailVO);
		ModelAndView mav = new ModelAndView("redirect:/member/myInfo.do?userID=" + userId);
		return mav;
	}

	// ������ ����
	@Override
	@RequestMapping(value = "/survey/writeSurveyForm.do", method = RequestMethod.GET)
	public ModelAndView writeSurvey(@RequestParam("syllabusID") String syllabusID,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String viewName = (String) request.getAttribute("viewName");
		courseVO = surveyService.writeSurvey(syllabusID);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("courseVO", courseVO);
		return mav;
	}

	@RequestMapping(value = "/survey/surveyInsert.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView surveyInsert(@ModelAttribute SurveyVO surveyVO, @RequestParam("courseID") String courseID,
			RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		surveyService.surveyInsert(surveyVO);
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		return mav;
	}


}
