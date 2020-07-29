package cn.myjava.ssmDemo.controller.login;

import cn.myjava.ssmDemo.entity.Student;
import cn.myjava.ssmDemo.service.StudentService;
import cn.myjava.ssmDemo.service.impl.StudentServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/24  20:28
 */
@Controller
public class LoginController {
    @Autowired
    private StudentService studentService;
    private Logger logger = Logger.getLogger(StudentServiceImpl.class);

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @GetMapping({"login", "/"})
    public String login() {
        return "login";
    }

    @PostMapping({"login", "logout", "/"})
    public String doLogin(@RequestParam String sid,
                          @RequestParam String sPwd,
                          HttpSession session, HttpServletRequest request) {
        Student student = studentService.getStudentBySNameAndSPwd(sid, sPwd);
        if (student != null) {
            session.setAttribute("stuSession", student);
            return "redirect:/student/getStuList";
        }
        request.setAttribute("msg", "学号密码错误，请重新登录！");
        return "login";
    }
}
