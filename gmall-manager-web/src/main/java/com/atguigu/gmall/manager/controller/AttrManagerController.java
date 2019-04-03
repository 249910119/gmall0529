package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.manager.BaseAttrInfoService;
import com.atguigu.gmall.manager.BaseAttrValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@RequestMapping("attr")
@Controller
public class AttrManagerController {

    @Reference
    BaseAttrInfoService baseAttrInfoService;

    @ResponseBody
    @RequestMapping("/value/{id}")
    public List<BaseAttrValue> getBaseAttrValueByAttrId(@PathVariable("id") Integer id){
        return baseAttrInfoService.getBaseAttrValueByAttrId(id);
    }

    @RequestMapping("/listPage.html")
    public String toAttrListPage(){
        System.out.println("去 attrListPage 页面");
        return "attr/attrListPage";
    }
}
