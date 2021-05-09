package cn.itcast.service;

import cn.itcast.domain.ErrorBook;


import java.util.List;
import java.util.Map;

public interface ErrorBookService {
	public List<ErrorBook> find(ErrorBook errorBook);
	public ErrorBook get(int id);
	public void insert(ErrorBook errorBook);
	public void update(ErrorBook errorBook);
	public void delete(int id);
	public List<ErrorBook> getBookInfo(Map map);
}
