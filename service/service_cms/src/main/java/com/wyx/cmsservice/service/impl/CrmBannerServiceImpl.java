package com.wyx.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyx.cmsservice.entity.CrmBanner;
import com.wyx.cmsservice.mapper.CrmBannerMapper;
import com.wyx.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectIndexList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        //根据id降序
        wrapper.orderByDesc("id");
        //取前两条结果，last可以在语句最后进行sql拼接
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
