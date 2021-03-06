package cn.itcast.controller;

import cn.itcast.domain.*;
import cn.itcast.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class PaperController {
    @Autowired
    QuestionService questionService;
    @Autowired
    CourseService courseService;
    @Autowired
    TypeService typeService;

    @Autowired
    GradeService gradeService;
    @Autowired
    PaperService paperService;


    @RequestMapping("/allPaper")
    public String allPaper(Model model){
       List<Paper> paperList=paperService.find(new Paper());
        for(Paper paper:paperList){
            Course course;
            Grade grade;
            String courseName= "";
            String gradeName="";
            course = courseService.get(Integer.parseInt(paper.getCourseId()));
            courseName=course.getCourseName();
            grade=gradeService.get(Integer.parseInt(paper.getGradeId()));
            gradeName=grade.getGradeName();
            paper.setGradeId(gradeName);
            paper.setCourseId(courseName);

        }

        model.addAttribute("paperList",paperList);
        return "paperMa";

    }

    @RequestMapping("/toPaperDetail")
    public String toQuesDetail(Paper paper,Model model){
        String paperId=paper.getPaperId();
        Paper paperInfo=paperService.get(paperId);
        Course course=courseService.get(Integer.parseInt(paperInfo.getCourseId()));
        Grade grade=gradeService.get(Integer.parseInt(paperInfo.getGradeId()));
        paperInfo.setGradeId(grade.getGradeName());
        paperInfo.setCourseId(course.getCourseName());
        model.addAttribute("paper", paperInfo);
        String quesId=paperInfo.getQuestionId();
        int selectNum=0;
        int inputNum=0;
        int descNum=0;
        String ids[]=quesId.split(",");

        for(String id:ids){
           Question question=questionService.get(Integer.parseInt(id));
           Integer typeid=Integer.parseInt(question.getTypeId().trim());
           if(typeid==1)
               selectNum++;
           if (typeid==4)
               inputNum++;
           if (typeid==5)
               descNum++;
        }
        model.addAttribute("selectNum",selectNum);
        model.addAttribute("inputNum",inputNum);
        model.addAttribute("descNum",descNum);
        return "paper-qry";
    }
    @RequestMapping("/toAddPaper")
    public String toAddPaper(Paper paper,Model model){
        //??????????????????
        List<Paper> dataList = paperService.find(paper);
        //??????????????????
        List<Course> courseList = courseService.find(new Course());
        //??????????????????
        model.addAttribute("grade", gradeService.find(new Grade()));
        model.addAttribute("type", typeService.find(new Type()));//???????????????????????? ?????????
        model.addAttribute("dataList", dataList);
        model.addAttribute("course", courseList);
        return "paper-reg";
    }
    /**
     * ??????????????????
     * @param paper
     * @param model
     * @return
     */
    @RequestMapping("/addPaper")
    public String addPaper(Paper paper, Model model,@RequestParam int selectNum,@RequestParam int inputNum,@RequestParam int descNum){

        Map map = new HashMap();
        List<Question> selectList = null;   //???????????????
        List<Question> inputList = null;//???????????????
        List<Question> descList = null;//???????????????
        List<Question> paperList = new ArrayList<Question>();
        map.put("gradeId", paper.getGradeId());
        map.put("courseId", paper.getCourseId());
        if(selectNum>0){//?????????
            map.put("num", selectNum);
            map.put("typeId", 1);
            selectList = questionService.createPaper(map);
            System.out.println("???????????????????????????");
            System.out.println("?????????????????????"+selectList);
            paperList.addAll(selectList);
        }
        if(inputNum>0){//?????????
            map.put("num", inputNum);
            map.put("typeId", 4);
            inputList = questionService.createPaper(map);
            System.out.println("???????????????????????????");
            System.out.println("?????????????????????"+selectList);
            paperList.addAll(inputList);
        }
        if(descNum > 0 ){//?????????
            map.put("num", descNum);
            map.put("typeId", 5);
            descList = questionService.createPaper(map);
            System.out.println("???????????????????????????");
            System.out.println("?????????????????????"+selectList);
            paperList.addAll(descList);
        }
        String quesId="";
        for(Question ques : paperList){
            quesId+=ques.getQuestionId()+",";
        }
        if(!quesId.isEmpty()){
            quesId = removeLast(quesId);
        }
        paper.setQuestionId(quesId);
        paper.setPaperstate("0");
        model.addAttribute("selectNum",selectNum);
        model.addAttribute("inputNum",inputNum);
        model.addAttribute("descNum",descNum);
        paperService.insert(paper);
        return "redirect:/admin/allPaper";
    }

    @RequestMapping("/deletePaper")
    public String deletePaper(Model model, @RequestParam("paperId") String paperId){
       paperService.delete(paperId);
        List<Paper> paperList=paperService.find(new Paper());
        model.addAttribute("paperList",paperList);
        return "redirect:/admin/allPaper";

    }
    @RequestMapping("/toUpdatePaper")
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

    @RequestMapping("/updPaper")
    public String updateUser(Question question,Model model){
     //   System.out.println("???????????????????????????"+user);
        questionService.update(question);
        return "redirect:/admin/allQuestion";
    }

    /**
     * ????????????????????????
     * @param str
     * @return
     */
    public String removeLast(String str){
        //??????????????????????????????????????????????????????
        String newStr = str.substring(str.length() -1, str.length());
        if(",".equals(newStr)){
            newStr = str.substring(0, str.length()-1);
        }else{
            newStr = str;
        }
        return newStr;
    }
}
