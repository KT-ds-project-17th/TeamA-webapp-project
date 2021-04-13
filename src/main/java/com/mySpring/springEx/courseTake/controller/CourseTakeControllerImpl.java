package com.mySpring.springEx.courseTake.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.springEx.common.pagination.Pagination;
import com.mySpring.springEx.courseTake.service.CourseTakeService;
import com.mySpring.springEx.courseTake.vo.CourseTakeVO;

@Controller("courseTakeController")
//@EnableAspectJAutoProxy
public class CourseTakeControllerImpl implements CourseTakeController {
	@Autowired
	private CourseTakeService courseTakeService;
	@Autowired
	CourseTakeVO courseTakeVO;
	/*
	 * @RequestMapping(value = "/courseTake/courseApplyList.do", method =
	 * RequestMethod.GET) public ModelAndView courseApplyList(HttpServletRequest
	 * request, HttpServletResponse response) { List courseApplyList =
	 * courseTakeService.courseApplyList(); String viewName = (String)
	 * request.getAttribute("viewName"); ModelAndView mav = new ModelAndView();
	 * mav.setViewName(viewName); mav.addObject("courseApplyList", courseApplyList);
	 * return mav; }
	 */
	
	@Override
	@RequestMapping(value = "/courseTake/courseApplyList.do", method = RequestMethod.GET)
	public ModelAndView courseApplyList(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "20") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
            Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		
		//�������� �� ������ �޾ƿ� courseTakeServiceImpl testTableCountCT()-pagination.xml�� testTableCountCT ������ ���� ���� courseApplyList�� ����(int��)
		int courseApplyList = courseTakeService.testTableCountCT();
		//Pagination�� request�� currentPage,cntPerPage,pageSize�� �Ķ���Ͱ����� �޴� ��ü�� ����
		Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		//�� ���ڵ� ���� ���� ������ ó�� method�� �������� �� ������ ����
		pagination.setTotalRecordCount(courseApplyList);
		ModelAndView mav = new ModelAndView(viewName);
		//ó���� �κ��� ȭ�鿡 ����
		mav.addObject("pagination",pagination);
		mav.addObject("courseApplyList", courseTakeService.selectAllCourseApplyList(pagination));
		/* mav.setViewName(viewName); */
		return mav;
	}

	//�׽�Ʈ������
	@RequestMapping(value = "/courseTake/courseCompleteList.do", method = RequestMethod.GET)
	public ModelAndView courseCompleteList(HttpServletRequest request, HttpServletResponse response) {
		List courseCompleteList = courseTakeService.courseCompleteList();
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("courseCompleteList", courseCompleteList);
		return mav;
	}

}
