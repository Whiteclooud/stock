package com.lan.stock.service.Impl;


import com.lan.stock.mapper.StockMarketIndexInfoMapper;
import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.vo.StockInfoConfig;
import com.lan.stock.service.StockService;
import com.lan.stock.utils.DateTimeUtil;
import com.lan.stock.vo.resp.R;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description：股票服务实现
 * @author     ：lan
 * @date       ：2024/12/23 15:55
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    /**
     * @author: lan
     * @description: 获取国内大盘最新数据
     * @date: 2024/12/23 15:58
     * @return
     */
    @Override
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        //1、获取股票最新交易时间点（精确到分钟，秒和毫秒置为0）
//        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
//        Date curDate = curDateTime.toDate();
        //mock data，等后续完成job工程，再删除
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        curDate = DateTime.parse("2021-12-28 09:31:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2、获取大盘编码集合
        List<String> mcodes = stockInfoConfig.getInner();
        //3、调用mapper查询数据
        List<InnerMarketDomain> data = stockMarketIndexInfoMapper.getMarketInfo(curDate, mcodes);
        //4、封装并相应
        return R.ok(data);
    }
}
