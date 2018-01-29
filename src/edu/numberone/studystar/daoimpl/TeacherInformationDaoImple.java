package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.numberone.studystar.dao.PersonInfomationDao;
import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.SubAnswer;
import edu.numberone.studystar.entity.Teacher;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.utils.DBUtils;

public class TeacherInformationDaoImple implements PersonInfomationDao<Teacher>{

	
	
	/**
	 * 从数据库获得个人信息
	 * 
	 * @param person
	 *            需要查找的学生信息
	 * @return 返回已查找的教师信息 ，如果查找成功则返回个人信息，否则返回为null
	 * 
	 */
	@Override
	public Teacher findPersonInforFromDB(Teacher person) {
		// TODO Auto-generated method stub
		

		String t_id = null;
		

		Connection conn = null;

		PreparedStatement preparedStatement = null;
		
		ResultSet reSet = null;
		
		DBUtils dbUtils = null;

		if (person != null) {
			t_id = person.getT_Id();
			

			// 对需要查找的教师工号进行判断，如果教师工号不为null,则得到学生的学号
			t_id = person.getT_Id() != null ? ("".equals(person.getT_Id()) ? null : person.getT_Id()) : null;

		
			if (t_id != null) {

				try {
			     dbUtils = DBUtils.getInstance();
				conn = dbUtils.openConnection();
				StringBuffer sql = new StringBuffer();
				  sql.append("select T_ID,T_Pass,T_Name,t_Job from teacher");
				  sql.append(" where T_ID =?;");
				
				
					preparedStatement = conn.prepareStatement(sql.toString());
					preparedStatement.setString(1, t_id);
					
					
			   		 reSet = preparedStatement.executeQuery();
			   		
			   		if(reSet.next()){
			   			
			   			Teacher teacher = new Teacher();
			   			teacher.setT_Id(reSet.getString("t_Id"));
			   			teacher.setT_Name(reSet.getString("t_Name"));
			   			teacher.setT_Pass(reSet.getString("t_Pass"));
			   			teacher.setT_Job(reSet.getString("t_Job"));
			   			return teacher;
			   			
			   			
			   		}	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
					dbUtils.disResultSet(reSet);
					dbUtils.disSatement(preparedStatement);
					dbUtils.disConnection(conn);
					
				}

			}

		}

	
		return null;
	}

