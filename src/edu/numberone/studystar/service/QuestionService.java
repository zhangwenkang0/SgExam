package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.entity.Course;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.QuestionType;

public interface QuestionService {
	
	
	
	
	/**修改试题信息到model
	 * @param question 需要修改的试题信息
	 * @return 返回已修改的试题信息 ，如果修改成功则返回成功提示标识，否则返回为null
	 * 
	 * */
   public int updateQuestionToModel(Question question);
   
   
   
 
     /**
   	 *查找所有试题信息
   	 * 
   	 * */
      public Pager<Question> findQuestionFromModel(int pageNum, int pageSize,String type,String keyword);
      
      /**
     	 *根据查找试题信息
     	 * 
     	 * */
        public Question findQuestionById(String id);
        
        /*
    	 * 根据查找试题类型
    	 * */
       public List<QuestionType> findAllQuestionType();
       
       /*根据查找试题类型
   	 * 
   	 * */
      public List<Course> findAllCourseType();
      
      /*根据课程、试题类型查找试题
     	 * 
     	 * */
        public List<Question> findAllQuestion(String courseType,String questionType);
        /*根据试题id查找试题
     	 * 
     	 * */
        public List<Question> findSelectedQuestion(String[] qids);
   
   
   /**
  	 *添加试题信息到model
  	 *@param person 需要添加person
  	 * 
  	 * */
   public int AddQuestionToModel(Question question);
   
   /**
 	 *删除试题信息到model
 	 * @param questions 需要需要删除的question集合
 	 * */
   public int deletePersonInformationToMedel(List<Question> questions);

}
