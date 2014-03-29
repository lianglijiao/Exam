package com.supinfo.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.supinfo.dao.StudentDao;
import com.supinfo.entity.Student;
import com.supinfo.exception.StudentNotExistException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		try {
			System.out.println("添加用户:（a）     删除用户:（b）     查询成绩:（c）");
			System.out.print("请输入操作类型：");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String type = br.readLine();

			if ("a".equals(type)) {
				System.out.print("请输入学生姓名：");
				String name = br.readLine();

				System.out.print("请输入学生准考证号码：");
				String examid = br.readLine();

				System.out.print("请输入学生身份证号码：");
				String idcard = br.readLine();

				System.out.print("请输入学生所在地：");
				String location = br.readLine();

				System.out.print("请输入学生分数：");
				String grade = br.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("用户添加成功！！");

			} else if ("b".equals(type)) {

				try {
					System.out.print("请输入删除学生的姓名：");
					String name = br.readLine();
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("您已经成功删除");
				} catch (StudentNotExistException e) {
					System.out.println("您要删除的用户不存在！！！");
				}

			} else if ("c".equals(type)) {
				
				
				System.out.print("请输入要查找学生的准考证号码：");
				String examid = br.readLine();
				StudentDao dao = new StudentDao();
				dao.find(examid);
				System.out.println("查找成功");
				
				

			} else {
				System.out.println("对不起，不支持您的操作！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("对不起，俺出错了！！");
		}

	}

}
