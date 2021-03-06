package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.manager.BaseAttrInfo;
import com.atguigu.gmall.manager.SkuEsService;
import com.atguigu.gmall.manager.SkuService;
import com.atguigu.gmall.manager.SpuInfoService;
import com.atguigu.gmall.manager.sku.SkuInfo;
import com.atguigu.gmall.manager.spu.SpuImage;
import com.atguigu.gmall.manager.spu.SpuSaleAttr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/sku")
@RestController
public class SkuController {

    @Reference
    SkuService skuService;
    @Reference
    SpuInfoService spuInfoService;
    @Reference
    SkuEsService skuEsService;

    @ResponseBody
    @RequestMapping("/onSale")
    public String onSale(@RequestParam("skuId") Integer skuId){
        skuEsService.onSale(skuId);
        return "ok";
    }

    @RequestMapping("/skuinfo")
    public List<SkuInfo> getSkuInfoBySpuId(@RequestParam("id") Integer spuId){
        return skuService.getSkuInfoBySpuId(spuId);
    }

    @RequestMapping("/bigsave")
    public String skuBigSave(@RequestBody SkuInfo skuInfo){
        log.debug("页面提交过来的大skuInfo信息：{}",skuInfo);
        skuService.saveBigSkuInfo(skuInfo);
        return "OK";
    }
    /**
     * 查询spu下的所有图片
     * @param spuId
     * @return
     */
    @RequestMapping("/spuImgaes")
    public List<SpuImage> getSpuImages(@RequestParam("id") Integer spuId){

        return  spuInfoService.getSpuImages(spuId);
    }

    /**
     * 查询spuId对应的所有可供选择的sku
     * @param spuId
     * @return
     */
    @RequestMapping("/spu_sale_attr.json")
    public List<SpuSaleAttr> getSpuSaleAttrBySpuId(@RequestParam("id") Integer spuId){
        return skuService.getSpuSaleAttrBySpuId(spuId);
    }

    @RequestMapping("/base_attr_info.json")
    public List<BaseAttrInfo> getBaseAttrInfoByCatalog3Id(@RequestParam("id") Integer catalog3Id){

        return skuService.getBaseAttrInfoByCatalog3Id(catalog3Id);
    }
}
