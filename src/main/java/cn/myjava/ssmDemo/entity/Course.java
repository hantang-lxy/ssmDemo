package cn.myjava.ssmDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/22  22:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Course implements Serializable {

    private static final long serialVersionUID = 8072491369315671368L;
    /***
     * 课程编号
     */
    private String cid;
    /***
     * 课程名称
     */
    private String cName;
    /***
     * 教师编号
     */
    private Integer tid;


}
