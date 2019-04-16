package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.manager.BaseAttrInfo;
import com.atguigu.gmall.manager.BaseAttrInfoService;
import com.atguigu.gmall.manager.BaseAttrValue;
import com.atguigu.gmall.manager.vo.BaseAttrInfoAndValueVO;
import com.atguigu.gmall.manager.vo.BaseAttrValueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("attr")
@Controller
public class AttrManagerController {

    @Reference
    BaseAttrInfoService baseAttrInfoService;

    @ResponseBody
    @RequestMapping("/updates")
    public String  saveOrUpdateOrDeleteAttrInfoAndValue(@RequestBody BaseAttrInfoAndValueVO baseAttrInfoAndValueVO){

        log.info("页面提交来的数据：{}",baseAttrInfoAndValueVO);

        //1、修改还是添加
//        if(baseAttrInfoAndValueVO.getId()!=null){  不用在controller中判断是修改还是添加，我们直接在service里面全部处理
        //controller这里负责整理好数据传递过去即可
        //修改,检查新提交的属性名不能是空串.....
        //1、修改基本属性名
        //2、修改这个属性对应的所有的值
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        //将vo中的所有属性复制到bean中
        BeanUtils.copyProperties(baseAttrInfoAndValueVO,baseAttrInfo);

        List<BaseAttrValue> values = new ArrayList<>();
        //遍历页面上的数据vo
        for (BaseAttrValueVO baseAttrValueVo : baseAttrInfoAndValueVO.getAttrValues()) {
            //将这个vo里面的数据封装到BaseAttrValue这个对象
            BaseAttrValue baseAttrValue = new BaseAttrValue();
            BeanUtils.copyProperties(baseAttrValueVo,baseAttrValue);
            values.add(baseAttrValue);
        };
        //将复制好的list设置在attrInfo中
        baseAttrInfo.setAttrValues(values);
        log.info("复制属性完成：{}",baseAttrInfo);

        //以上数据整理完成，调用远程service进行处理
        baseAttrInfoService.saveOrUpdateBaseInfo(baseAttrInfo);
        return "OK";
    }

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
