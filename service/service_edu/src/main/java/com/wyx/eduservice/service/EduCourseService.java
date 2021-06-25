package com.wyx.eduservice.service;

import com.wyx.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyx.eduservice.entity.vo.CourseEntityVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-05
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseEntityVo courseEntityVo);
}
