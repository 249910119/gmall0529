package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.manager.SpuInfoService;
import com.atguigu.gmall.manager.spu.BaseSaleAttr;
import com.atguigu.gmall.manager.spu.SpuInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/spu")
public class SpuManagerController {

    @Reference
    SpuInfoService spuInfoService;

    @ResponseBody
    @RequestMapping("/bigSave")
    public String saveAllSpuInfos(@RequestBody SpuInfo spuInfo){

        log.info("页面提交的spuInfo数据如下：{}",spuInfo);
        spuInfoService.saveBigSpuInfo(spuInfo);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/base_sale_attr")
    public List<BaseSaleAttr> getBaseSaleAttr(){
        return spuInfoService.getBaseSaleAttr();
    }

    @ResponseBody
    @RequestMapping("/info.json")
    public List<SpuInfo> getSpuInfoByC3Id(@RequestParam("catalog3Id") Integer catalog3Id){

        return spuInfoService.getSpuInfoByC3Id(catalog3Id);
    }

    @RequestMapping("/listPage.html")
    public String spuListPage(){
        return "spu/spuListPage";
    }
}
