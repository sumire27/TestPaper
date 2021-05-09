package cn.itcast.service;




import cn.itcast.domain.Paper;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PaperService {
	public List<Paper> find(Paper paper);
	public List<Paper> findByGrade(String searchInfo);
	public List<Paper> findByCourse(String searchInfo);
	public List<Paper> findByGC(Integer gradeId,Integer courseId);
	public List<Paper> getDone(String paperId);
	public Paper get(Serializable id);
	public void insert(Paper paper);
	public void update(Paper paper);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	/**通过学生编号获取所有的试卷*/
	public List<Paper> getUserPaperById(Serializable id);
	/**查看试卷详情*/
	public Paper getPaperDetail(Map map);
	/**更新用户试卷信息*/
	public void updateUserPaper(Map map);
	/**查询未考试的试卷*/
	public List<Paper> getUndoPaper(Map map);
	/**查询学生未考试的试卷*/
	public List<Paper> qryUndoPaper(Map map);
	//public List<Paper> findAllPage(Paper paper);
}
