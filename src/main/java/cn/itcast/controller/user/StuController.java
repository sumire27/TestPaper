package cn.itcast.controller.user;

import cn.itcast.domain.Grade;
import cn.itcast.domain.MsgItem;
import cn.itcast.domain.User;
import cn.itcast.service.GradeService;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StuController {
	@Autowired
	UserService userService;
	@Autowired
	GradeService gradeService;
	//跳转到前台登录页面
	@RequestMapping("/toLogin")
	public String toUserLogin(User user, Model model, HttpSession session){
		if(session.getAttribute("userName")!= null){
			return "/user/index";
		}
		if(session.getAttribute("user")== null){
			session.setAttribute("user", userService.findStuById(user.getUserId()));
		}
		List<User> dataList = userService.findAllStu();
		model.addAttribute("dataList", dataList);
		return "/user/login";
	}
		
	/**
	 * 前台用户登录
	 * @param userId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/toIndex.action")
	public String toIndex(String userId, Model model, HttpSession session){
		User user1=userService.findStuById(userId);
		System.out.println("user1在这"+userId);
		model.addAttribute("user",user1);
		session.setAttribute("user",user1);
		String userName=user1.getUserName();
		System.out.println(userName);
		if(userName!= null){
			System.out.println("toindex"+userName);
			return "/user/index";
		}else{
			return "forward:/toLogin";
		}
	}
	
	/**
	 * 用户账号密码检查
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkPwd.action")
	@ResponseBody
	public MsgItem checkPwd(User user, Model model, HttpSession session){
		MsgItem item = new MsgItem();
		User loginUser = userService.login(user);
		if(loginUser!=null && loginUser.getUserType() ==0){
			if(loginUser.getUserState()==0 ){
				item.setErrorNo("1");
				item.setErrorInfo("该账号尚未通过审核!");
			}else{
				item.setErrorNo("0");
				item.setErrorInfo("登录成功!");
				session.setAttribute("userName", loginUser.getUserName());
				session.setAttribute("user", loginUser);
			}
		}else{
			item.setErrorNo("1");
			item.setErrorInfo("账号不存在或用户名密码错误!");
		}
		return item;
	}
	
	@RequestMapping("/toRegistPage")
	public String toRegistPage(Model model, HttpSession session){
		List<Grade> list = gradeService.find(new Grade());
		model.addAttribute("grade", list);
		return "/user/regist";
	}
	
	/**
	 * 添加用户信息
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUserInfo.action")
	public String addUserInfo(User user, Model model, HttpSession session){
		userService.addUser(user);
		return "redirect:/toLogin";
	}
	
	//跳转到前台登录页面
	@RequestMapping("/toUserInfo.action")
	public String toUserInfo(User user, Model model, HttpSession session){
	//	System.out.println(user.toString());
		User loginUser = userService.findStuById(user.getUserId());
		System.out.println(loginUser.toString());
		Grade grade = gradeService.get(Integer.parseInt(loginUser.getGrade()));
		//loginUser.setGrade(grade.getGradeName());
		model.addAttribute("gradeName",grade.getGradeName());
		model.addAttribute("user", loginUser);
		return "/user/userinfo";
	}
	
	/**
	 * 更新学生信息
	 * @param user
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateUserInfo.action")
	public String updateUserInfo(String newPwd, User user, Model model, HttpSession session){
		if(newPwd!= null && newPwd.trim().length()>0){
			user.setUserPwd(newPwd);
			System.out.println("运行至此。。。");
		}
		userService.updateUser(user);
		user = userService.findStuById(user.getUserId());
		if(session.getAttribute("user")== null){
			session.setAttribute("user", userService.getStu(user));
		}
		return "redirect:/user/toIndex.action?userId="+user.getUserId();
	}
	
	//跳转到登录页面
	@RequestMapping("/user/exitSys.action")
	public String exitSystem(User user, Model model, HttpSession session){
		if(session.getAttribute("userName")!= null){
			session.removeAttribute("userName");
			return "/user/login";
		}
		return "/user/login";
	}
	
	//跳转到前台登录页面
	@RequestMapping("/toAbout.action")
	public String toAbout(User user, Model model, HttpSession session){
		User loginUser = (User) session.getAttribute("user");
		model.addAttribute("user", loginUser);
		return "/user/about";
	}
}
