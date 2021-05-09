package cn.itcast.service.impl;


import cn.itcast.dao.QuestionDao;
import cn.itcast.domain.Question;
import cn.itcast.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	public List<Question> find(Question question) {
		return questionDao.find(question);
	}

	public Question get(Integer id) {
		return questionDao.get(id);
	}

	public void insert(Question question) {
		questionDao.insert(question);
	}

	public void update(Question question) {
		questionDao.update(question);
	}

	public void delete(Integer id) {
		questionDao.delete(id);
	}

	public List<Question> createPaper(Map map) {
		// TODO Auto-generated method stub
		System.out.println("执行service");
		return questionDao.createPaper(map);
	}

	@Override
	public List<Question> findByInfo(String searchInfo) {

		return questionDao.findByInfo(searchInfo);
	}
}
