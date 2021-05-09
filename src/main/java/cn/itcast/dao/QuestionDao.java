package cn.itcast.dao;



import cn.itcast.domain.Question;

import java.util.List;
import java.util.Map;

public interface QuestionDao extends BaseDao<Question>{

	public List<Question> createPaper(Map map);

}
