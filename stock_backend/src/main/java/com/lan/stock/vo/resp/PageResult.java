package com.lan.stock.vo.resp;


/**
 * @author ：lan
 * @description：TODO
 * @date ：2024/12/24 15:36
 */

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 */
@ApiModel(description = "分页实体类")
@Data
public class PageResult<T> implements Serializable {
    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private Long totalRows;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private Integer totalPages;

    /**
     * 当前第几页
     */
    @ApiModelProperty("当前第几页")
    private Integer pageNum;
    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    @ApiModelProperty("当前页记录数")
    private Integer size;
    /**
     * 结果集
     */
    @ApiModelProperty("结果集")
    private List<T> rows;

    /**
     * 分页数据组装
     * @param pageInfo 数据来自PageHelper对象
     * @return
     */
    public PageResult(PageInfo<T> pageInfo) {
        totalRows = pageInfo.getTotal();
        totalPages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        size = pageInfo.getSize();
        rows = pageInfo.getList();
    }
}
