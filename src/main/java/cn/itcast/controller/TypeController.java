package cn.itcast.controller;

import cn.itcast.domain.Question;
import cn.itcast.domain.Type;
import cn.itcast.domain.User;
import cn.itcast.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;
    @RequestMapping("/allType")
    public String allType(Model model){
        List<Type> typeList=typeService.find(new Type());
        model.addAttribute("typeList",typeList);
        return "typeMa";

    }

    @RequestMapping("/toTypeDetail")
    public String toTypeDetail(Type type,Model model){
       Integer typeId= type.getTypeId();
       Type typeInfo=typeService.get(typeId);
        model.addAttribute("type", typeInfo);
        return "type-qry";
    }
    @RequestMapping("/toAddType")
    public String toAddUser(){

        return "type-reg";
    }
    /**
     * 添加用户信息
     * @param type
     * @param model
     * @return
     */
    @RequestMapping("/addType")
    public String addUser(Type type, Model model){
        typeService.insert(type);
        return "redirect:/admin/allType";
    }

    @RequestMapping("/deleteType")
    public String deleteStu(Model model, @RequestParam("typeId") String  typeid){
       typeService.delete(Integer.parseInt(typeid));
       List<Type> typeList=typeService.find(new Type());
       model.addAttribute("typeList",typeList);
        return "redirect:/admin/allType";

    }
    @RequestMapping("/toUpdateType")
    public String toUpdateType(Model model,Integer typeId){
        Type typeInfo=typeService.get(typeId);
        model.addAttribute("type",typeInfo);
        return "type-upd";
    }

    @RequestMapping("/updateType")
    public String updateUser(Type type, Model model){
        //   System.out.println("修改用户。。。查看"+user);
        typeService.update(type);
        return "redirect:/admin/allType";
    }
}
