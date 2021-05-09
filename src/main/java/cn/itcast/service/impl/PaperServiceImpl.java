package cn.itcast.service.impl;


import cn.itcast.dao.PaperDao;
import cn.itcast.domain.Paper;
import cn.itcast.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService  {

	@Autowired
	PaperDao paperDao;
	public List<Paper> find(Paper paper) {
		return paperDao.find(paper);
	}

	@Override
	public List<Paper> findByGrade(String searchInfo) {
		return paperDao.findByGrade(searchInfo);
	}

	@Override
	public List<Paper> findByCourse(String searchInfo) {
		return paperDao.findByCourse(searchInfo);
	}

	@Override
	public List<Paper> findByGC(Integer gradeId,Integer courseId){
		return paperDao.findByGC(gradeId,courseId);
	}

	public Paper get(Serializable id) {
		return paperDao.get(id);
	}

	public void insert(Paper paper) {
		paperDao.insert(paper);
	}

	public void update(Paper paper) {
		paperDao.update(paper);
	}

	public void delete(Serializable id) {
		paperDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		paperDao.delete(ids);
	}

	public List<Paper> getUserPaperById(Serializable id) {
		// TODO Auto-generated method stub
		return paperDao.getUserPaperById(id);
	}

	public Paper getPaperDetail(Map map) {
		// TODO Auto-generated method stub
		return paperDao.getPaperDetail(map);
	}

	public void updateUserPaper(Map map) {
		// TODO Auto-generated method stub
		paperDao.updateUserPaper(map);
	}

	public List<Paper> getUndoPaper(Map map) {
		// TODO Auto-generated method stub
		return paperDao.getUndoPaper(map);
	}

	public List<Paper> qryUndoPaper(Map map) {
		// TODO Auto-generated method stub
		return paperDao.qryUndoPaper(map);
	}

	@Override
	public List<Paper> getDone(String paperId) {
		return paperDao.getDone(paperId);
	}
}
