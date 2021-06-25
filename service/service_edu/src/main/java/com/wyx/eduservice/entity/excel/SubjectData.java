package com.wyx.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(index = 0)  //index表示列，0表示第一列
    private String oneSubjectName;

    @ExcelProperty(index = 1)  //index表示列，0表示第一列
    private String twoSubjectName;
}
