package cn.itcast.controller.user;


import cn.itcast.domain.*;
import cn.itcast.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 试卷综合管理
 * @author hspcadmin
 *
 */
@Controller
public class PaperMgController {

	@Autowired
	UserService userService;
	@Autowired
	GradeService gradeService;
	@Autowired
	PaperService paperService;
	@Autowired
	CourseService courseService;
	@Autowired
	QuestionService questionService;
	@Autowired
	ErrorBookService bookService;
	
	//跳转到成绩查询页面
	@RequestMapping("/toScoreQry.action")
	public String toScoreQry(User user, Model model, HttpSession session){
		System.out.println("toScoreQry"+user.toString());
		User user1;
		if("".equals(user.getUserId()) || user==null){
			user = (User) session.getAttribute("user");
		}
		if(session.getAttribute("user")== null){
			session.setAttribute("user", userService.findStuById(user.getUserId()));
		}
		user1 = userService.findStuById(user.getUserId());
		List<Paper> paper = paperService.getUserPaperById(user1.getUserId());
		Course course = null;
		for(Paper p : paper){
			course = courseService.get(Integer.parseInt(p.getCourseId()));
			p.setCourseId(course.getCourseName());
		}
		model.addAttribute("user", user1);
		model.addAttribute("paper", paper);
		return "/user/scorequery";
	}
	
