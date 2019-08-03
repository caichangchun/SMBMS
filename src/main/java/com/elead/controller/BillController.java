package com.elead.controller;

import com.elead.pojo.SmbmsBill;
import com.elead.pojo.SmbmsProvider;
import com.elead.service.BillService;
import com.elead.service.ProviderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp")
public class BillController {

    @Autowired
    private BillService billService;
    @Autowired
    private ProviderService providerService;

    @RequestMapping("/getBillList")
    @ResponseBody
    public Map<String, Object> getBillList(Integer pageNum, Integer pageSize, SmbmsBill bill) {
        System.out.println(pageNum + "=================");
        System.out.println(pageSize + "=================");
        System.out.println(bill.toString() + "=================");
        // 开始分页
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<SmbmsBill> allBill = billService.getAllBill(bill);
        // 新建一个map集合用于存储数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 总条数
        map.put("total", allBill.getTotal());
        // 结果集
        map.put("rows", allBill.getList());
        // 当前页
        map.put("pageNum", allBill.getPageNum());
        // 每页的数量
        map.put("pageSize", allBill.getPageSize());
        // 当前页的数量
        map.put("size", allBill.getSize());
        // 总页数
        map.put("pages", allBill.getPages());
        // 所有导航页号
        map.put("navigatepageNums", allBill.getNavigatepageNums());
        // 是否为首页
        map.put("isIsFirstPage", allBill.isIsFirstPage());
        // 最后一页
        map.put("isIsLastPage", allBill.isIsLastPage());
        System.out.println("开始遍历商品");
        System.err.println("订单管理后台信息：" + map);
        return map;

    }

    @RequestMapping("/bill.do")
    @ResponseBody
    public Object billdo(String method, Long billid, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView modelAndView = new ModelAndView();
        SmbmsBill smbmsBill = billService.selectByPrimaryKey(billid);
        switch (method) {
            case "delbill": {
                int i = billService.deleteByPrimaryKey(billid);
                if (i == 1)
                    map.put("delResult", "true");
                else
                    map.put("delResult", "false");
                return map;
            }
            case "view": {
                modelAndView.setViewName("billview");
                modelAndView.addObject("viewbill", smbmsBill);
                return modelAndView;
            }
            case "modify": {
                modelAndView.setViewName("billmodify");
                modelAndView.addObject("modifybill", smbmsBill);
                return modelAndView;
            }
            case "getproviderlist": {
                List<SmbmsProvider> list = providerService.getAllProvider();
                return list;
            }
            default:
                return null;
        }
    }

    @RequestMapping("/updatebill")
    public String updatebill(SmbmsBill bill, RedirectAttributes ra) {
        int i = billService.updateByPrimaryKeySelective(bill);
        if (i == 1) {
            ra.addFlashAttribute("updatestatus", "true");
        } else
            ra.addFlashAttribute("updatestatus", "false");
        return "redirect:/jsp/billlist";
    }

    @RequestMapping("/addbill")
    public String addbill(SmbmsBill bill, RedirectAttributes ra) {
        bill.setCreationdate(new Date());
        int i = billService.insert(bill);
        if (i == 1) {
            ra.addFlashAttribute("addstatus", "true");
        } else
            ra.addFlashAttribute("addstatus", "false");
        return "redirect:/jsp/billlist";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SmbmsBill> billlist = billService.resolveExcel(file);
        if(billlist==null){
            map.put("code", "fail");
            return map;
        }
        if (billService.insertList(billlist) == 1) {
            map.put("code", "success");
        } else {
            map.put("code", "fail");
        }
        return map;
    }

    @RequestMapping("/download")
    @ResponseBody
    public String download(HttpServletResponse response) {
//        String downloadFilePath = "./excelModel/";//被下载的文件在服务器中的路径,
        String fileName=new String();
        try {
            fileName = new String("导入订单 - 模版.xlsx".getBytes("UTF-8"),"iso-8859-1");//被下载文件的名称
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File file = new File("./excelModel/导入订单 - 模版.xlsx");
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开            
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }

}
