package cn.itcast.service;



import cn.itcast.domain.Type;

import java.util.List;

public interface TypeService {
	public List<Type> find(Type type);
	public Type get(Integer id);
	public void insert(Type type);
	public void update(Type type);
	public void delete(Integer id);
	//public PageInfo<Type> findByPage(Type type, Integer pageNo, Integer pageSize);
}
