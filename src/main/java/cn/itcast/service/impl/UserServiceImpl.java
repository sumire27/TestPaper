package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public List<User> findAllStu() {
        return userDao.findAllStu();
    }

    @Override
    public List<User> findAllTea() {
        return userDao.findAllTea();
    }

    @Override
    public User findStuById(String userId) {
        return userDao.findStuById(userId);
    }

    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        String userPwd = user.getUserPwd();
        //密码加密
        userPwd = MD5Util.getData(userPwd);
        user.setUserPwd(userPwd);
        userDao.addUser(user);
    }

/*
用户登录验证
 */
    @Override
    public User login(User user) {
        User u = findStuById(user.getUserId());
        if(u!=null){
            String userPwd = MD5Util.getData(user.getUserPwd());
            if(userPwd.equals(u.getUserPwd())){
                return u;
            }
        }
        return null;
    }

    @Override
    public User getStu(User user) {
        return userDao.getStu(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findByInfo(String searchInfo) {
        return userDao.findByInfo(searchInfo);
    }

    @Override
    public List<User> findByGrade(String searchInfo) {
        return userDao.findByGrade(searchInfo);
    }

}
