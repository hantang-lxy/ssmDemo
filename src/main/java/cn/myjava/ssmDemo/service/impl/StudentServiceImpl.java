package cn.myjava.ssmDemo.service.impl;

import cn.myjava.ssmDemo.dao.student.StudentMapper;
import cn.myjava.ssmDemo.entity.Course;
import cn.myjava.ssmDemo.entity.ExcelBean;
import cn.myjava.ssmDemo.entity.Student;
import cn.myjava.ssmDemo.service.StudentService;
import cn.myjava.ssmDemo.utils.ExcelUtil;
import cn.myjava.ssmDemo.utils.PageInfo;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/22  23:25
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    private Logger logger = Logger.getLogger(StudentServiceImpl.class);

    /**
     * 获取学生列表
     *
     * @return
     */
    @Override
    public List<Student> getStudentList() {
        List<Student> students = studentMapper.selectStudentList();
        for (Student stu : students) {
            logger.info("学生姓名：" + stu.getSName() + "课程名称：");
            List<Course> courses = stu.getCourses();
            for (Course course : courses) {
                logger.info("\n" + course.getCName());
            }
        }

        return students;
    }

    /**
     * 添加一名学生
     *
     * @return int
     */
    @Override
    public int addStudent(Student student) {
        int count = studentMapper.insertStudent(student);
        logger.info("添加学生结果：" + count);
        return count;
    }

    /**
     * 模糊查询获得学生个数
     *
     * @param name
     * @return int
     */
    @Override
    public int getStudentCountByName(String name) {
        return studentMapper.selectStudentCountByName(name);
    }

    /**
     * 模糊查询获得学生列表
     *
     * @param name
     * @param pageNo
     * @return PageInfo<Student>
     */
    @Override
    public PageInfo<Student> getStudentPageByName(String name, Integer pageNo) {
        PageInfo<Student> pageInfo = new PageInfo<>();
        pageInfo.setPageNo(pageNo);
        List<Student> list = studentMapper.selectStudentListByName(name, pageInfo);
        logger.info("list>>>" + JSON.toJSONString(list));
        pageInfo.setDatas(list);
        int count = studentMapper.selectStudentCountByName(name);
        if (count == 0) {
            pageInfo.setPageNo(0);
        }
        logger.info(JSON.toJSONString("方法getStudentPageByName，查询出来记录count>>>" + count));
        pageInfo.setTotalRows(count);
        return pageInfo;
    }

    /**
     * 通过学号查找学生
     *
     * @param sid
     * @return Student
     */
    @Override
    public Student getStudentBySid(String sid) {
        Student student = studentMapper.selectStudentBySid(sid);
        logger.info("student>>>" + student);
        return student;
    }

    /**
     * 修改一名学生
     *
     * @param student
     * @return int
     */
    @Override
    public int updateStudent(Student student) {
        int count = studentMapper.updateStudent(student);
        logger.info("更新结果>>>" + count);
        if (count > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 删除一个学生信息
     *
     * @param sid
     * @return int
     */
    @Override
    public Integer deleteStudent(String sid) {
        int count = studentMapper.deleteStudent(sid);
        logger.info("删除结果>>>" + count);
        if (count > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 登录验证
     *
     * @param sid
     * @param sPwd
     * @return Student
     */
    @Override
    public Student getStudentBySNameAndSPwd(String sid, String sPwd) {
        Student student = studentMapper.selectStudentBySNameAndSPwd(sid, sPwd);
        logger.info("登录验证结果student：" + student);
        return student;
    }

    /**
     * 导出到Excel表格
     *
     * @return XSSFWorkbook
     */
    @Override
    public XSSFWorkbook exportExcelInfo(String name) throws Exception {
        //查询需要导入的数据
        List<Student> studentList = studentMapper.selectStudentAllListByName(name);
        Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
        List<ExcelBean> excelBeanList = new ArrayList<>();
        //设置excel表头和属性字段对应，设计表格的头
        excelBeanList.add(new ExcelBean("学号", "sid", 0));
        excelBeanList.add(new ExcelBean("姓名", "sName", 0));
        excelBeanList.add(new ExcelBean("生日", "sAge", 0));
        excelBeanList.add(new ExcelBean("性别", "sSex", 0));
        excelBeanList.add(new ExcelBean("密码", "sPwd", 0));
        excelBeanList.add(new ExcelBean("头像地址", "sHead", 0));
        map.put(0, excelBeanList);
        //表格里面sheet的名字
        String sheetName = null;
        if (name == null) {
            sheetName = "所有学生名单";
        } else {
            sheetName = "包含<" + name + ">的学生名单";
        }
        //调用工具类，创建工作簿
        XSSFWorkbook workbook = ExcelUtil.createExcelFile(Student.class, studentList, map, sheetName);
        return workbook;
    }

    /**
     * 批量导入学生信息
     *
     * @param multipartFile
     * @return int
     */
    @Override
    public int importStudents(MultipartFile multipartFile) throws IOException, Exception {
        //从上传文件中获取数据
        InputStream inputStream = multipartFile.getInputStream();
        List<List<Object>> list = ExcelUtil.getListByExcel(inputStream, multipartFile.getOriginalFilename());
        logger.info("import Students count:" + list.size());
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                List<Object> objs = list.get(i);
                Student student = new Student();
                student.setSid(String.valueOf(objs.get(0)));
                student.setSName(String.valueOf(objs.get(1)));
                String value = String.valueOf(objs.get(2));
                Date date = DateUtil.parseYYYYMMDDDate(value);
                student.setSAge(date);
                student.setSSex(String.valueOf(objs.get(3)));
                student.setSPwd(String.valueOf(objs.get(4)));
                student.setSHead(String.valueOf(objs.get(5)));
                int count = studentMapper.insertStudent(student);
            }

        }
        return 0;
    }
}
