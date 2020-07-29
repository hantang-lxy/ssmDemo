package cn.myjava.ssmDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/22  21:52
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student implements Serializable {
    private static final long serialVersionUID = 6621265085772434434L;
    /**
     * 学号
     */
    private String sid;
    /**
     * 学生姓名
     */
    private String sName;
    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sAge;
    /**
     * 性别
     */
    private String sSex;
    /**
     * 头像
     */
    private String sHead;
    /**
     * 密码
     */
    private String sPwd;
    /**
     * 所选课程列表
     */
    private List<Course> courses;


}
