package com.lan.stock.mapper;

import com.lan.stock.pojo.domain.StockBlockDomain;
import com.lan.stock.pojo.domain.StockUpdownDomain;
import com.lan.stock.pojo.entity.StockBlockRtInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author lan
* @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
* @createDate 2024-10-09 16:50:30
* @Entity com.lan.stock.pojo.entity.StockBlockRtInfo
*/
public interface StockBlockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBlockRtInfo record);

    int insertSelective(StockBlockRtInfo record);

    StockBlockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBlockRtInfo record);

    int updateByPrimaryKey(StockBlockRtInfo record);

    /**
     * @author: lan
     * @description: 沪深两市板块分时行情数据查询，以交易时间和交易总金额降序查询，取前10条数据
     * @date: 2024/12/23 20:30
     * @param timePoint 指定时间点
     * @return
     */
    List<StockBlockDomain> sectorAllLimit(@Param("timePoint") Date timePoint);

    /**
     * @author: lan
     * @description: 查询指定时间点的股票交易数据,并根据涨幅降序排序
     * @date: 2024/12/24 17:13
     * @param lastDate 日期时间
     * @return
     */
    List<StockUpdownDomain> getStockInfoByTime(@Param("lastDate") Date lastDate);

    /**
     * @author: lan
     * @description: 查询前n个指定时间点的股票交易数据,并根据涨幅降序排序
     * @date: 2024/12/24 19:09
     * @return
     */
    List<StockUpdownDomain> getFisrstNStockInfoByTime(@Param("lastDate") Date lastDate);
}
