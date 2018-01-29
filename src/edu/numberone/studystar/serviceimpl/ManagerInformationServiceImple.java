package edu.numberone.studystar.serviceimpl;

import java.util.List;

import org.apache.commons.lang.text.StrBuilder;

import edu.numberone.studystar.dao.PersonInfomationDao;
import edu.numberone.studystar.daoimpl.ManagerInformationDaoImple;
import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.service.PersonInformationService;

public class ManagerInformationServiceImple implements PersonInformationService<Manager> {

	/**
	 * 从model的Dao层获得管理员信息
	 * 
	 * @param teacher
	 *            需要查找的管理员信息
	 * @return 返回已查找的管理员信息 ，如果查找成功则返回管理员信息，否则返回为null
	 * 
	 */
	@Override
	public Manager findPersonInformationFromModel(Manager manager) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Manager> personInfomationDao = new ManagerInformationDaoImple();
		Manager manage = personInfomationDao.findPersonInforFromDB(manager);

		if (manage != null) {
			return manage;
		}

		return null;
	}

	@Override
	public int updatePersonInformationToModel(Manager manager) {
		// TODO Auto-generated method stub

		PersonInfomationDao<Manager> personInfomationDao = new ManagerInformationDaoImple();
		int result = personInfomationDao.updatepersonInformationToDB(manager);
		System.out.println("result--->" + result);
		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 查找出所有管理员信息
	 * 
	 */
	@Override
	public Pager<Manager> findAllPersonInformationFromModel(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Manager> personInfomationDao = new ManagerInformationDaoImple();
		 
		int startIndex = (pageNum - 1) * pageSize;// 开始查询的索引
		StrBuilder sql = new StrBuilder();
		sql.append("select * from manager where 1=1");
		sql.append(" limit " + startIndex);
		sql.append("," + pageSize);
		List<Manager> list = personInfomationDao.findPersonInforFromDB(sql.toString(), null);

		//分页查询
		Pager<Manager> pager = new Pager<>(pageNum, pageSize, list);
		if (pager != null) {
			return pager;
		}

		return null;
	}

	@Override
	public int AddPersonInformationToModel(Manager manager) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Manager> personInfomationDao = new ManagerInformationDaoImple();
		int result = personInfomationDao.addPersonInformationToDB(manager);
		System.out.println("result--->" + result);
		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 添加人员信息到model
	 * 
	 * @param managers
	 *            需要需要删除的managers集合
	 */
	@Override
	public int deletePersonInformationToMedel(List<Manager> managers) {
		// TODO Auto-generated method stub
		PersonInfomationDao<Manager> personInfomationDao = new ManagerInformationDaoImple();
		int result = personInfomationDao.deletePersonInformationToDB(managers);
		System.out.println("result--->" + result);
		if (result == 1) {
			return result;
		}

		return 0;
	}

	/**
	 * 修改管理员上次登录的时间
	 * 
	 * @param manager需要修改的管理员
	 * 
	 */
	public int updateManagerLastTime(Manager manager) {

		ManagerInformationDaoImple managerInfomationDao = new ManagerInformationDaoImple();
		int result = managerInfomationDao.updateManagerLastTime(manager);
		System.out.println("result--->" + result);
		if (result == 1) {
			return result;
		}

		return 0;

	}

}
