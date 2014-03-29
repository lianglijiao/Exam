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
			System.out.println("����û�:��a��     ɾ���û�:��b��     ��ѯ�ɼ�:��c��");
			System.out.print("������������ͣ�");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String type = br.readLine();

			if ("a".equals(type)) {
				System.out.print("������ѧ��������");
				String name = br.readLine();

				System.out.print("������ѧ��׼��֤���룺");
				String examid = br.readLine();

				System.out.print("������ѧ�����֤���룺");
				String idcard = br.readLine();

				System.out.print("������ѧ�����ڵأ�");
				String location = br.readLine();

				System.out.print("������ѧ��������");
				String grade = br.readLine();

				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);

				StudentDao dao = new StudentDao();
				dao.add(s);

				System.out.println("�û���ӳɹ�����");

			} else if ("b".equals(type)) {

				try {
					System.out.print("������ɾ��ѧ����������");
					String name = br.readLine();
					StudentDao dao = new StudentDao();
					dao.delete(name);
					System.out.println("���Ѿ��ɹ�ɾ��");
				} catch (StudentNotExistException e) {
					System.out.println("��Ҫɾ�����û������ڣ�����");
				}

			} else if ("c".equals(type)) {
				
				
				System.out.print("������Ҫ����ѧ����׼��֤���룺");
				String examid = br.readLine();
				StudentDao dao = new StudentDao();
				dao.find(examid);
				System.out.println("���ҳɹ�");
				
				

			} else {
				System.out.println("�Բ��𣬲�֧�����Ĳ�������");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�Բ��𣬰������ˣ���");
		}

	}

}
