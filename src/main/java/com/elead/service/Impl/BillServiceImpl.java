package com.elead.service.Impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.elead.dao.SmbmsStatisticalMapper;
import com.elead.pojo.SmbmsStatistical;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elead.dao.SmbmsBillMapper;
import com.elead.pojo.SmbmsBill;
import com.elead.service.BillService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private SmbmsBillMapper smbmsbillmapper;
    @Autowired
    private SmbmsStatisticalMapper smbmsStatisticalMapper;

    @Override
    public  int insertList(List<SmbmsBill> list){
        try {
            for (SmbmsBill bill : list) {
                smbmsbillmapper.insert(bill);
            }
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public  List<SmbmsStatistical> getTopnProduct(Date date){
        return smbmsStatisticalMapper.getTopnProduct(date);
    }

    @Override
    public  List<SmbmsStatistical> getAllTopnProduct(){ return smbmsStatisticalMapper.getAllTopnProduct(); }

    @Override
    public  List<SmbmsStatistical> getDayTotalPrice(Date date){ return smbmsStatisticalMapper.getDayTotalPrice(date); }

    @Override
    public  List<SmbmsStatistical> getProductPercent(){ return smbmsStatisticalMapper.getProductPercent(); }

    @Override
    public  List<SmbmsStatistical> getDayProductPercent(Date date){ return smbmsStatisticalMapper.getDayProductPercent(date); }

    @Override
    public  List<SmbmsStatistical> getBillCount(Date date){ return smbmsStatisticalMapper.getBillCount(date); }

    @Override
    public  SmbmsBill selectByPrimaryKey(Long id){
        return smbmsbillmapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SmbmsBill bill){
        return smbmsbillmapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public  int deleteByPrimaryKey(Long id){
        return smbmsbillmapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SmbmsBill bill){
        return smbmsbillmapper.insert(bill);
    }

    @Override
    public PageInfo<SmbmsBill> getAllBill(SmbmsBill bill) {
        // TODO Auto-generated method stub
        List<SmbmsBill> allBill = smbmsbillmapper.getAllBill(bill);
        PageInfo<SmbmsBill> pageInfo = new PageInfo<SmbmsBill>(allBill);
        return pageInfo;
    }

    //解析excel
    @Override
    public  List<SmbmsBill> resolveExcel(MultipartFile file) {
        String SUFFIX_2003 = ".xls";
        String SUFFIX_2007 = ".xlsx";
//        /**
//         * 电话的正则
//         */
//        String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[01356789]|18[0-9]|19[89])\\d{8}$";
//        /**
//         * 密码长度
//         */
//        int passWardLength = 6;


        List<SmbmsBill> list = new ArrayList<>();
        if (file == null) {
            System.out.println("对象不能为空！");
        }
        //获取文件的名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            //logger.info(originalFilename);
            e.printStackTrace();
            System.out.println("格式错误！");
        }
        if (workbook == null) {
            //logger.info(originalFilename);
            System.out.println("格式错误！");
        } else {
            //获取所有的工作表的的数量
            int numOfSheet = workbook.getNumberOfSheets();
            //遍历这个这些表
            for (int i = 0; i < numOfSheet; i++) {
                //获取一个sheet也就是一个工作簿
                Sheet sheet = workbook.getSheetAt(i);
                int lastRowNum = sheet.getLastRowNum();
                //从第一行开始第一行一般是标题
                Row row0 = sheet.getRow(0);
                if(!("订单编码".equals(row0.getCell(0).toString())&&"商品名称".equals(row0.getCell(1).toString())&&"商品单位".equals(row0.getCell(2).toString())&&"商品数量".equals(row0.getCell(3).toString())&&"总金额".equals(row0.getCell(4).toString())&&"供应商".equals(row0.getCell(5).toString())&&"是否付款".equals(row0.getCell(6).toString()))){
                    return null;
                }
                for (int j = 1; j < lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    SmbmsBill smbmsBill = new SmbmsBill();

                    //获取订单编码元格
                    if (row.getCell(0) != null) {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        String billcode = row.getCell(0).getStringCellValue();
                        //todo 正则比对
//                        boolean matche = Pattern.matches(PHONE_NUMBER_REG, billcode);
//                        if (!matche) {
//                            System.out.println("电话格式错误");
//                        }
                        smbmsBill.setBillcode(billcode);
                    }
                    else return null;
                    //商品名称
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        String productname = row.getCell(1).getStringCellValue();
//                        if (productname.replace("", "").length() < passWardLength) {//校验密码长度
//                            System.out.println("密码的格式有误");
//                        }
                        smbmsBill.setProductname(productname);
                    }else return null;
                    //商品单位
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                        String productunit = row.getCell(2).getStringCellValue();
                        smbmsBill.setProductunit(productunit);
                    }else return null;
                    //商品数量
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        BigDecimal productcount = new BigDecimal(row.getCell(3).getStringCellValue());
                        smbmsBill.setProductcount(productcount);
                    }else return null;
                    //总金额
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        BigDecimal totalprice = new BigDecimal(row.getCell(4).getStringCellValue());
                        smbmsBill.setTotalprice(totalprice);
                    }else return null;
                    //供应商
                    if (row.getCell(5) != null) {
                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                        Long providerid = Long.parseLong(row.getCell(5).getStringCellValue());
                        smbmsBill.setProviderid(providerid);
                    }else return null;
                    //是否付款
                    if (row.getCell(6) != null) {
                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                        Integer ispayment = Integer.parseInt(row.getCell(6).getStringCellValue());
                        smbmsBill.setIspayment(ispayment);
                    }else return null;
                    //创建日期
                    if (row.getCell(7) != null) {
                        //row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date=row.getCell(7).getDateCellValue();
//                        try {
//                            date = sdf.parse(row.getCell(7).getCellStyle().getDataFormatString());
//                        }catch(Exception e){
//                            e.printStackTrace();
//                        }
                        //Date date=new Date();
                        smbmsBill.setCreationdate(date);
                    }
                    list.add(smbmsBill);
                }
            }
        }
        return list;
    }


}
