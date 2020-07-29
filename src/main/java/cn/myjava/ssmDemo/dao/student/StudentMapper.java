package cn.myjava.ssmDemo.dao.student;

import cn.myjava.ssmDemo.entity.Student;
import cn.myjava.ssmDemo.utils.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/22  21:50
 */
public interface StudentMapper {
    /**
     * 查询学生列表和所选的课程
     *
     * @return List<Student>
     */
    List<Student> selectStudentList();

    /**
     * 添加一个学生信息
     *
     * @param student
     * @return int
     */
    int insertStudent(Student student);

    /**
     * 查询指定的学生个数
     *
     * @param name
     * @return int
     */
    int selectStudentCountByName(@Param("name") String name);

    /**
     * 查询指定的学生列表
     *
     * @param name
     * @return int
     */
    List<Student> selectStudentAllListByName(@Param("name") String name);

    /**
     * 查询指定的学生列表
     *
     * @param name
     * @param pageInfo
     * @return
     */
    List<Student> selectStudentListByName(@Param("name") String name, @Param("pageInfo") PageInfo<Student> pageInfo);

    /**
     * 通过学号查找学生
     *
     * @param sid
     * @return Student
     */
    Student selectStudentBySid(@Param("sid") String sid);

    /**
     * 修改一个学生信息
     *
     * @param student
     * @return int
     */
    int updateStudent(Student student);

    /**
     * 删除一个学生信息
     *
     * @param sid
     * @return int
     */
    int deleteStudent(@Param("sid") String sid);

    /**
     * 通过学号，密码查找学生
     *
     * @param sid
     * @param sPwd
     * @return
     */
    Student selectStudentBySNameAndSPwd(@Param("sid") String sid, @Param("sPwd") String sPwd);
}
