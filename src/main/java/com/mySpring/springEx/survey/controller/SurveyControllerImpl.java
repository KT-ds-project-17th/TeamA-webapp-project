
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

	// 리스트
	@Override
	@RequestMapping(value = "/survey/listSurvey.do", method = RequestMethod.GET)
	public ModelAndView listSurvey(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		// 데이터의 총 갯수를 받아옴 surveyServiceImpl testTableCount()-pagination.xml의
		// testTableCount 쿼리를 담은 값을 surveyList에 담음(int형)
		int surveyList = surveyService.testTableCount();
		// Pagination에 request한 currentPage,cntPerPage,pageSize을 파라미터값으로 받는 객체를 생성
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		// 총 레코드 수에 따른 페이지 처리 method에 데이터의 총 갯수를 전달
		pagination.setTotalRecordCount(surveyList);
		ModelAndView mav = new ModelAndView(viewName);
		// 추가할 항목을 미리 세션에 저장시키기 위한 surveyList= insertList
		/* mav.addObject("insertSurvey", surveyService.SelectInsertList()); */
		// 처리된 부분을 화면에 전달
		mav.addObject("pagination", pagination);
		mav.addObject("surveyList", surveyService.SelectAllList(pagination));
		/* mav.setViewName(viewName); */
		return mav;
	}

	// 추가
	@Override
	@RequestMapping(value = "/survey/addSurvey.do", method = RequestMethod.POST)
	public ModelAndView addSurvey(@ModelAttribute("survey") SurveyVO survey, // modelAttritbute로 회원가입창에서 받은 member정보를 //
																				// MemberVO클래스의 member객체에 저장
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = surveyService.addSurvey(survey);
		ModelAndView mav = new ModelAndView("redirect:/survey/listSurvey.do");
		return mav;
	}

	// 설문조사 삭제
	@Override
	@RequestMapping(value = "/survey/removeSurvey.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("survey_Id") String survey_Id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		surveyService.removeSurvey(survey_Id);
		ModelAndView mav = new ModelAndView("redirect:/survey/listSurvey.do");
		return mav;
	}

	// 설문지 작성
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

	// 설문지작성완료
	@RequestMapping(value = "/survey/insertSurvey.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView insertSurvey(@ModelAttribute DetailVO detailVO, @RequestParam("userId") String userId,
			RedirectAttributes rttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		surveyService.insertSurvey(detailVO);
		ModelAndView mav = new ModelAndView("redirect:/member/myInfo.do?userID=" + userId);
		return mav;
	}

	// 설문지 생성
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
