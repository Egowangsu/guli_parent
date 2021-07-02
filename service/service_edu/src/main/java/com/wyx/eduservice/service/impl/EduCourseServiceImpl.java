package com.wyx.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyx.eduservice.entity.EduCourse;
import com.wyx.eduservice.entity.EduCourseDescription;
import com.wyx.eduservice.entity.vo.CourseEntityVo;
import com.wyx.eduservice.mapper.EduCourseMapper;
import com.wyx.eduservice.service.EduCourseDescriptionService;
import com.wyx.eduservice.service.EduCourseService;
import com.wyx.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Override
    public String addCourseInfo(CourseEntityVo courseEntityVo) {
        EduCourse eduCourse = new EduCourse();
        //把相同属性赋值到另一个对象中
        BeanUtils.copyProperties(courseEntityVo, eduCourse);
        int count = baseMapper.insert(eduCourse);   //将数据存到course表中
        if(count == 0){
            throw new GuliException("20001","添加课程信息失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseEntityVo, eduCourseDescription);
        //因为course和description是一对一的关系，所以id值一样
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }
}
