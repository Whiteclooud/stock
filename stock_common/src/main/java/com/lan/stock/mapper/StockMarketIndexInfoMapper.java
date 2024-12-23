package com.lan.stock.mapper;

import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.domain.StockBlockDomain;
import com.lan.stock.pojo.entity.StockMarketIndexInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author lan
* @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-10-09 16:50:30
* @Entity com.lan.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    /**
     * @author: lan
     * @description: 根据指定时间点查询指定大盘编码对应的大盘数据
     * @date: 2024/12/23 16:21
     * @param curDate 指定时间点
     * @param marketCodes 大盘代码集合
     * @return
     */
    List<InnerMarketDomain> getMarketInfo(@Param("curDate") Date curDate, @Param("marketCodes") List<String> marketCodes);

    /**
     * @author: lan
     * @description: 沪深两市板块分时行情数据查询，以交易时间和交易总金额降序查询，取前10条数据
     * @date: 2024/12/23 20:30
     * @param timePoint 指定时间点
     * @return
     */
    List<StockBlockDomain> sectorAllLimit(@Param("timePoint") Date timePoint);
}
