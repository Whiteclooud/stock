package com.lan.stock.service;

import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.domain.StockBlockDomain;
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

    /**
     * @author: lan
     * @description: 获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据
     * @date: 2024/12/23 20:13
     */
    R<List<StockBlockDomain>> sectorAllLimit();
}
