package com.lan.stock.controller;


import com.lan.stock.pojo.domain.InnerMarketDomain;
import com.lan.stock.service.StockService;
import com.lan.stock.service.UserService;
import com.lan.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：lan
 * @description：定义股票相关接口控制器
 * @date ：2024/12/23 15:42
 */
@Api(value = "api/quot", tags = {"股票相关接口控制器"})
@RestController
@RequestMapping("api/quot")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * @author: lan
     * @description: 获取国内大盘最新数据
     * @date: 2024/12/23 15:49
     * @return
     */
    @ApiOperation(value = "获取国内大盘最新数据", notes = "获取国内大盘最新数据", httpMethod = "GET")
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarketInfo() {
        return stockService.getInnerMarketInfo();
    }
}
