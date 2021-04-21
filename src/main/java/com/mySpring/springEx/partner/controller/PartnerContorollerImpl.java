package com.mySpring.springEx.partner.controller;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    //	post job opening
    @Override
    @RequestMapping(value = "/partner/jobOpeningPost.do", method = RequestMethod.GET)
    public ModelAndView jobOpeningPost(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");

        int partnerApplyNList = partnerService.testTableCountPartnerApplyN();

        Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);

        pagination.setTotalRecordCount(partnerApplyNList);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("pagination", pagination);
        mav.addObject("partnerApplyNList", partnerService.selectPartnerApplyN(pagination));

        return mav;
    }

    @Override
    @RequestMapping(value = "/partner/jobOpeningList.do", method = RequestMethod.GET)
    public ModelAndView jobOpeningList (@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
                                        @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                        Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");

        int jobOpeningList = partnerService.testTableCountJobOpeningList();

        Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);

        pagination.setTotalRecordCount(jobOpeningList);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("pagination", pagination);
        mav.addObject("jobOpeningList", partnerService.selectJobOpeningList(pagination));
//        mav.addObject("applicantNum", partnerService.getApplicantNum());
        return mav;
    }

    // ȸ�� ���� �Է�
    @Override
    @RequestMapping(value = "/partner/addPartner.do", method = {RequestMethod.GET, RequestMethod.POST})
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
    @RequestMapping(value = "/partner/modPartner.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView modPartner(@ModelAttribute("partner") PartnerVO partner, RedirectAttributes rttr,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        partnerService.modPartner(partner);
        rttr.addFlashAttribute("msg", "modSuccess");
        rttr.addFlashAttribute("partnerName", partner.getPartnerName());
        ModelAndView mav = new ModelAndView("redirect:/partner/partnerList.do");

        return mav;
    }

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

    // ��� ����
    @Override
    @RequestMapping(value = "/partner/deletePartner.do", method = {RequestMethod.GET, RequestMethod.POST})
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

    @Override
    @RequestMapping(value = "/partner/postJobOpening.do", method = RequestMethod.POST)
    public ModelAndView postJobOpening(@RequestParam List<String> valueArr) {
        String date = valueArr.get(0);
        for (int i = 1; i < valueArr.size(); i++) {
            partnerService.postJobOpening(valueArr.get(i), date);
        }
        ModelAndView mav = new ModelAndView("redirect:/partner/jobOpeningPost.do");
        return mav;
    }

    @Override
    @RequestMapping(value = "/partner/deleteJobOpening.do", method = RequestMethod.POST)
    public ModelAndView deleteJobOpening(@RequestParam List<String> valueArr) {
        for (int i = 0; i < valueArr.size(); i++) {
            partnerService.deleteJobOpening(valueArr.get(i));
        }
        ModelAndView mav = new ModelAndView("redirect:/partner/jobOpeningList.do");
        return mav;
    }

    @Override
    public ModelAndView companyInfo(String partnerLicenseNum,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String viewName = (String)
                request.getAttribute("viewName");

        /*
         * partnerVO = partnerService.getCompanyInfo(partnerLicenseNum);
         */
        ModelAndView mav = new ModelAndView(viewName);

        return mav;
    }


}
