package com.qunjie.fanwei.controller;

import com.qunjie.fanwei.webservice.dept.CompanyService;
import com.qunjie.fanwei.webservice.dept.DeptService;
import com.qunjie.fanwei.webservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class OaTestController {

    @Autowired
    private DeptService deptService;

    @Autowired
    CompanyService companyService;

    @Autowired
    private UserService userService;

    @GetMapping("/allCompany")
    public List<CompanyService.Company> testCom(){
        return companyService.getHrmDepartmentInfo();
    }

    @RequestMapping("/allDept")
    public List<DeptService.Deparment> test(Integer subcompanyId){
        return deptService.getHrmDepartmentInfo(subcompanyId);
    }

    @RequestMapping("/allDept2")
    public List<DeptService.Deparment> test2(String subcompanyId){
        return deptService.getHrmDepartmentInfo2(subcompanyId);
    }

    @RequestMapping("/allUser")
    public List<UserService.User> list(@RequestParam(value = "workcode",required = false) String workcode, @RequestParam(value = "subcompanyId",required = false) String subcompanyId,
                                       @RequestParam(value = "departmentid",required = false) String departmentid, @RequestParam(value = "jobtitleid",required = false) String jobtitleid){
        return userService.getHrmUserInfo(workcode,subcompanyId,departmentid,jobtitleid);
    }

    @RequestMapping("/allUser2")
    public List<UserService.User> list2(){
        return userService.getHrmUserInfo2();
    }

    @RequestMapping("setCookie")
    public void setCookie(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
        Cookie cookie = new Cookie("wanghongsheng","demo");
        cookie.setDomain("www.baidu.com");
        URLEncoder.encode("wanghongsheng","utf-8");
        response.addCookie(cookie);
//        response.sendRedirect("http://localhost:8075");
//        ModelAndView modelAndView = new ModelAndView("forward:http://localhost:8080");
        return ;
    }

    @RequestMapping("getCookie")
    public String getCookie(HttpServletRequest request , HttpServletResponse response){
        System.out.println("=============================================================");
        Cookie[] cookies = request.getCookies();
        StringBuffer stringBuffer = new StringBuffer("");
        if (cookies.length>0) {
            for (int i = 0; i < cookies.length; i++) {
                stringBuffer.append(cookies[i].getName() + "=" + cookies[i].getValue() + "\r");
            }
        }
        return stringBuffer.toString();
    }

    @PostMapping("testCrm")
    public void testCrm(@RequestBody Map<String,Object> map){
        System.out.println("=========================testCrm1===================================");
        map.forEach((k,v)->{
            System.out.println(k+"======="+v);
        });
    }

    @GetMapping("testCrm2")
    public void testCrm(){
        System.out.println("===================================testCrm2===========================");
    }

    @GetMapping("testCrm3")
    public void testCrm3(String string){
        System.out.println("===================================testCrm3===========================");
        System.out.println("========================string===================" + string);
    }

    @PostMapping("testCrm4")
    public void testCrm4(){
        System.out.println("=========================testCrm4==================================");
    }
}
