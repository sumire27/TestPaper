package cn.itcast.service;


import cn.itcast.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
	public List<Question> find(Question question);
	public Question get(Integer id);
	public void insert(Question question);
	public void update(Question question);
	public void delete(Integer id);
	public List<Question> createPaper(Map map);
	public List<Question> findByInfo(String searchInfo);
//	public PageInfo<Question> findByPage(Question question, Integer pageNo, Integer pageSize);
}
