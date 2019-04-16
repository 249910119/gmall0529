package com.atguigu.gmall.manager;

import com.atguigu.gmall.manager.spu.BaseSaleAttr;
import com.atguigu.gmall.manager.spu.SpuImage;
import com.atguigu.gmall.manager.spu.SpuInfo;

import java.util.List;

public interface SpuInfoService {
    List<SpuInfo> getSpuInfoByC3Id(Integer catalog3Id);

    List<BaseSaleAttr> getBaseSaleAttr();

    void saveBigSpuInfo(SpuInfo spuInfo);

    List<SpuImage> getSpuImages(Integer spuId);
}
