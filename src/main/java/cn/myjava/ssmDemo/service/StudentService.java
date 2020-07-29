package cn.myjava.ssmDemo.service;

import cn.myjava.ssmDemo.entity.Student;
import cn.myjava.ssmDemo.utils.PageInfo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/28  13:32
 */
public interface StudentService {
    /**
     * 获取学生列表
     *
     * @return
     */
    List<Student> getStudentList();

    /**
     * 添加一名学生
     *
     * @param student
     * @return int
     */
    int addStudent(Student student);

    /**
     * 模糊查询获得学生个数
     *
     * @param name
     * @return
     */
    int getStudentCountByName(String name);

    /**
     * 模糊查询获得学生列表
     *
     * @param name
     * @param pageNo
     * @return PageInfo<Student>
     */
    PageInfo<Student> getStudentPageByName(String name, Integer pageNo);

    /**
     * 通过学号查找学生
     *
     * @param sid
     * @return Student
     */
    Student getStudentBySid(String sid);

    /**
     * 修改一名学生
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
    Integer deleteStudent(String sid);

    /**
     * 登录验证
     *
     * @param sid
     * @param sPwd
     * @return Student
     */
    Student getStudentBySNameAndSPwd(String sid, String sPwd);

    /**
     * 导出到Excel表格
     *
     * @param name
     * @return XSSFWorkbook
     */
    XSSFWorkbook exportExcelInfo(String name) throws Exception;

    /**
     * 批量导入学生信息
     *
     * @param multipartFile
     * @return
     * @throws IOException
     * @throws Exception
     */
    int importStudents(MultipartFile multipartFile) throws IOException, Exception;
}