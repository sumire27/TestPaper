package cn.itcast.controller;

import cn.itcast.domain.*;
import cn.itcast.service.GradeService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    UserService userService;

    @Autowired
    GradeService gradeService;

    //跳转到登录页面
    @RequestMapping("/login")
    public String toLogin(User user, Model model, HttpSession session){
        if(session.getAttribute("userName")!= null){
            return "index";
        }
        System.out.println(user.toString());
        model.addAttribute("user",user);
        List<Grade> gradeList=gradeService.find(new Grade());
        List<User> dataList = userService.findAllStu();
        return "tLogin";
    }


    @RequestMapping("/userLogin")
    public String checkUser(User user, Model model, HttpSession session){
        User loginUser = userService.login(user);
        model.addAttribute("user",loginUser);
        System.out.println(loginUser.toString());
        if(session.getAttribute("userName")!= null){
            return "index";
        }

        if(loginUser!=null && loginUser.getUserType() == 2){
            session.setAttribute("userName", loginUser.getUserName());
            return "index";
        }else{
            model.addAttribute("message", "用户名或密码输入错误！！！");
            return "tLogin";
        }

    }

    /**
     * ajax验证用户账号是否存在
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("/userRegist")
    @ResponseBody
    public MsgItem userRegist(String userId, Model model, HttpSession session){
        MsgItem msgItem = new MsgItem();
        User user = userService.findStuById(userId);
        if(user!=null){
            msgItem.setErrorNo("1");
            msgItem.setErrorInfo("账号已经存在");
        }else{
            msgItem.setErrorNo("0");
            msgItem.setErrorInfo("<font color='green'>验证通过</font>");
        }
        return msgItem;
    }

    /*
    查询所有用户
     */
    @RequestMapping("/allStu")
    public String findAllStu(Model model){
        List<User> stuList=userService.findAllStu();
        List<Grade> gradeList=gradeService.find(new Grade());
        for(User user:stuList){
            String gradeName=gradeService.get(Integer.parseInt(user.getGrade())).getGradeName();
            user.setGrade(gradeName);

        }
        model.addAttribute("stuList",stuList);
        model.addAttribute("gradeList",gradeList);
        return "stuMa";

    }

    @RequestMapping("/deleteStu")
    public String deleteStu(Model model, @RequestParam("userId") String userId){
        userService.deleteUser(userId);
        List<User> stuList=userService.findAllStu();
        model.addAttribute("stuList",stuList);
        return "redirect:/user/allStu";

    }


    @RequestMapping("/toUpdateStu")
    public String toUpdateUser(@RequestParam("userId") String userId,Model model){
       // String userid = userid.trim();
        User userInfo = userService.findStuById(userId);
        List<Grade> gradeList=gradeService.find(new Grade());
        model.addAttribute("user", userInfo);
        System.out.println("更新用户转而更新"+userInfo);
        model.addAttribute("gradeList",gradeList);

        return "info-upd";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user,Model model){
        System.out.println("修改用户。。。查看"+user);
        userService.updateUser(user);
        return "redirect:/user/allStu";
    }

    @RequestMapping("/toDetail")
    public String toDetail(User user,Model model){
        System.out.println(user.toString());
        String userId = user.getUserId().trim();
        User userInfo = userService.findStuById(userId);
        String grade=gradeService.get(Integer.parseInt(userInfo.getGrade())).getGradeName();
        model.addAttribute("gradeName",grade);
        model.addAttribute("user", userInfo);
        return "info-qry";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser(Model model){
        List<Grade> gradeList =gradeService.find(new Grade());
        model.addAttribute("gradeList",gradeList);
        return "info-reg";
    }
    /**
     * 添加用户信息
     * @param user
     * @param model
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(User user, Model model){
        userService.addUser(user);
        return "redirect:/user/allStu";
    }


    @RequestMapping("/searchInfo")
    public String searchInfo(@RequestParam("searchInfo") String searchInfo, Model model){
        List<User> userList=userService.findByInfo(searchInfo);
        for(User user:userList){
            Grade grade;
            String gradeName= "";
            grade = gradeService.get(Integer.parseInt(user.getGrade()));
            gradeName=grade.getGradeName();
            user.setGrade(gradeName);
        }
        model.addAttribute("searchInfo",searchInfo);
        model.addAttribute("userList",userList);
        return "user-multi";
    }

}
