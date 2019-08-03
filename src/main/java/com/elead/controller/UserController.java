package com.elead.controller;

import com.elead.pojo.SmbmsUser;
import com.elead.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public Map<String, Object> getUserList(Integer pageNum, Integer pageSize, SmbmsUser user, String usercode) {
        System.out.println(pageNum + "=================");
        System.out.println(pageSize + "=================");
        System.out.println(user.toString() + "=================");
        // 开始分页

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SmbmsUser> allUser = userService.getAllUser(user);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 总条数
        map.put("total", allUser.getTotal());
        // 结果集
        map.put("rows", allUser.getList());
        // 当前页
        map.put("pageNum", allUser.getPageNum());
        // 每页的数量
        map.put("pageSize", allUser.getPageSize());
        // 当前页的数量
        map.put("size", allUser.getSize());
        // 总页数
        map.put("pages", allUser.getPages());
        // 所有导航页号
        map.put("navigatepageNums", allUser.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", allUser.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", allUser.isIsLastPage());
        System.out.println("开始遍历用户");
        System.err.println("订单管理后台信息：" + map);
        return map;
    }

    @RequestMapping("/selectUser")
    @ResponseBody
    public Map<String, Object> selectUser(Integer pageNum, Integer pageSize, SmbmsUser user, String usercode) {
        System.out.println(pageNum + "=================");
        System.out.println(pageSize + "=================");
        System.out.println(usercode + "=================");
        //System.out.println(userrole + "=================");

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SmbmsUser> allUser = userService.selectUser(user);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("usercode", usercode);
        // 总条数
        map.put("total", allUser.getTotal());
        // 结果集
        map.put("rows", allUser.getList());
        // 当前页
        map.put("pageNum", allUser.getPageNum());
        // 每页的数量
        map.put("pageSize", allUser.getPageSize());
        // 当前页的数量
        map.put("size", allUser.getSize());
        // 总页数
        map.put("pages", allUser.getPages());
        // 所有导航页号
        map.put("navigatepageNums", allUser.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", allUser.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", allUser.isIsLastPage());
        System.out.println("开始遍历用户");
        System.err.println("订单管理后台信息：" + map);
        return map;
    }

    @RequestMapping("/userexist")
    @ResponseBody
    public String userexist(Long id) {
        SmbmsUser user = userService.selectUserById(id);
        if (user != null)
            return "exist";
        else
            return "notexist";
    }

    @RequestMapping("/getUserrole")
    @ResponseBody
    public Map<String, Object> getUserrole() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", userService.getUserrole());
        return map;
    }

    /**
     * 修改用户密码
     */
    @RequestMapping("/user.do")
    @ResponseBody
    public Object userdo(String newpassword, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        SmbmsUser newuser = (SmbmsUser) session.getAttribute("user");
        newuser.setUserpassword(newpassword);
        //修改用户密码，然后查询用户信息
        SmbmsUser user = userService.userdoService(newuser);
        System.out.println(user.toString());
        //将用户信息重新存入session中
        if (user != null) {
            session.setAttribute("user", user);
            //session.setAttribute("userpassword",newpassword);
            //将密码是否修改成功标识返回给页面
            map.put("status", "1");
        } else {
            map.put("status", "0");
        }
        return map;
    }

    /**
     * 修改用户信息
     */
    @RequestMapping("/userupdate")
    public Object userupdate(SmbmsUser user,RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("userlist");
        //modelAndView.setViewName("/userlist");
        System.out.println(user.toString());
//        Map<String, Object> map = new HashMap<String, Object>();
        int i = userService.userUpdate(user);
        if (i == 1)
            ra.addFlashAttribute("updatestatus", "true");
        else if (i == 0)
            ra.addFlashAttribute("updatestatus", "false");
        return "redirect:/jsp/userlist";
    }

    @RequestMapping("/userview")
    @ResponseBody
    public Object userview(String usercode) {
        SmbmsUser user = userService.selectUser_Smbms(usercode);
        return user;
    }

    @RequestMapping("/useradd")
    public Object useradd(SmbmsUser user, RedirectAttributes ra) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/jsp/userlist");
        //Map<String, Object> map = new HashMap<String, Object>();
        int i = userService.addUser(user);
        if (i == 1)
            ra.addFlashAttribute("addstatus", "true");
        else if (i == 0)
            ra.addFlashAttribute("addstatus", "false");
        return "redirect:/jsp/userlist";
    }

    //用户编辑界面转向函数
    @RequestMapping("/modifyUser")
    public String modifyUser(@RequestParam(value = "usercode") String usercode, HttpServletRequest request) {
        SmbmsUser user=userService.selectUser_Smbms(usercode);
        request.setAttribute("modifyusercode", usercode);
        request.setAttribute("modifyuserrole", user.getUserrole());
        request.setAttribute("modifygender", user.getGender());
        return "/usermodify";
    }

    //添加用户界面转向函数
    @RequestMapping("/openuseradd")
    public String openuseradd(SmbmsUser user) {
        return "/useradd";
    }

    @RequestMapping("/openuserview")
    public String openuserview(@RequestParam(value = "usercode") String usercode, HttpSession session, HttpServletRequest request) {
        request.setAttribute("viewusercode", usercode);
        return "/userview";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Object user(Long userid, HttpSession session) {
        System.out.println(userid);
        Map<String, Object> map = new HashMap<String, Object>();
        int i = userService.deleteUser(userid);
        if (i == 1)
            map.put("status", "true");
        else if (i == 0)
            map.put("status", "false");
        return map;
    }
}
