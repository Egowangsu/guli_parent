package com.wyx.cmsservice.controller;


import com.wyx.Response;
import com.wyx.cmsservice.entity.CrmBanner;
import com.wyx.cmsservice.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@RestController   //前台客户对轮播图查询
@RequestMapping("/cmsservice/bannerfront")
@CrossOrigin
public class BannerFrontController {


    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    @Cacheable(value = "banner",key = "'selectIndexList'")  //先查询是否存在缓存，若存在则返回缓存，不存在就将查询结果放入缓存，然后返回
    public Response index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return Response.success().data("bannerList", list);
    }
}

