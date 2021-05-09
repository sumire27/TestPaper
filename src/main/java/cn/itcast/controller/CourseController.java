package cn.itcast.controller;

import cn.itcast.domain.Course;
import cn.itcast.domain.Question;
import cn.itcast.domain.Type;
import cn.itcast.service.CourseService;
import cn.itcast.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CourseController implements Serializable {
    @Autowired
    CourseService courseService;
    @RequestMapping("/allCourse")
    public String allType(Model model){
        List<Course> courseList=courseService.find(new Course());
        model.addAttribute("courseList",courseList);
        return "courseMa";

    }


    /**
     * 跳转到添加课程信息页面

     */
    @RequestMapping("/toAddCourse")
    public String toAddCourse(Course course,Model model, HttpSession session){
        List<Course> dataList = courseService.find(course);
        model.addAttribute("dataList", dataList);

        return "course-reg";
    }
    /**
     * 添加课程信息

     * @return
     */
    @RequestMapping("/addCourse")
    public String addQues(Course course, Model model){
        course.setCourseState("0");
        courseService.insert(course);
        return "redirect:/admin/allCourse";
    }

    @RequestMapping("/deleteCourse")
    public String deleteQues(Model model, @RequestParam("courseId") String courseid){
        courseService.delete(Integer.parseInt(courseid));
        List<Course> courseList=courseService.find(new Course());
        model.addAttribute("course",courseList);
        return "redirect:/admin/allCourse";

    }
    @RequestMapping("/toUpdateCourse")
    public String toUpdateQues(Model model,Course course){
        Course courseInfo=courseService.get(course.getCourseId());
        model.addAttribute("course",courseInfo);
        return "course-upd";

    }


    @RequestMapping("/updCourse")
    public String updCourse(Course course,Model model){
        //   System.out.println("修改用户。。。查看"+user);
        course.setCourseState("0");
        courseService.update(course);
        //model.addAttribute("course",)
        return "redirect:/admin/allCourse";
    }
}
