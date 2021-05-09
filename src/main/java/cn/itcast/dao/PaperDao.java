package cn.itcast.dao;

import cn.itcast.domain.Paper;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface PaperDao extends BaseDao<Paper> {
	/**
	 * 通过学生编号获取所有的试卷
	 * @param id
	 * @return
	 */
	public List<Paper> getUserPaperById(Serializable id);
	public List<Paper> findByGrade(String searchInfo);
	public List<Paper> findByCourse(String searchInfo);
	public List<Paper> findByGC(Integer gradeId,Integer courseId);
	public List<Paper> getDone(String paperId);
	/**查看试卷详情*/
	public Paper getPaperDetail(Map map);
	
	/**更新用户试卷信息*/
	public void updateUserPaper(Map map);
	/**查询未考试的试卷*/
	public List<Paper> getUndoPaper(Map map);
	/**查询学生未考试的试卷*/
	public List<Paper> qryUndoPaper(Map map);
}
