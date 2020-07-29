package cn.myjava.ssmDemo.controller.student;

import cn.hutool.core.util.PageUtil;
import cn.myjava.ssmDemo.entity.Student;
import cn.myjava.ssmDemo.service.StudentService;
import cn.myjava.ssmDemo.utils.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;


/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/22  23:36
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    private Logger logger = Logger.getLogger(StudentController.class);

    @GetMapping("getStuList")
    public ModelAndView getStuList(@RequestParam(required = false) String name,
                                   @RequestParam(defaultValue = "1") Integer pageNo) {
        ModelAndView view = new ModelAndView("stulist");
        PageInfo<Student> page = studentService.getStudentPageByName(name, pageNo);
        Integer totalPages = page.getTotalPages();
        Integer pageNum = page.getPageNo();
        logger.info("TotalPages：" + totalPages);
        //彩虹分页
        int[] rainbow = PageUtil.rainbow(pageNum, totalPages, 3);
        view.addObject("page", page);
        view.addObject("name", name);
        view.addObject("rainbow", rainbow);
        return view;
    }

    @GetMapping("add.html")
    public String addPage() {
        return "add";
    }

    @PostMapping("add.html")
    @ResponseBody
    public String addStudent(Student student, HttpServletRequest request,
                             @RequestParam(value = "sHeadPic", required = false) MultipartFile attach) {
        String sHead = null;
        String path = null;
        //判断文件是否为空
        if (!attach.isEmpty()) {
            System.out.println("进入文件上传方法，文件不为空");
            //定义上传目标的路径
            path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadImages");
            System.out.println("路径：" + path);
            String filename = attach.getOriginalFilename();
            //获取文件后缀名
            String suffix = FilenameUtils.getExtension(filename);
            //限制上传图片大小为2M
            int filesize = 2097152;
            if (attach.getSize() > filesize) {
                System.out.println("进入文件上传方法，文件超过2M");
                //图片大小超过2M
//                request.setAttribute("errorInfo", "*图片大小不能超过2M");
                return "*图片大小不能超过2M";
            } else if (suffix.equalsIgnoreCase("jpg") ||
                    suffix.equalsIgnoreCase("jpeg") ||
                    suffix.equalsIgnoreCase("png") ||
                    suffix.equalsIgnoreCase("pneg")) {
                //重命名当前文件名，避免服务器端发生文件覆盖情况
                String fileName = System.currentTimeMillis() + RandomUtils.nextInt() + "_lxy." + suffix;
                File targetFile = new File(path, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    //文件上传失败
//                    request.setAttribute("errorInfo", "*图片上传失败，请稍后再试！");
                    return "*图片上传失败，请稍后再试！";
                }
                sHead = path + File.separator + fileName;
            } else {
                //图片格式不正确
                System.out.println("图片格式不正确");
//                request.setAttribute("errorInfo", "*图片格式不正确");
                return "*图片格式不正确";
            }

        }
        student.setSHead(sHead);
        int count = studentService.addStudent(student);
        if (count > 0) {
            return "上传成功";
        } else {
            return "上传失败！";
        }
    }

    @GetMapping("/getStuBySid/{sid}")
    public String getStuBySid(@PathVariable String sid, Model model) {
        Student student = studentService.getStudentBySid(sid);
        model.addAttribute("stu", student);
        return "update";
    }

    @PostMapping("/update")
    @ResponseBody
    public int updateStuBySid(Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/deleteStuBySid/{sid}")
    @ResponseBody
    public Integer updateStuBySid(@PathVariable String sid) {
        return studentService.deleteStudent(sid);
    }

    @GetMapping({"/export", "/export/{name}"})
    public void exportToExcel(@PathVariable(required = false) String name, HttpServletResponse response) {
        OutputStream output = null;
        BufferedOutputStream buffer = null;
        try {
            //工作簿的名字
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(("学生名单.xlsx").getBytes("GBK"), "ISO-8859-1"));
            response.setContentType("application/vnd.ms-excel--MSEXCEL;charset=UTF-8");
            output = response.getOutputStream();
            XSSFWorkbook workbook = studentService.exportExcelInfo(name);
            buffer = new BufferedOutputStream(output);
            workbook.write(buffer);
            buffer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null) {
                    buffer.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

//    @RequestMapping(value = "/import", method = RequestMethod.POST)
//    @ResponseBody
//    public String importFromExcel(@RequestParam MultipartFile multipartFile) {
//        try {
//            int count = studentService.importStudents(multipartFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "导入失败";
//        }
//        return "导入成功";
//    }

    @PostMapping("/importFile")
    @ResponseBody
    public String importUser(@RequestParam("importFile") MultipartFile importFile) {
        try {
            studentService.importStudents(importFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "ok";
    }
}
