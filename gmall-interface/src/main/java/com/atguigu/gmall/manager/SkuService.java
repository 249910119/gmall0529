package com.atguigu.gmall.manager;

import com.atguigu.gmall.manager.sku.SkuAttrValueMappingTo;
import com.atguigu.gmall.manager.sku.SkuInfo;
import com.atguigu.gmall.manager.spu.SpuSaleAttr;

import java.util.List;

public interface SkuService {
    List<BaseAttrInfo> getBaseAttrInfoByCatalog3Id(Integer catalog3Id);

    List<SpuSaleAttr> getSpuSaleAttrBySpuId(Integer spuId);

    void saveBigSkuInfo(SkuInfo skuInfo);

    List<SkuInfo> getSkuInfoBySpuId(Integer spuId);

    SkuInfo getSkuInfoBySkuId(Integer skuId) throws InterruptedException;

    List<SkuAttrValueMappingTo> getSkuAttrValueMapping(Integer spuId);
}
