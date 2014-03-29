package junit.test;

import org.junit.Test;

import com.supinfo.dao.StudentDao;
import com.supinfo.entity.Student;
import com.supinfo.exception.StudentNotExistException;

public class StudentDaoTest {

	@Test
	public void testAdd() {

		StudentDao dao = new StudentDao();

		Student s = new Student();

		s.setName("lianglina");
		s.setExamid("456");
		s.setIdcard("789");
		s.setGrade(100);
		s.setLocation("xingtai");

		dao.add(s);

	}

	@Test
	public void testFind() {

		StudentDao dao = new StudentDao();
		dao.find("456");

	}

	@Test
	public void testDelete() throws StudentNotExistException {
		StudentDao dao = new StudentDao();
		dao.delete("lianglina");

	}

}
