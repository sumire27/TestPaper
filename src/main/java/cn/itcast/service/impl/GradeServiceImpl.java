package cn.itcast.service.impl;

import cn.itcast.dao.GradeDao;
import cn.itcast.domain.Grade;
import cn.itcast.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	GradeDao gradeDao;
	
	public List<Grade> find(Grade grade) {
		return gradeDao.find(grade);
	}

	public Grade get(int id) {
		return gradeDao.get(id);
	}

	public void insert(Grade grade) {
		gradeDao.insert(grade);
	}

	public void update(Grade grade) {
		gradeDao.update(grade);
	}

	public void delete(int id) {
		gradeDao.delete(id);		
	}

}