	/**
	 * 查看试卷详情
	 * @param paperId
	 * @param userId
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/qrypaper.action")
	public String qrypaper(String paperId, String userId, Model model, HttpSession session){
		Map map = new HashMap();
		map.put("paperId", paperId);
		map.put("userId", userId);
		Paper paper = paperService.getPaperDetail(map);
		Question question = null;
		String []ids = paper.getQuestionId().split(",");
		List<Question> selList = new ArrayList<Question>();
		List<Question> inpList = new ArrayList<Question>();
		List<Question> desList = new ArrayList<Question>();
		for(int i = 0;i<ids.length;i++){
			question = questionService.get(Integer.parseInt(ids[i]));
			if("1".equals(question.getTypeId())){//单选
				selList.add(question);
			}
			if("4".equals(question.getTypeId())){//填空
				inpList.add(question);
			}
			if("5".equals(question.getTypeId())){//简答题
				desList.add(question);
			}
		}
		
		if(selList.size()>0){
			model.addAttribute("selectQ", "单项选择题（每题5分）");
			model.addAttribute("selList", selList);
		}
		
		if(inpList.size()>0){
			model.addAttribute("inpQ", "填空题（每题5分）");
			model.addAttribute("inpList", inpList);
		}
		
		if(desList.size()>0){
			model.addAttribute("desQ", "简答题（每题5分）");
			model.addAttribute("desList", desList);
		}

		model.addAttribute("paper", paper);
		return "/user/qrypaper";
	}
	
	/**
	 * 考试完之后处理试卷，自动评分
	 * @param paper
	 * @param model
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/dealPaper.action")
	@ResponseBody
	public MsgItem dealPaper( Paper paper, Model model, HttpSession session) throws UnsupportedEncodingException{
		String paperId = paper.getPaperId();
		//答案临时存放
		String ans = paper.getScore();
		ans = URLDecoder.decode(ans,"UTF-8");
		System.out.println("得分"+ans);
		String [] answer = null;
		if(ans.contains("&")){
			answer = ans.split("&");
		}
		Map map = new HashMap();
		User user = (User) session.getAttribute("user");
		map.put("paperId", paperId);
		map.put("userId", user.getUserId());
		Paper paperInfo = paperService.getPaperDetail(map);
		String []ids = paperInfo.getQuestionId().split(",");
		List<Question> question = new ArrayList<Question>();
		Question ques = null;
		int endScore = 0;
		ErrorBook book = new ErrorBook();
		book.setUserId(user.getUserId());
		for(int i = 1 ;i<answer.length;i++){
			String[] str = answer[i].split("=");
			//题号
			String str1 = str[0];
			ques = questionService.get(Integer.parseInt(str1));
			//数据库对应的答案
			String answer1 = ques.getAnswer();
			if(str.length>1){
				//学生的答案
				String str2 = str[1];
				if(!"5".equals(ques.getTypeId())){//判断是否为简答题
					if(str2.equals(answer1)){//如果用户答案和数据库中的答案一致
						endScore+=5;
					}else{//插入错题本
						book.setQuestion(ques);
						book.setCourseId(ques.getCourseId());
						book.setGradeId(ques.getGradeId());
						book.setUserAnswer(str2);
						bookService.insert(book);
					}
				}

			}
		}
		System.out.println("最后得分："+endScore);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Date currentTime = new Date();//得到当前系统时间  
		String endTime = formatter.format(currentTime); //将日期时间格式化  
		map.put("beginTime", paper.getBeginTime());
		map.put("endTime", endTime);
		map.put("score", endScore);
		//将考试的试卷状态改为2
		map.put("paperState", "2");
		paperService.updateUserPaper(map);
		if(session.getAttribute("user")== null){
			session.setAttribute("user", user);
		}
		MsgItem msgItem = new MsgItem();
		msgItem.setErrorNo("1");
		msgItem.setErrorInfo("试卷提交成功，本次考试得分："+endScore +"分");
		model.addAttribute("msgItem",msgItem);
		return msgItem;		
	}
	
	/**
	 * 点击试卷名称跳转到考试页面
	 * @param paperId
	 * @param userId
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/qryPaperDetail.action")
	public String qryPaperDetail(String paperId, String userId, Model model, HttpSession session){
		Map map = new HashMap();
		map.put("paperId", paperId);
		map.put("userId", userId);
		User user=userService.findStuById(userId);
		Paper paper = paperService.getPaperDetail(map);
		Question question = null;
		String []ids = paper.getQuestionId().split(",");
		List<Question> selList = new ArrayList<Question>();
		List<Question> inpList = new ArrayList<Question>();
		List<Question> desList = new ArrayList<Question>();
		for(int i = 0;i<ids.length;i++){
			question = questionService.get(Integer.parseInt(ids[i]));
			if("1".equals(question.getTypeId())){//单选
				selList.add(question);
			}
			if("4".equals(question.getTypeId())){//填空
				inpList.add(question);
			}
			if("5".equals(question.getTypeId())){//简答题
				desList.add(question);
			}
		}
		
		if(selList.size()>0){
			model.addAttribute("selectQ", "单项选择题（每题5分）");
			model.addAttribute("selList", selList);
		}
		
		if(inpList.size()>0){
			model.addAttribute("inpQ", "填空题（每题5分）");
			model.addAttribute("inpList", inpList);
		}
		
		if(desList.size()>0){
			model.addAttribute("desQ", "简答题（每题5分）");
			model.addAttribute("desList", desList);
		}
		model.addAttribute("user",user);
		model.addAttribute("paper", paper);
		return "/user/paperdetail";
	}
	
	/**
	 * 获取未考试试卷，并将为考试的试卷添加用户信息
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/toMyPaperPage.action")
	public String toMyPaperPage(User user, Model model, HttpSession session){
		System.out.println(user.toString());
		User user1;
		if("".equals(user.getUserId()) || user.getUserId()==null){
			user1 = (User) session.getAttribute("user");
			System.out.println("if1"+user.toString());
		}
		if(session.getAttribute("user")== null){
			user1=userService.findStuById(user.getUserId());
			session.setAttribute("user", user1);
			System.out.println("if2"+user1.toString());
		}
		user1= userService.findStuById(user.getUserId());
		System.out.println("else"+user1.toString());
		Map map =new HashMap();
		map.put("userId", user1.getUserId());
		//List<Paper> paper = paperService.getUserPaperById(user.getUserId());
		List<Paper> paper1 = paperService.getUndoPaper(map);
		System.out.println("paper"+paper1);
		Course course = null;
		for(Paper p : paper1){
			course = courseService.get(Integer.parseInt(p.getCourseId()));
			p.setUserId(user1.getUserId());
			p.setPaperstate("1");
			paperService.insert(p);
			p.setCourseId(course.getCourseName());
		}
		List<Paper> paper = paperService.qryUndoPaper(map);
		for(Paper p : paper){
			course = courseService.get(Integer.parseInt(p.getCourseId()));
			p.setCourseId(course.getCourseName());
		}
		model.addAttribute("user", user1);
		model.addAttribute("paper", paper);
		return "/user/mypaper";
	}
}
