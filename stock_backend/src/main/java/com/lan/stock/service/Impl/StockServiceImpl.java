package com.lan.stock.service.Impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lan.stock.mapper.StockBlockRtInfoMapper;
import com.lan.stock.mapper.StockMarketIndexInfoMapper;
import com.lan.stock.mapper.StockRtInfoMapper;
import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.domain.StockBlockDomain;
import com.lan.stock.pojo.domain.StockUpdownDomain;
import com.lan.stock.pojo.vo.StockInfoConfig;
import com.lan.stock.service.StockService;
import com.lan.stock.utils.DateTimeUtil;
import com.lan.stock.vo.resp.PageResult;
import com.lan.stock.vo.resp.R;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;




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

    /**
     * @author: lan
     * @description: 沪深两市板块分时行情数据查询，以交易时间和交易总金额降序查询，取前10条数据
     * @date: 2024/12/23 20:30
     * @return
     */
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //1、获取股票交易最新时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //mock数据，后续删除
        lastDate=DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2、调用mapper接口获取数据
        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(lastDate);
        //3、组装数据
        return R.ok(infos);
    }

    /**
     * @author: lan
     * @description: 分页查询最新的股票交易数据
     * @date: 2024/12/24 16:48
     * @return
     */
    @Override
    public R<PageResult<StockUpdownDomain>> getStockInfoByPage(Integer page, Integer pageSize) {
        //1、获取最新的股票交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //mock数据，后续删除
        lastDate=DateTime.parse("2021-12-30 09:42:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2、设置分页参数
        PageHelper.startPage(page, pageSize);
        //3、调用mapper接口获取数据
        List<StockUpdownDomain> pageData = stockRtInfoMapper.getStockInfoByTime(lastDate);
        //4、组装PaegResult数据
        PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(pageData);
        PageResult<StockUpdownDomain> pageResult = new PageResult<StockUpdownDomain>(pageInfo);
        //5、响应数据
        return R.ok(pageResult);
    }

    /**
     * @return
     * @author: lan
     * @description: 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @date: 2024/12/24 19:01
     */
    @Override
    public R<List<StockUpdownDomain>> getMostIncreseStock(Integer num) {
        //1、获取当前时间
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //mock数据，后续删除
        lastDate=DateTime.parse("2021-12-30 09:42:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2、调用mapper接口，获取数据
        List<StockUpdownDomain> topStockInfo = stockRtInfoMapper.getFisrstNStockInfoByTime(lastDate);
        //3、相应数据
        return R.ok(topStockInfo);
    }

    /**
     * @author: lan
     * @description: 统计最新交易日下股票每分钟涨跌停的数量
     * @date: 2024/12/24 20:57
     * @return
     */
    @Override
    public R<Map<String, List>> getStockUpDownCount() {
        //1、获取最新股票的交易时间点(截止时间)
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        //mock数据，后续删除
        curDateTime = DateTime.parse("2022-01-06 14:25:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        //mock/
        Date closeDate = curDateTime.toDate();
        //获取最新股票的交易时间点(开盘时间)
        Date startDate = DateTimeUtil.getOpenDate(curDateTime).toDate();
        //3、统计涨停数据
        List upList = stockRtInfoMapper.getStockUpDownCount(startDate, closeDate,1);
        //4、统计跌停
        List downList = stockRtInfoMapper.getStockUpDownCount(startDate, closeDate,0);
        //5、组装数据
        HashMap<String, List> info = new HashMap<>();
        info.put("upList", upList);
        info.put("downList", downList);
        return R.ok(info);
    }
}
