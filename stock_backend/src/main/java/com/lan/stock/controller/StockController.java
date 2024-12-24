package com.lan.stock.controller;


import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.pojo.domain.StockBlockDomain;
import com.lan.stock.pojo.domain.StockUpdownDomain;
import com.lan.stock.service.StockService;
import com.lan.stock.service.UserService;
import com.lan.stock.vo.resp.PageResult;
import com.lan.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ：lan
 * @description：定义股票相关接口控制器
 * @date ：2024/12/23 15:42
 */
@Api(value = "api/quot", tags = {"：定义股票相关接口控制器"})
@RestController
@RequestMapping("api/quot")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * @author: lan
     * @description: 获取国内大盘最新数据
     * @date: 2024/12/23 15:49
     */
    @ApiOperation(value = "获取国内大盘最新数据", notes = "获取国内大盘最新数据", httpMethod = "GET")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        return stockService.getInnerMarketInfo();
    }

    /**
     * @author: lan
     * @description: 获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据
     * @date: 2024/12/23 20:12
     */
    @ApiOperation(value = "获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据", notes = "获取沪深两市板块最新数据，以交易总金额降序查询，取前10条数据", httpMethod = "GET")
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll(){
        return stockService.sectorAllLimit();
    }

    /**
     * @author: lan
     * @description: 分页查询最新的股票交易数据
     * @date: 2024/12/24 16:44
     * @param page 当前页
     * @param pageSize 每页大小
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "page", value = "当前页"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "pageSize", value = "每页大小")
    })
    @ApiOperation(value = "分页查询最新的股票交易数据", notes = "分页查询最新的股票交易数据", httpMethod = "GET")
    @GetMapping ("/stock/all")
    public R<PageResult<StockUpdownDomain>> getStockInfoByPage(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize){
        return stockService.getStockInfoByPage(page, pageSize);
    }
    
    /**
     * @author: lan
     * @description: 统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据
     * @date: 2024/12/24 19:01
     * @return
     */
    @ApiOperation(value = "统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据", notes = "统计沪深两市个股最新交易数据，并按涨幅降序排序查询前4条数据", httpMethod = "GET")
    @GetMapping("/stock/increase")
    public R<List<StockUpdownDomain>> getMostIncreseStock(@RequestParam(value = "num", required = false, defaultValue = "4") Integer num){
        return stockService.getMostIncreseStock(num);
    }

    /**
     * @author: lan
     * @description: 统计最新交易日下股票每分钟涨跌停的数量
     * @date: 2024/12/24 20:57
     * @return
     */
    @GetMapping("/stock/updown/count")
    public R<Map<String, List>> getStockUpdownCount(){
        return stockService.getStockUpdownCount();
    }
}
