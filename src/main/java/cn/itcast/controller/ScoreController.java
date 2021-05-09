package cn.itcast.controller;

import cn.itcast.domain.Course;
import cn.itcast.domain.Grade;
import cn.itcast.domain.Paper;
import cn.itcast.domain.User;
import cn.itcast.service.CourseService;
import cn.itcast.service.GradeService;
import cn.itcast.service.PaperService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import java.util.logging.SocketHandler;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    UserService userService;
    @Autowired
    GradeService gradeService;
    @Autowired
    PaperService paperService;

    @Autowired
    CourseService courseService;

    @RequestMapping("/toScoreMa")
    public String toScoreMa(User user,Model model){
        List<User> userList=userService.findAllStu();
        List<Grade> gradeList=gradeService.find(new Grade());
        List<Paper> paperList=paperService.find(new Paper());
        List<Course> courseList=courseService.find(new Course());
        for(User user1:userList){
            Grade grade;
            String gradeName= "";
            grade = gradeService.get(Integer.parseInt(user1.getGrade()));
            gradeName=grade.getGradeName();
            user1.setGrade(gradeName);
        }
        model.addAttribute("userList",userList);
        model.addAttribute("gradeList",gradeList);
        model.addAttribute("paperList",paperList);
        model.addAttribute("courseList",courseList);
        return "scoreMa";
    }

    @RequestMapping("/scoreMa")
    @SuppressWarnings( "unchecked ")
    public  String scoreMa(@RequestParam  String paperId, Model model, HttpSession session){
        List<Paper> paperList=paperService.getDone(paperId);
        System.out.println(paperList.size());
        List<User> userList=userService.findAllStu();
        List userName=new ArrayList();
        if(paperList.size()>0) {
            Double sum = 0.0;
            Double ava;
            Double max = Double.parseDouble(paperList.get(0).getScore());
            Double min = Double.parseDouble(paperList.get(0).getScore());
            int count = 0;
            BigDecimal bd;
            Double score;
            String papaerName = paperService.get(paperId).getPaperName();

            for (Paper paper : paperList) {
                User user = userService.findStuById(paper.getUserId());
                String name = user.getUserName();
                userName.add(name);

            }
//计算总分，最高分，最低分，参考人数
            for (int i = 0; i < paperList.size(); i++) {
                sum += Double.parseDouble(paperList.get(i).getScore());
                count++;
                score = Double.parseDouble(paperList.get(i).getScore());
                if (max < score)
                    max = score;
                if (min > score)
                    min = score;
            }
            ava = sum / count;

            bd = new BigDecimal(ava);
            ava = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(userName);
            model.addAttribute("sum", sum);
            model.addAttribute("ava", ava);
            model.addAttribute("max", max);
            model.addAttribute("min", min);
            model.addAttribute("count", count);
            model.addAttribute("userName", userName);
            model.addAttribute("paperList", paperList);
            model.addAttribute("paperName", papaerName);
            return "scoreInfo";
        }
        else return "error";

    }

    @RequestMapping("/select")
    @ResponseBody
    public List<Paper> select( @RequestBody String grade, Model model){
        System.out.println("年级"+grade);

        String gradeId=grade.substring(0,grade.length()-1);

        List<Paper> paperList;

        if(gradeId.equals("0")){
          paperList=paperService.find(new Paper());
        }else
            paperList=paperService.findByGrade(gradeId);

        for(Paper paper:paperList){
            Grade grade1;
            Course course1;
            String gradeName= "";
            String courseName="";
            grade1= gradeService.get(Integer.parseInt(paper.getGradeId()));
            gradeName=grade1.getGradeName();
            course1=courseService.get(Integer.parseInt(paper.getCourseId()));
            courseName=course1.getCourseName();
            paper.setCourseId(courseName);
            paper.setGradeId(gradeName);
        }

        model.addAttribute("paperList",paperList);
        return paperList;
    }

    @RequestMapping("/searchInfo")
    @ResponseBody
    public String searchInfo(String searchInfo, Model model){
        List<Paper> paperList=paperService.getDone(searchInfo);
        List<User> userList=userService.findAllStu();
        List userName=new ArrayList();

        for (Paper paper:paperList){
            User user=userService.findStuById(paper.getUserId());
            String name=user.getUserName();
            userName.add(name);
        }
        model.addAttribute("userName",userName);
        model.addAttribute("paperList",paperList);
        return "score-multi";
    }
    /*
    删除成绩
     */
    @RequestMapping("deleteScore")
    public String deleteScore(String paperId,String userId,Model model){

        List<Paper> paperList=paperService.getDone(paperId);
        List<User> userList=userService.findAllStu();
        List userName=new ArrayList();
        Double sum=0.0;
        Double ava;
        Double max=Double.parseDouble(paperList.get(0).getScore());
        Double min=Double.parseDouble(paperList.get(0).getScore());
        int count=0;
        BigDecimal bd;
        Double score;

        for (int i=0;i<paperList.size();i++){
            if(userId.equals(paperList.get(i).getUserId()))
                paperList.remove(i);
        }

        for (Paper paper:paperList){
            User user=userService.findStuById(paper.getUserId());
            String name=user.getUserName();
            userName.add(name);

        }
//计算总分，最高分，最低分，参考人数
        for(int i=0;i<paperList.size();i++){
            sum+=Double.parseDouble(paperList.get(i).getScore());
            count++;
            score=Double.parseDouble(paperList.get(i).getScore());
            if(max<score)
                max=score;
            if(min>score)
                min=score;
        }
        ava=sum/count;

        bd=new BigDecimal(ava);
        ava=bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(userName);
        model.addAttribute("sum",sum);
        model.addAttribute("ava",ava);
        model.addAttribute("max",max);
        model.addAttribute("min",min);
        model.addAttribute("count",count);
        model.addAttribute("userName",userName);
        model.addAttribute("paperList",paperList);
        return "scoreInfo";
    }
    /*
    修改成绩
     */
    @RequestMapping("toUpdateScore")
    public String toUpdateScore(String paperId,String userId,Model model){
            List<Paper> paperList=paperService.getDone(paperId);
            for(int i=0;i<paperList.size();i++){
                if(paperList.get(i).getUserId().equals(userId)) {
                    model.addAttribute("paper", paperList.get(i));
                    User user=userService.findStuById(paperList.get(i).getUserId());
                    model.addAttribute("userName",user.getUserName());
                    System.out.println(paperList.get(i));
                }
            }
        return "score-upd";
    }
    @RequestMapping("updateScore")
    public String updateScore(Paper paper,Model model){

        Map map=new HashMap();
        map.put("paperId",paper.getPaperId());
        map.put("userId",paper.getUserId());
        //map.put("paperName",paper.getPaperName());
        map.put("score",paper.getScore());
        paperService.updateUserPaper(map);

        return "redirect:/score/scoreMa?paperId="+paper.getPaperId();
    }
}
