package cn.itcast.service;

import cn.itcast.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAllStu();
    List<User> findAllTea();
    User findStuById(String userId);
      void addUser(User user);
      void deleteUser(String userId);
      void updateUser(User user);
      /*
      登录判断
       */
      User login(User user);

      User getStu(User user);
      List<User> findByInfo(String searchInfo);
      List<User> findByGrade(String searchInfo);

}
