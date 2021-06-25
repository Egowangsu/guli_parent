package com.wyx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyx.eduservice.Listener.SubjectExcelListener;
import com.wyx.eduservice.entity.EduSubject;
import com.wyx.eduservice.entity.excel.SubjectData;
import com.wyx.eduservice.entity.subject.OneSubject;
import com.wyx.eduservice.entity.subject.TwoSubject;
import com.wyx.eduservice.mapper.EduSubjectMapper;
import com.wyx.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-01
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取Excel内容
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch(Exception e){
                e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getOneTwoSubject() {
        //1.获取全部的一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper();
        wrapper.eq("parent_id", "0");
        //有两种调用的方法，1是使用baseMapper调用，已经帮我们注入，可以直接使用
        //baseMapper.selectList(wrapper);
        //第二种方法是直接使用eduSubjectServiceImpl来调用，因为他的list方法调用的就是baseMapper的selectList方法
        List<EduSubject> oneSubjectList = this.list(wrapper);  //this就代表当前对象

        //2获取所有二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper();
        wrapper2.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);

        //将所有的一级分类和二级分类放入到规范格式集合对象中(OneSubject)
        List<OneSubject>  findSubject = new ArrayList<>();


        //遍历将所有一级分类装入到findSubject中
        for(EduSubject eduSubject:oneSubjectList){
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());
            findSubject.add(oneSubject);
            List<TwoSubject>  findSubject2 = new ArrayList<>();
            for(EduSubject eduSubject2:twoSubjectList){   //二级分类
                if(eduSubject2.getParentId().equals(oneSubject.getId())){  //判断是parent_id是否和当前一级分类id相同，若相同则加入到children
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(eduSubject2.getId());
                    twoSubject.setTitle(eduSubject2.getTitle());
                    findSubject2.add(twoSubject);
                }
            }
            oneSubject.setChildren(findSubject2);
        }

        return findSubject ;
    }
}
