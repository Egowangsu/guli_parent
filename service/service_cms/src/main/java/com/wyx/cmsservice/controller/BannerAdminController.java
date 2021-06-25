package com.wyx.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyx.Response;
import com.wyx.cmsservice.entity.CrmBanner;
import com.wyx.cmsservice.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@RestController  //后台管理员对轮播图增删改查
@RequestMapping("/cmsservice/banneradmin")
@CrossOrigin
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    //分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public Response pageBanner(@PathVariable long page,@PathVariable long limit){
    //获取分页对象,将当前页和每页数量传入
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        //获取banner数据
        bannerService.page(pageBanner, null);

        return Response.success().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Response get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Response.success().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("addBanner")
    public Response save(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return Response.success();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Response updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Response.success();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Response remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Response.success();
    }
}

