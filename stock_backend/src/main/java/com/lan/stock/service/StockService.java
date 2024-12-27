package com.lan.stock.service;

import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.domain.StockBlockDomain;
import com.lan.stock.pojo.domain.StockUpdownDomain;
import com.lan.stock.vo.resp.PageResult;
import com.lan.stock.vo.resp.R;

import java.util.List;
import java.util.Map;

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

    /**
     * @author: lan
     * @description: 分页查询最新的股票交易数据
     * @date: 2024/12/24 16:47
     * @return 
     */
    R<PageResult<StockUpdownDomain>> getStockInfoByPage(Integer page, Integer pageSize);

    /**
     * @author: lan
     * @description: 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @date: 2024/12/24 19:01
     * @return
     */
    R<List<StockUpdownDomain>> getMostIncreseStock(Integer num);

    /**
     * @author: lan
     * @description: 统计最新交易日下股票每分钟涨跌停的数量
     * @date: 2024/12/24 20:57
     * @return
     */
    R<Map<String, List>> getStockUpDownCount();
}
