package cn.itcast.service.impl;


import cn.itcast.dao.TypeDao;
import cn.itcast.domain.Type;
import cn.itcast.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	TypeDao typeDao;
	
	public List<Type> find(Type type) {
		return typeDao.find(type);
	}

	public Type get(Integer id) {
		return typeDao.get(id);
	}

	public void insert(Type type) {
		typeDao.insert(type);
	}

	public void update(Type type) {
		typeDao.update(type);
	}

	public void delete(Integer id) {
		typeDao.delete(id);
	}



}
