package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.dao.QuestionDao;
import edu.numberone.studystar.daoimpl.QuestionDaoImple;
import edu.numberone.studystar.entity.Course;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.QuestionType;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.QuestionService;

public class QuestionServiceImple implements QuestionService {

	/**
	 * 修改试题信息到model
	 * 
	 * @param question
	 *            需要修改的试题信息
	 * @return 返回已修改的试题信息 ，如果修改成功则返回成功提示标识，否则返回为null
	 * 
	 */
	@Override
	public int updateQuestionToModel(Question question) {
		// TODO Auto-generated method stub
		QuestionDao questionDao = new QuestionDaoImple();
		int result = questionDao.updateQuestionToDB(question);

		if (result == 1) {
			return result;
		}
		return 0;
	}

	/**
	 * 查找所有试题信息
	 * 
	 */
	@Override
	public Pager<Question> findQuestionFromModel(int pageNum, int pageSize, String type,String keyword) {
		// TODO Auto-generated method stub
		QuestionDao questionDao = new QuestionDaoImple();
		//int startIndex = (pageNum - 1) * pageSize;// 开始查询的索引
		StringBuilder sql = new StringBuilder();
		sql.append("select * from question where 1=1");
		if (!"0".equals(type)) {
			sql.append(" and Q_Type='" + type+"'");
		}
		if (!"".equals(keyword)&&null!=keyword) {
			sql.append(" and q_title like"+" '%" +keyword+"%'");
		}
		//sql.append(" limit " + startIndex);
		//sql.append("," + pageSize);
		List<Question> questions = questionDao.findQuestionsFromDB(sql.toString(), null);
		if (questions != null) {
			// 分页查询
			Pager<Question> pager = new Pager<>(questions,pageNum, pageSize);
			if (pager != null) {
				return pager;
			}
		}
		return null;
	}

	/**
	 * 添加试题信息到model
	 * 
	 * @param person
	 *            需要添加person
	 * 
	 */
	@Override
	public int AddQuestionToModel(Question question) {
		// TODO Auto-generated method stub
		QuestionDao questionDao = new QuestionDaoImple();
		int result = questionDao.addQuestionToDB(question);

		if (result == 1) {
			return result;
		}
		return 0;
	}

	/**
	 * 删除试题信息到model
	 * 
	 * @param questions
	 *            需要需要删除的question集合
	 */
	@Override
	public int deletePersonInformationToMedel(List<Question> questions) {
		// TODO Auto-generated method stub
		QuestionDao questionDao = new QuestionDaoImple();
		int result = questionDao.deleteQuestionToDB(questions);

		if (result == 1) {
			return result;
		}
		return 0;
	}

	@Override
	public Question findQuestionById(String id) {
		// TODO Auto-generated method stub
		String sql=new String();
		sql="select * from question where q_id='"+id+"'";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Question.class);
		Question question=querys.getBean();
		return question;
	}

	@Override
	public List<QuestionType> findAllQuestionType() {
		// TODO Auto-generated method stub
		String sql=new String();
		sql="select * from questionType";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, QuestionType.class);
		List<QuestionType> list=(List<QuestionType>) querys.getResultList();
		return list;
	}

	@Override
	public List<Course> findAllCourseType() {
		// TODO Auto-generated method stub
		String sql=new String();
		sql="select * from course";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Course.class);
		List<Course> list=(List<Course>) querys.getResultList();
		return list;
	}

	@Override
	public List<Question> findAllQuestion(String courseType, String questionType) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from question");
		if(null!=courseType&&!courseType.equals("0")){
			sql.append(" where course_id='"+courseType+"'");
		}
		if(null!=questionType&&!questionType.equals("0")){
			sql.append(" and q_type='"+questionType+"'");
		}
		System.out.println(sql.toString());
		System.out.println(Question.class);
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), Question.class);
		List<Question> list=(List<Question>) querys.getResultList();
		return list;
	}

	@Override
	public List<Question> findSelectedQuestion(String[] qids) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from question where q_id in (");
		for(int i=0;i<qids.length;i++){
			if(i!=qids.length-1){
			sql.append("'"+qids[i]+"',");
			}else{
				sql.append("'"+qids[i]+"'");
			}
		}
		sql.append(")");
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), Question.class);
		List<Question> list=(List<Question>) querys.getResultList();
		return list;
	}

}
