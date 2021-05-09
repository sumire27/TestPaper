package cn.itcast.service;

import cn.itcast.domain.Course;


import java.util.List;

public interface CourseService {
	public List<Course> find(Course course);
	public Course get(Integer id);
	public void insert(Course course);
	public void update(Course course);
	public void delete(Integer id);
	//public PageInfo<Course> findByPage(Course course, Integer pageNo, Integer pageSize);
}
