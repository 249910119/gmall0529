package com.atguigu.gmall.manager;

import com.atguigu.gmall.manager.es.SkuSearchParamEsVo;
import com.atguigu.gmall.manager.es.SkuSearchResultEsVo;

public interface SkuEsService  {
    void onSale(Integer skuId);

    SkuSearchResultEsVo searchSkuFromES(SkuSearchParamEsVo paramEsVo);

    void updateHotScore(Integer skuId, Long hincrBy);
}
