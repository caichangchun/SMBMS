package com.elead.controller;

import com.elead.pojo.SmbmsProvider;
import com.elead.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class ProviderController {
    @Autowired
    private ProviderService providerservice;

    @RequestMapping("/getProviderList")
    @ResponseBody
    public Map<String, Object> getProviderList(Integer pageNum, Integer pageSize, SmbmsProvider provider) {
        System.out.println(pageNum + "=================");
        System.out.println(pageSize + "=================");
        System.out.println(provider.toString() + "=================");

        if (pageNum == null && pageSize == null) {
            // 开始分页
            PageHelper.startPage(0, 0);
            PageInfo<SmbmsProvider> provider2 = providerservice.getProvider(provider);
            // 新建一个map集合用于存储数据
            Map<String, Object> map = new HashMap<String, Object>();
            // 总条数
            map.put("total", provider2.getTotal());
            // 结果集
            map.put("rows", provider2.getList());
            // 当前页的数量
            map.put("size", provider2.getSize());
            // 总页数
            map.put("pages", provider2.getPages());
            // 所有导航页号
            map.put("navigatepageNums", provider2.getNavigatepageNums());
            // 是否为首页
            map.put("isIsFirstPage", provider2.isIsFirstPage());
            // 最后一页
            map.put("isIsLastPage", provider2.isIsLastPage());
            System.err.println("供应商管理后台信息1：" + map);
            return map;
        } else {
            // 开始分页
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<SmbmsProvider> provider2 = providerservice.getProvider(provider);
            // 新建一个map集合用于存储数据
            Map<String, Object> map = new HashMap<String, Object>();
            // 总条数
            map.put("total", provider2.getTotal());
            // 结果集
            map.put("rows", provider2.getList());
            // 当前页
            map.put("pageNum", provider2.getPageNum());
            // 每页的数量
            map.put("pageSize", provider2.getPageSize());
            // 当前页的数量
            map.put("size", provider2.getSize());
            // 总页数
            map.put("pages", provider2.getPages());
            // 所有导航页号
            map.put("navigatepageNums", provider2.getNavigatepageNums());
            // 是否为首页
            map.put("isIsFirstPage", provider2.isIsFirstPage());
            // 最后一页
            map.put("isIsLastPage", provider2.isIsLastPage());
            System.err.println("供应商管理后台信息2：" + map);
            return map;
        }
    }

    @RequestMapping("/provider.do")
    @ResponseBody
    public Object providedo(String method, Long proid, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView modelAndView = new ModelAndView();
        switch (method) {
            case "delprovider": {
                Long count=providerservice.selectCountByPrimaryKey(proid);
                if(count==0){
                int i = providerservice.deleteByPrimaryKey(proid);
                if (i == 1)
                    map.put("delResult", "true");
                else
                    map.put("delResult", "false");
                return map;}
                else{
                    map.put("delResult", count);
                    return  map;
                }
            }
            case "view": {
                SmbmsProvider smbmsProvider = providerservice.selectByPrimaryKey(proid);
                //map.put("provider",smbmsProvider);
                //request.setAttribute("viewpeovider",smbmsProvider);
                modelAndView.setViewName("providerview");
                modelAndView.addObject("viewprovider", smbmsProvider);
                return modelAndView;
            }
            case "modify": {
                SmbmsProvider smbmsProvider = providerservice.selectByPrimaryKey(proid);
                modelAndView.setViewName("providermodify");
                modelAndView.addObject("modifyprovider", smbmsProvider);
                return modelAndView;
            }
            default:
                return null;
        }
    }

    @RequestMapping("/updateprovider")
    public String providerModify(SmbmsProvider provider,HttpServletRequest request,RedirectAttributes ra){
        int i=providerservice.updateByPrimaryKey(provider);
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("redirect:/jsp/providerlist");
        if(i==1){
            ra.addFlashAttribute("updatestatus","true");
            //System.out.println(((RedirectAttributesModelMap) ra).get("updatestatus"));
        }
        else
            ra.addFlashAttribute("updatestatus","false");
        return  "redirect:/jsp/providerlist/#";
    }

    @RequestMapping("/addprovider")
    public String addprovider(SmbmsProvider provider,RedirectAttributes ra){
        provider.setCreationdate(new Date());
        int i=providerservice.addProvider(provider);
//        ModelAndView mv=new ModelAndView();
//        mv.setViewName("redirect:/jsp/providerlist");
        //RedirectAttributes ra=new RedirectAttributesModelMap();
        if(i==1)
            ra.addFlashAttribute("addstatus","true");
        else
            ra.addFlashAttribute("addstatus","false");
        return  "redirect:/jsp/providerlist/#";
    }
}
