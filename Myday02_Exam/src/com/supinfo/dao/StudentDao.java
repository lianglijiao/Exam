package com.supinfo.dao;

import javax.xml.stream.Location;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.supinfo.entity.Student;
import com.supinfo.exception.StudentNotExistException;
import com.supinfo.utils.XmlUtils;

public class StudentDao {
	public void add(Student s) {

		try {
			Document document = XmlUtils.getdocument(); // check
			// exception(����ʱ�쳣)

			// ��������װѧ����Ϣ�ı�ǩ�����ԣ�
			Element student_tag = document.createElement("student");
			student_tag.setAttribute("idcard", s.getIdcard());
			student_tag.setAttribute("examid", s.getExamid());

			// �������ڷ�װѧ������ ���ڵ� �����ı�ǩ
			Element name = document.createElement("name");
			Element locaction = document.createElement("location");
			Element grade = document.createElement("grade");

			name.setTextContent(s.getName());
			locaction.setTextContent(s.getLocation());
			grade.setTextContent(s.getGrade() + "");

			student_tag.appendChild(name);
			student_tag.appendChild(locaction);
			student_tag.appendChild(grade);

			// �ѷ�װ��ѧ����Ϣ�ҵ��ĵ� -- �ҵ����ڵ���
			document.getElementsByTagName("exam").item(0).appendChild(
					student_tag);

			// �����ڴ�
			XmlUtils.write2Xml(document);

		} catch (Exception e) {
			throw new RuntimeException(e); // uncheck exception����ʱ�쳣
		}
	}

	public Student find(String examid) {

		try {
			Document document = XmlUtils.getdocument();

			NodeList list = document.getElementsByTagName("student");
			for (int i = 0; i < list.getLength(); i++) {
				Element student_tag = (Element) list.item(i);
				if (student_tag.getAttribute("examid").equals(examid)) {

					// �ҵ�����examid ��ƥ���ѧ����new��һ��student�����װ���ѧ����Ϣ����
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(student_tag.getAttribute("idcard"));
					s.setName(student_tag.getElementsByTagName("name").item(0)
							.getTextContent());
					s.setLocation(student_tag.getElementsByTagName("location")
							.item(0).getTextContent());
					s.setGrade(Double.parseDouble(student_tag
							.getElementsByTagName("grade").item(0)
							.getTextContent()));
					return s;
				}
			}

			return null;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void delete(String name) throws StudentNotExistException {

		try {
			Document document = XmlUtils.getdocument();

			NodeList list = document.getElementsByTagName("name");
			for (int i = 0; i < list.getLength(); i++) {
				if (list.item(i).equals(name)) {
					list.item(i).getParentNode().getParentNode().removeChild(
							list.item(i).getParentNode());
					XmlUtils.write2Xml(document);

					return;
				}
			}

			throw new StudentNotExistException(name + "�����ڣ���");
		} catch (StudentNotExistException e) {
			throw e;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
