package edu.numberone.studystar.serviceimpl;

import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.entity.Teacher;
import edu.numberone.studystar.service.PersonInformationService;
import edu.numberone.studystar.service.PresonInformationFactoryService;

public class PersonInformationFactoryServiceImple implements PresonInformationFactoryService {

	/**
	 * 创建一个用于查看用户信息的工厂，用于根据不同的用户查看不同的信息
	 * 
	 * @param flag
	 *            用户身份标识，用于识别用户是教师，管理或者学生
	 * 
	 * 
	 */
	@Override
	public PersonInformationService<?> personFactory(String flag) {
		// TODO Auto-generated method stub

		if (flag != null && !"".equals(flag)) {
			switch (flag) {
			case "student":

				PersonInformationService<Student> StudentInformationServiceImple = new StudentInformationServiceImple();
				return StudentInformationServiceImple;

			case "teacher":
				PersonInformationService<Teacher> teacherInformationServiceImple = new TeacherInformationServiceImple();
				return teacherInformationServiceImple;

			case "manager":
				PersonInformationService<Manager> managerInformationServiceImple = new ManagerInformationServiceImple();
				return managerInformationServiceImple;
			default:
				break;
			}
		}

		return null;

	}

}
