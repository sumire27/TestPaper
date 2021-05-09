package cn.itcast.service.impl;


import cn.itcast.dao.CourseDao;
import cn.itcast.dao.QuestionDao;
import cn.itcast.domain.Course;
import cn.itcast.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	CourseDao courseDao;
	public List<Course> find(Course course) {
		return courseDao.find(course);
	}

	public Course get(Integer id) {
		return courseDao.get(id);
	}

	public void insert(Course course) {
		courseDao.insert(course);
	}

	public void update(Course course) {
		courseDao.update(course);
	}

	public void delete(Integer id) {
		courseDao.delete(id);
	}



}