	/**
	 * 修改管给教师的信息
	 * 
	 * @param manager
	 *            需要修改的教师员
	 * 
	 */
	public int updatepersonInformationToDB(Teacher teacher) {
		// TODO Auto-generated method stub
		String t_id = null;

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (teacher != null) {
			t_id = teacher.getT_Id();

			// 对需要查找的管理工号进行判断，如果管理工号不为null,则得到学生的学号
			t_id = teacher.getT_Id() != null ? ("".equals(teacher.getT_Id()) ? null : teacher.getT_Id()) : null;

			if (t_id != null) {

				try {
					dbUtils = DBUtils.getInstance();
					conn = dbUtils.openConnection();
					StringBuffer sql = new StringBuffer();
					sql.append("UPDATE teacher SET t_id=?,t_Pass=?,t_Name=?, t_Job=?");
					sql.append(" where 1=1");
					sql.append(" and t_Id = ?;");

					preparedStatement = conn.prepareStatement(sql.toString());
					preparedStatement.setString(1, teacher.getT_Id());
					preparedStatement.setString(2, teacher.getT_Pass());
					preparedStatement.setString(3, teacher.getT_Name());
					preparedStatement.setString(4, teacher.getT_Job());
					preparedStatement.setString(5, t_id);

					result = preparedStatement.executeUpdate();

					return result;

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {

					dbUtils.disSatement(preparedStatement);
					dbUtils.disConnection(conn);

				}

			}

		}

		return 0;
	}

	/**
	 * 从数据库获得教师信息
	 * 
	 * @param sql
	 *            查找教师信息的sql语句
	 * @param 查找条件
	 * @return 返回已查找的教师信息 ，如果查找成功则返回教师信息，否则返回为null
	 * 
	 */
	@Override
	public List<Teacher> findPersonInforFromDB(String sql, String[] parms) {
		// TODO Auto-generated method stub
		DBUtils dbUtils = null;
		Connection conn = null;

		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;
		dbUtils = DBUtils.getInstance();
		conn = dbUtils.openConnection();

		List<Teacher> list = new ArrayList<Teacher>();

		try {

			if (conn != null) {
				preparedStatement = conn.prepareStatement(sql);

				if (preparedStatement != null) {
					if (parms != null && parms.length > 0) {
						for (int i = 0; i < parms.length; i++) {
							System.out.println("i-->" + i);
							preparedStatement.setString(i + 1, parms[i]);
						}
					}
					System.out.println(sql);
					resultSet = preparedStatement.executeQuery();

					ResultSetMetaData reData = resultSet.getMetaData();

					while (resultSet.next()) {
						Teacher teacher = new Teacher();
			   			teacher.setT_Id(resultSet.getString("t_id"));
			   			teacher.setT_Name(resultSet.getString("t_Name"));
			   			teacher.setT_Pass(resultSet.getString("t_Pass"));
			   			teacher.setT_Job(resultSet.getString("t_Job"));
						list.add(teacher);

					}

					return list;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new RuntimeException(e.getMessage());
		}finally {
			dbUtils.disResultSet(resultSet);
			dbUtils.disSatement(preparedStatement);
			dbUtils.disConnection(conn);

		}

		return null;
	}

	/**
	 * 添加教师人员
	 * 
	 * @param manager
	 *            需要添加的教师
	 * 
	 */
	@Override
	public int addPersonInformationToDB(Teacher teacher) {
		String s_id = null;

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (teacher != null) {
			s_id = teacher.getT_Id();

			// 对需要查找的教师工号进行判断，如果教师工号不为null,则得到教师的工号
			s_id = teacher.getT_Id() != null ? ("".equals(teacher.getT_Id()) ? null : teacher.getT_Id()) : null;

			if (s_id != null) {

					dbUtils = DBUtils.getInstance();
					conn = dbUtils.openConnection();
					StringBuffer sql = new StringBuffer();
					sql.append("insert into teacher(T_ID,T_Pass,T_Name,T_Job) values (");
					sql.append("'"+teacher.getT_Id()+"',");
					sql.append("'"+teacher.getT_Pass()+"',");
					sql.append("'"+teacher.getT_Name()+"',");
					sql.append("'"+teacher.getT_Job()+"')");

					JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), Teacher.class);
					result=querys.excuteUpdate();

					return result;



			}

		}

		return 0;
	}

	
	/**
	 * 删除教师信息
	 * @param managers 需要删除的教师
	 * 
	 * */
	@Override
	public int deletePersonInformationToDB(List<Teacher> teachers) {
		// TODO Auto-generated method stub
		String t_id = null;

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (teachers != null && teachers.size() > 0) {
			
			
			for(int i= 0 ; i < teachers.size();i++){
				
				
				t_id = teachers.get(i).getT_Id();

				// 对需要查找的管理工号进行判断，如果管理工号不为null,则得到学生的学号
				t_id = teachers.get(i).getT_Id() != null ? ("".equals(teachers.get(i).getT_Id()) ? null : teachers.get(i).getT_Id()) : null;

				if (t_id != null) {

					try {
						dbUtils = DBUtils.getInstance();
						conn = dbUtils.openConnection();
						StringBuffer sql = new StringBuffer();
						sql.append("DELETE from teacher where T_ID = ?;");

						preparedStatement = conn.prepareStatement(sql.toString());
						preparedStatement.setString(1, t_id);
						
						result = preparedStatement.executeUpdate();

						

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {

						dbUtils.disSatement(preparedStatement);
						dbUtils.disConnection(conn);

					}

				}
				
				

			}
			return result;
		}

		return 0;
	}

}
