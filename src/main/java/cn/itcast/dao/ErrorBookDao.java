package cn.itcast.dao;

import cn.itcast.domain.ErrorBook;


import java.util.List;
import java.util.Map;

public interface ErrorBookDao extends BaseDao<ErrorBook>{
	public List<ErrorBook> getBookInfo(Map map);
}
