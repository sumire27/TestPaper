package cn.itcast.service.impl;

import cn.itcast.dao.ErrorBookDao;
import cn.itcast.domain.ErrorBook;
import cn.itcast.service.ErrorBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ErrorBookServiceImpl implements ErrorBookService {

	@Autowired
	ErrorBookDao bookDao;
	public List<ErrorBook> find(ErrorBook errorBook) {
		return bookDao.find(errorBook);
	}

	public ErrorBook get(int id) {
		return bookDao.get(id);
	}

	public void insert(ErrorBook errorBook) {
		bookDao.insert(errorBook);
	}

	public void update(ErrorBook errorBook) {
		bookDao.update(errorBook);
	}

	public void delete(int id) {
		bookDao.delete(id);
	}

	public List<ErrorBook> getBookInfo(Map map) {
		return bookDao.getBookInfo(map);
	}

}
