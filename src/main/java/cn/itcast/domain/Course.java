package cn.itcast.domain;

import lombok.Data;

/**
 * 课程表
 * @author hspcadmin
 *
 */
@Data
public class Course {
	/**课程编号*/
	private Integer courseId;
	/**课程名称*/
	private String courseName;
	/**课程状态*/
	private String courseState;



}
