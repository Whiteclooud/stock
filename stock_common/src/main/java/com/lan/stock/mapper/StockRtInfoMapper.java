package com.lan.stock.mapper;

import com.lan.stock.pojo.domain.StockUpdownDomain;
import com.lan.stock.pojo.entity.StockRtInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author lan
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-10-09 16:50:30
* @Entity com.lan.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

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

    /**
     * 查询指定时间范围内每分钟涨停或者跌停的数量
     * @param startDate 开始时间
     * @param closeDate 结束时间 一般开始时间和结束时间在同一天
     * @param flag 约定:1->涨停 0:->跌停
     * @return
     */
    @MapKey("time")
    List<Map> getStockUpdownCount(@Param("startDate") Date startDate, @Param("closeDate") Date closeDate, @Param("flag") int flag);
}
