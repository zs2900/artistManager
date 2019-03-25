/*
 * 文 件 名:  Test.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  ZhongYi
 * 修改时间:  2018年12月28日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.lunzn.artistmanager;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  ZhongYi
 * @version  [版本号, 2018年12月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Test
{
    public static void main(String[] args)
    {
        Student student = new Student("zhangs", 22);
        System.out.println(student.toString());
        changeStudent(student);
        System.out.println(student.toString());
    }
    
    public static void changeStudent(Student student)
    {
        student.name = "lishi";
    }
}
