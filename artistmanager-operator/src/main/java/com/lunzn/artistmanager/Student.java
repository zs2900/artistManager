/*
 * 文 件 名:  Student.java
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
public class Student
{
    public String name;
    
    public int age;
    
    /** 
     * <默认构造函数>
     */
    public Student(String name, int age)
    {
        super();
        this.name = name;
        this.age = age;
    }
    
    /** 
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String toString()
    {
        return "Student [name=" + name + ", age=" + age + "]";
    }
    
}
