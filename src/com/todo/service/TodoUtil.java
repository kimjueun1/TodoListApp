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
import java.util.HashSet;

public class TodoUtil {

	public static void createItem(TodoList list) {

		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("\n"
				+ "---------�׸� �߰�---------\n"
				+ "ī�װ� �Է� > ");
		
		category = sc.next();
		sc.nextLine();
		
		System.out.print("���� �Է� > ");

		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("���� �ߺ�!");
			return;
		}


		System.out.print("���� �Է� > ");
		desc = sc.nextLine().trim();
		
		System.out.print("�����ð� �Է� >");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println("�߰� �Ϸ�. ");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);


		System.out.print("\n"
				+ "---------�׸� ����---------\n"
				+ "������ ��ȣ�� �Է� > ");

		int number = sc.nextInt();
		
		int count = 0;

		for (TodoItem item : l.getList()) {
			count++;
			if (number == count) {
				System.out.println(count+"."+item.toString());
				System.out.print("�� �׸��� �����Ͻðڽ��ϱ�? y/n ");
				char user = sc.next().charAt(0);
				if(user == 'y') {
					l.deleteItem(item);
					System.out.println("���� �Ϸ�. ");
				}
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("\n"
				+ "---------�׸� ����---------\n"
				+ "������ �׸� ��ȣ > ");
		
		int count=0;
		
		for (TodoItem item : l.getList()) {
			count++;
		}
		
		int number = sc.nextInt();
		if(number < 0 || number > count) {
			System.out.println("��ȣ�� �������� ����.");
			return;
		}
		
		System.out.print("���ο� ī�װ� >");
		String new_category = sc.next().trim();
		sc.nextLine();


		System.out.print("���ο� ���� > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("���� �ߺ�!");
			return;
		}

		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("���ο� �����ð� >");
		String new_due_date = sc.next().trim();
		
		int count2=0;
		
		for (TodoItem item : l.getList()) {
			count2++;
			if(number == count2) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.print("���� �Ϸ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("---------LIST---------");
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
		} 
		System.out.println("��ü��� - �� "+count+"��");
		int count2=0;
		for (TodoItem item : l.getList()) {
			count2++;
			System.out.println(count2+"."+item.toString());
		} 
	}
	
	public static void ls_cate(TodoList l) {
		HashSet<String> set = new HashSet<String>();
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
		}
		
		Iterator iter = set.iterator();	
		while(iter.hasNext()) {
		    System.out.print(iter.next());
		    if(iter.hasNext()) System.out.print(" / ");
		}
		System.out.println();
		System.out.println("�� "+set.size()+"���� ī�װ� ����");
	}

	public static void loadList(TodoList l, String filename ) {


		try {
			int count=0;
			
			BufferedReader br  = new BufferedReader(new FileReader(filename));
			String oneline;

			while((oneline = br.readLine()) != null) {
				//System.out.println(oneline);
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String content = st.nextToken();
				String due_date = st.nextToken();
				String date = st.nextToken();
				TodoItem item = new TodoItem(title, content, category, due_date);
				item.setCurrent_date(date);
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
	
	public static void find(TodoList l, String word) {
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
			if (item.getTitle().contains(word) || item.getDesc().contains(word)) {
				System.out.println(count+"."+item.toString());
			
			}
		}
	}
	
	public static void find_cate(TodoList l, String cate) {
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
			if (item.getCategory().contains(cate)) {
				System.out.println(count+"."+item.toString());
			
			}
		}
	}
	
}
