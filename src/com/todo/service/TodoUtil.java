package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList list) {

		String title, desc;
		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "---------�׸� �߰�---------\n"
				+ "���� �Է� > \n");

		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("���� �ߺ�!");
			return;
		}
		sc.nextLine();

		System.out.println("���� �Է� > ");
		desc = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�߰� �Ϸ�. ");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);


		System.out.println("\n"
				+ "---------�׸� ����---------\n"
				+ "������ ������ �Է� > ");

		String title = sc.next();

		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("���� �Ϸ�. ");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n"
				+ "---------�׸� ����---------\n"
				+ "������ �׸� ���� > ");

		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("������ �������� ����.");
			return;
		}

		System.out.println("���ο� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("���� �ߺ�!");
			return;
		}

		System.out.println("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("���� �Ϸ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("---------LIST---------");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void loadList(TodoList l, String filename ) {


		try {
			int count=0;
			
			BufferedReader br  = new BufferedReader(new FileReader(filename));
			String oneline;

			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String name = st.nextToken();
				String content = st.nextToken();
				String time = st.nextToken();
				TodoItem item = new TodoItem(name, content);
				item.setCurrent_date(time);
				l.addItem(item);	
				count++;
			}
			if(count ==0) {
				System.out.println("�ҷ��� �׸��� �����ϴ�.");
			}
			else {
				System.out.println(count+"���� �׸��� �ҷ��Խ��ϴ�.");
			}
			br.close();
		}
		catch (IOException e) {
			System.out.println("������ ã�� �� ����.");
		}



	}

	public static void saveList(TodoList l, String filename ) {


		try {

			Writer w = new FileWriter(filename);

			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}

			w.close();

			System.out.println("��� ���� ���� �Ϸ�.");
		} catch (IOException e) {
		}

	}
}
