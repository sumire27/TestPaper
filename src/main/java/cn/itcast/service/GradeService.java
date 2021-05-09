package cn.itcast.service;

import cn.itcast.domain.Grade;


import java.util.List;

public interface GradeService {
	public List<Grade> find(Grade grade);
	public Grade get(int id);
	public void insert(Grade grade);
	public void update(Grade grade);
	public void delete(int id);
	//public PageInfo<Grade> findByPage(Grade grade, Integer pageNo, Integer pageSize);
}
