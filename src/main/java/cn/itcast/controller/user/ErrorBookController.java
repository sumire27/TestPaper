package cn.itcast.controller.user;

import cn.itcast.domain.*;
import cn.itcast.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ErrorBookController {
	@Autowired
	ErrorBookService bookService;
	@Autowired
	GradeService gradeService;
	@Autowired
	CourseService courseService;
	@Autowired
	TypeService typeService;
	@Autowired
	UserService userService;
	//跳转到我的错题本页面
	@RequestMapping("/toMyBooksPage.action")
	public String toMyBooksPage(User user, Model model, HttpSession session){
		if(session.getAttribute("user")== null){
			session.setAttribute("user", userService.findStuById(user.getUserId()));
		}
		List<ErrorBook> errorBookList = bookService.find(new ErrorBook());
		List<Grade> gradeList = gradeService.find(new Grade());
		List<Course> courseList = courseService.find(new Course());
		List<Type> typeList = typeService.find(new Type());
		Map map = new HashMap();
		map.put("userId", user.getUserId());
		List<ErrorBook> bookList = bookService.getBookInfo(map);
		User user1=userService.findStuById(user.getUserId());
		model.addAttribute("user",user1);
		model.addAttribute("grade", gradeList);
		model.addAttribute("course", courseList);
		model.addAttribute("type", typeList);
		model.addAttribute("errorBook", bookList);
		return "/user/mybooks";
	}
	
	//跳转到前台登录页面
	@RequestMapping("/getBooks.action")
	public String getBooks(User user, Model model, HttpSession session){
		List<Grade> gradeList = gradeService.find(new Grade());
		List<Course> courseList = courseService.find(new Course());
		List<Type> typeList = typeService.find(new Type());
		List<ErrorBook> errorBookList = bookService.getBookInfo(new HashMap());
		User user1=userService.findStuById(user.getUserId());
		model.addAttribute("user",user1);
		model.addAttribute("grade", gradeList);
		model.addAttribute("course", courseList);
		model.addAttribute("type", typeList);
		return "/user/mybooks";
	}
	
}
