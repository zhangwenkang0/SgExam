package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.numberone.studystar.dao.QuestionDao;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.utils.DBUtils;

public class QuestionDaoImple implements QuestionDao {

	/**
	 * 查询试题
	 * 
	 * @param sql
	 *            需要查询的语句
	 * @param params需要查询的参数
	 * @return 返回questtion的集合
	 * 
	 */
	@Override
	public List<Question> findQuestionsFromDB(String sql, String[] parms) {
		// TODO Auto-generated method stub
		DBUtils dbUtils = null;
		Connection conn = null;

		ResultSet resultSet = null;

		PreparedStatement preparedStatement = null;
		dbUtils = DBUtils.getInstance();
		conn = dbUtils.openConnection();

		List<Question> list = new ArrayList<Question>();

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
						Question question = new Question();
						question.setQ_id(resultSet.getString("Q_ID"));
						question.setQ_type(resultSet.getString("Q_Type"));
						question.setQ_title(resultSet.getString("Q_Title"));
						question.setQ_select(resultSet.getString("Q_Select"));
						question.setQ_score(String.valueOf(resultSet.getFloat("Q_Score")));
						question.setQ_answer(resultSet.getString("Q_Answer"));
						question.setCourse_id(resultSet.getString("course_id"));
						question.setQ_explaination(resultSet.getString("q_explaination"));
						list.add(question);

					}

					return list;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			throw new RuntimeException(e.getMessage());
		} finally {
			dbUtils.disResultSet(resultSet);
			dbUtils.disSatement(preparedStatement);
			dbUtils.disConnection(conn);

		}

		return null;
	}

	/**
	 * 修改试题
	 * 
	 * @param question
	 *            需要修改的试题
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 */
	@Override
	public int updateQuestionToDB(Question question) {
		// TODO Auto-generated method stub
		String q_id = null;

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (question != null) {
			q_id = question.getQ_id();

			// 对需要查找的管理工号进行判断，如果管理工号不为null,则得到学生的学号
			q_id = question.getQ_id() != null ? ("".equals(question.getQ_id()) ? null : question.getQ_id()) : null;

			if (q_id != null) {

				try {
					dbUtils = DBUtils.getInstance();
					conn = dbUtils.openConnection();
					StringBuffer sql = new StringBuffer();
					sql.append("UPDATE Question SET ");
					sql.append("Q_Type=?,");
					sql.append("Q_Title=?,");
					sql.append("q_select=?,");
					sql.append("Q_Score=?,");
					sql.append("Q_Answer=?,");
					sql.append("course_id=?,");
					sql.append("q_explaination=?");
					sql.append(" where  Q_ID =?;");

					preparedStatement = conn.prepareStatement(sql.toString());

					preparedStatement.setString(1, question.getQ_type());
					preparedStatement.setString(2, question.getQ_title());
					preparedStatement.setString(3, question.getQ_select());
					preparedStatement.setFloat(4, Float.parseFloat(question.getQ_score()));
					preparedStatement.setString(5, question.getQ_answer());
					preparedStatement.setString(6, question.getCourse_id());
					preparedStatement.setString(7, question.getQ_explaination());
					preparedStatement.setString(8, q_id);
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
	 * 增加试题
	 * 
	 * @param question
	 *            需要增加的试题
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 */
	@Override
	public int addQuestionToDB(Question question) {
		// TODO Auto-generated method stub

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (question != null) {

			try {
				dbUtils = DBUtils.getInstance();
				conn = dbUtils.openConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("insert into Question(q_id,Q_Type,Q_Title,Q_Select,Q_Score,Q_Answer,course_id,Q_Explaination) values (?,?,?,?,?,?,?,?)");

				preparedStatement = conn.prepareStatement(sql.toString());
				preparedStatement.setString(1, question.getQ_id());
				preparedStatement.setString(2, question.getQ_type());
				preparedStatement.setString(3, question.getQ_title());
				preparedStatement.setString(4, question.getQ_select());
				preparedStatement.setFloat(5, Float.parseFloat(question.getQ_score()));
				preparedStatement.setString(6, question.getQ_answer());
				preparedStatement.setString(7, question.getCourse_id());
				preparedStatement.setString(8, question.getQ_explaination());

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

		return 0;
	}

	/**
	 * 删除试题
	 * 
	 * @param questions
	 *            需要删除的试题的集合
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 */
	@Override
	public int deleteQuestionToDB(List<Question> questions) {
		// TODO Auto-generated method stub
		String q_id = null;

		Connection conn = null;

		PreparedStatement preparedStatement = null;

		int result = 0;

		DBUtils dbUtils = null;

		if (questions != null && questions.size() > 0) {

			for (int i = 0; i < questions.size(); i++) {
				q_id = questions.get(i).getQ_id();

				// 对需要查找的试题编号进行判断，如果管理工号不为null,则得到学生的学号
				q_id = questions.get(i).getQ_id() != null
						? ("".equals(questions.get(i).getQ_id()) ? null : questions.get(i).getQ_id()) : null;

				if (q_id != null) {

					try {
						dbUtils = DBUtils.getInstance();
						conn = dbUtils.openConnection();
						StringBuffer sql = new StringBuffer();
						sql.append("DELETE from Question where Q_ID = ?;");

						preparedStatement = conn.prepareStatement(sql.toString());
						preparedStatement.setString(1, q_id);

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
