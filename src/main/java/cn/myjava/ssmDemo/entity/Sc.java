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
public class Sc implements Serializable {

    private static final long serialVersionUID = 7219645347098932714L;
    /***
     * 学号
     */
    private String sid;
    /***
     * 课程编号
     */
    private String cid;
    /***
     * 成绩
     */
    private Integer score;


}
