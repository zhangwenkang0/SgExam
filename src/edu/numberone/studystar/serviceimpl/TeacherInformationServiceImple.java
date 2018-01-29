package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.dao.PersonInfomationDao;
import edu.numberone.studystar.daoimpl.TeacherInformationDaoImple;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.entity.Teacher;
import edu.numberone.studystar.service.PersonInformationService;

public class TeacherInformationServiceImple implements PersonInformationService<Teacher> {

	/**
	 * 从model的Dao层获得教师信息
	 * 
	 * @param teacher
	 *            需要查找的学生信息
	 * @return 返回已查找的教师信息 ，如果查找成功则返回教师信息，否则返回为null
	 * 
	 */
	@Override
	public Teacher findPersonInformationFromModel(Teacher teacher) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Teacher> personInfomationDao = new TeacherInformationDaoImple();
		Teacher tea = personInfomationDao.findPersonInforFromDB(teacher);

		if (tea != null) {
			return tea;
		}

		return null;
	}

	/**
	 * 修改教师信息
	 * 
	 * @param person
	 *            需要修改教师信息
	 * @return 返回已删教师除教师信息 ，如果教师成功，返回1，否则返回0
	 * 
	 */
	@Override
	public int updatePersonInformationToModel(Teacher person) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Teacher> personInfomationDao = new TeacherInformationDaoImple();
		int result = personInfomationDao.updatepersonInformationToDB(person);

		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 查找所有的教师信息
	 * 
	 * @param teacher
	 *            需要添加教师信息
	 * @return 返回已添加教师信息 ，如果添加成功，返回1，否则返回0
	 * 
	 */
	@Override
	public Pager<Teacher> findAllPersonInformationFromModel(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Teacher> personInfomationDao = new TeacherInformationDaoImple();
		int startIndex = (pageNum - 1) * pageSize;// 开始查询的索引

		StringBuilder sql = new StringBuilder();
		sql.append(
				"select T_ID,T_Pass,T_Name,t_Job from teacher ");
		sql.append(" where 1=1");
		sql.append(" limit " + startIndex);
		sql.append("," + pageSize);

		List<Teacher> teachers = personInfomationDao.findPersonInforFromDB(sql.toString(), null);

		// 分页查询
		Pager<Teacher> pager = new Pager<>(pageNum, pageSize, teachers);
		if (pager != null) {
			return pager;
		}

		return null;
	}

	/**
	 * 从添加教师信息到model
	 * 
	 * @param teacher
	 *            需要添加教师信息
	 * @return 返回已添加教师信息 ，如果添加成功，返回1，否则返回0
	 * 
	 */
	@Override
	public int AddPersonInformationToModel(Teacher person) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Teacher> personInfomationDao = new TeacherInformationDaoImple();
		int result = personInfomationDao.addPersonInformationToDB(person);

		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 从model删除教师信息
	 * 
	 * @param teacher
	 *            需要删除教师信息
	 * @return 返回已删除教师信息 ，如果删除成功，返回1，否则返回0
	 * 
	 */
	@Override
	public int deletePersonInformationToMedel(List<Teacher> persons) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Teacher> personInfomationDao = new TeacherInformationDaoImple();
		int result = personInfomationDao.deletePersonInformationToDB(persons);

		if (result == 1) {
			return result;
		}

		return 0;
	}

}
