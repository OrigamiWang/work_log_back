package com.wyx.testspringboot.common.api;

/**
 * @BelongsProject: testSpringBoot
 * @BelongsPackage: com.wyx.testspringboot.common.api
 * @Author: Origami
 * @Date: 2022/9/3 11:05
 */

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页数据封装类
 * @param <T>
 */
import com.github.pagehelper.PageInfo;

@Getter
@Setter
public class CommonPage<T> {

    private Integer pageNum;

    private Integer pageSize;

    private Integer totalPage;

    private Long total;

    private List<T> list;


    /**
     * 将pageHelper分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> res = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        res.setPageSize(pageInfo.getPageSize());
        res.setPageNum(pageInfo.getPageNum());
        res.setTotalPage(pageInfo.getPages());
        res.setTotal(pageInfo.getTotal());
        res.setList(pageInfo.getList());
        return res;
    }

}
