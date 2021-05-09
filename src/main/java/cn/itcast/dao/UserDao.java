package cn.itcast.dao;

import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao extends BaseDao {

    List<User> findAllStu();


    List<User> findAllTea();

    User findStuById(String userId);


    void deleteUser(String userId);

    //@Update("update t_user set username=#{username},userpwd=#{userpwd}, grade=#{grade}, usertype=#{usertype}, userstate=#{userstate}, email=#{email}, telephone=#{telephone}, address=#{address}, remark=#{remark} where userid=#{userid}")
    void updateUser(User user);

   // @Insert("insert into t_user (userid,username,userpwd,grade,usertype,userstate,email,telephone,address,remark) values(#{userid},#{username},#{userpwd},#{grade},#{usertype},'0',#{email},#{telephone},#{address},#{remark})")
    void addUser(User user);

    User getStu(User user);
    List<User> findByGrade(String searchInfo);
}
