package com.lan.stock.service;

import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.vo.resp.R;

import java.util.List;

/**
 * @author: lan
 * @description: 股票服务接口
 * @date: 2024/12/23 15:53
 */
public interface StockService {
    /**
     * @author: lan
     * @description: 获取国内大盘最新数据
     * @date: 2024/12/23 15:54
     * @return
     */
    R<List<InnerMarketDomain>> getInnerMarketInfo();
}
