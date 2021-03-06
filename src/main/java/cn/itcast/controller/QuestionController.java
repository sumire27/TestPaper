package cn.itcast.controller;

import cn.itcast.domain.*;
import cn.itcast.service.CourseService;
import cn.itcast.service.GradeService;
import cn.itcast.service.QuestionService;
import cn.itcast.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    CourseService courseService;
    @Autowired
    TypeService typeService;

    @Autowired
    GradeService gradeService;


    @RequestMapping("/allQuestion")
    public String allQuestion(Model model){
        List<Question> questionList=questionService.find(new Question());
        for(Question ques:questionList){
            Course course;
            Type type;

            String courseName= "";
            String typeName="";
            String gradeName="";
            course = courseService.get(Integer.parseInt(ques.getCourseId()));
            type = typeService.get(Integer.parseInt(ques.getTypeId()));
            gradeName=gradeService.get(Integer.parseInt(ques.getGradeId())).getGradeName();
            courseName=course.getCourseName();
            typeName=type.getTypeName();
            ques.setCourseId(courseName);
            ques.setTypeId(typeName);
            ques.setGradeId(gradeName);
        }

        model.addAttribute("questionList",questionList);
        return "questionMa";

    }

    @RequestMapping("/toQuesDetail")
    public String toQuesDetail(Question question,Model model){
        Integer quesId=question.getQuestionId();
        Question questionInfo=questionService.get(quesId);
        Course course=courseService.get(Integer.parseInt(questionInfo.getCourseId()));
        Type type=typeService.get(Integer.parseInt(questionInfo.getTypeId()));
        Grade grade=gradeService.get(Integer.parseInt(questionInfo.getGradeId()));
        questionInfo.setGradeId(grade.getGradeName());
        questionInfo.setTypeId(type.getTypeName());
        questionInfo.setCourseId(course.getCourseName());
        model.addAttribute("question", questionInfo);
        return "question-qry";
    }
    @RequestMapping("/toAddQues")
    public String toAddQues(Question question,Model model){
        //??????????????????
        List<Question> dataList = questionService.find(question);
        //??????????????????
        List<Course> courseList = courseService.find(new Course());
        //??????????????????
        model.addAttribute("grade", gradeService.find(new Grade()));
        //??????????????????
        model.addAttribute("type", typeService.find(new Type()));
        model.addAttribute("dataList", dataList);
        model.addAttribute("course", courseList);
        return "question-reg";
    }
    /**
     * ????????????
     * @param question
     * @param model
     * @return
     */
    @RequestMapping("/addQues")
    public String addQues(Question question, Model model){
        questionService.insert(question);
        return "redirect:/admin/allQuestion";
    }

    /*
    ????????????
     */
    @RequestMapping("/deleteQues")
    public String deleteQues(Model model, @RequestParam("questionId") Integer quesid){
       questionService.delete(quesid);
        List<Question> questionList=questionService.find(new Question());
        model.addAttribute("questionList",questionList);
        return "redirect:/admin/allQuestion";

    }
    /*
    ????????????
     */
    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(@RequestParam("questionId") String questionId, Model model){
        if(questionId != null){
            String ids[] = questionId.split(",");
            for(int i=0;i<ids.length;i++){
                questionService.delete(Integer.parseInt(ids[i]));
            }
        }
        return "redirect:admin/allQuestion";
    }
    /*
    ??????????????????
     */
    @RequestMapping("/toUpdateQues")
    public String toUpdateUser(@RequestParam("questionId") Integer quesid,Model model){
        Question question=questionService.get(quesid);
        List<Grade>gradeList = gradeService.find(new Grade());
        List<Course>courseList = courseService.find(new Course());
        List<Type> typeList = typeService.find(new Type());
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("courseList", courseList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("question", question);
        return "question-upd";
    }
    /*
    ????????????
     */
    @RequestMapping("/updQuestion")
    public String updateUser(Question question,Model model){
     //   System.out.println("???????????????????????????"+user);
        questionService.update(question);
        return "redirect:/admin/allQuestion";
    }

    /*
    ??????
     */
    @RequestMapping("/searchInfo")
    public String searchInfo(@RequestParam("searchInfo") String searchInfo,Model model){
        List<Question> questionList=questionService.findByInfo(searchInfo);
        for(Question ques:questionList){
            Course course;
            Type type;
            String courseName= "";
            String typeName="";
            course = courseService.get(Integer.parseInt(ques.getCourseId()));
            type = typeService.get(Integer.parseInt(ques.getTypeId()));
            courseName=course.getCourseName();
            typeName=type.getTypeName();
            ques.setCourseId(courseName);
            ques.setTypeId(typeName);
        }
        model.addAttribute("searchInfo",searchInfo);
        model.addAttribute("questionList",questionList);
        return "ques-multi";
    }

}
