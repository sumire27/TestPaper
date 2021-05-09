package cn.itcast.controller;

import cn.itcast.domain.Course;
import cn.itcast.domain.Grade;
import cn.itcast.domain.Type;
import cn.itcast.service.CourseService;
import cn.itcast.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class GradeController implements Serializable {
    @Autowired
    GradeService gradeService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/allGrade")
    public String allType(Model model){
        List<Grade> gradeList=gradeService.find(new Grade());

        for(Grade grade:gradeList){
            String id=grade.getCourseId();
            String courseName="";
            if (id!=null){
                String ids[]=id.split(",");
                for (int i=0;i<ids.length;i++){
                    courseName+=courseService.get(Integer.parseInt(ids[i])).getCourseName()+",";

                }
            }
            String str=courseName.substring(courseName.length()-1,courseName.length());
            if(",".equals(str)){
                str=courseName.substring(0,courseName.length()-1);
            }else {
                str=courseName;
            }

            grade.setCourseId(str);
        }

        model.addAttribute("gradeList",gradeList);
        return "gradeMa";

    }


    @RequestMapping("/toGradeDetail")
    public String toQryGrade(int gradeId, Model model, HttpSession session){
        Grade gradeInfo = gradeService.get(gradeId);

        String courseName= "";
        Course course=null;
        List<Course> dataList = courseService.find(course);
        model.addAttribute("dataList", dataList);
        String id = gradeInfo.getCourseId();
        String ids[]=null;

        if(id != null){
            ids = id.split(",");
            for(int i=0;i<ids.length;i++){
                course = courseService.get(Integer.parseInt(ids[i]));
                courseName+=course.getCourseName()+",";
            }
        }
        //判断最后一个字符是否为逗号，若是截取
        String str = courseName.substring(courseName.length() -1, courseName.length());
        if(",".equals(str)){
            str = courseName.substring(0, courseName.length()-1);
        }else{
            str = courseName;
        }
        gradeInfo.setCourseId(str);
        model.addAttribute("grade", gradeInfo);

        return "grade-qry";
    }
    /**
     * 跳转到添加年级信息页面

     */
    @RequestMapping("/toAddGrade")
    public String toAddGrade(Grade grade,Model model){
        List<Course> courseList=courseService.find(new Course());
        model.addAttribute("courseList",courseList);

        return "grade-reg";
    }
    /**
     * 添加课程信息

     * @return
     */
    @RequestMapping("/addGrade")
    public String addGrade(Grade grade, Model model){
        System.out.println(grade.getCourseId());
        gradeService.insert(grade);
        return "redirect:/admin/allGrade";
    }

    @RequestMapping("/deleteGrade")
    public String deleteQues(Model model, @RequestParam("gradeId") String gradeid){
        gradeService.delete(Integer.parseInt(gradeid));
        List< Grade> gradeList= gradeService.find(new  Grade());
        model.addAttribute("grade",gradeList);
        return "redirect:/admin/allGrade";

    }
    @RequestMapping("/toUpdateGrade")
    public String toUpdGrade(int gradeId, Model model, HttpSession session){
        Grade gradeInfo = gradeService.get(gradeId);
        String courseName= "";
        Course course=null;
        List<Course> dataList = courseService.find(course);

        model.addAttribute("dataList", dataList);
        String id = gradeInfo.getCourseId();
        String ids[]=null;
        ArrayList<String> cnames=new ArrayList<String>();
        if(id != null){

            ids = id.split(",");
            for(int i=0;i<ids.length;i++){
                course = courseService.get(Integer.parseInt(ids[i]));

                String name=course.getCourseName();
                cnames.add(name);

                courseName+=course.getCourseName()+",";
            }
        }
        //判断最后一个字符是否为逗号，若是截取
        String str = courseName.substring(courseName.length() -1, courseName.length());
        if(",".equals(str)){
            str = courseName.substring(0, courseName.length()-1);
        }else{
            str = courseName;
        }
        gradeInfo.setCourseId(str);
//        List<String> list = Arrays.asList(cnames );//数组转成List
//        ArrayList<String> courseIds= new ArrayList<>(list);
//
        model.addAttribute("courseNames",cnames);
        model.addAttribute("grade", gradeInfo);
        return "grade-upd";
    }


    @RequestMapping("/updateGrade")
    public String updateUser(Grade grade, Model model,String courseId){
        gradeService.update(grade);
        return "redirect:/admin/allGrade";
    }
}
