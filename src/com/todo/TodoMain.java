package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);	
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				isList = true;
				break;
			
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("�̸��� ���� �Ϸ�.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("���̸��� ���� �Ϸ�.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("��¥�� ���� �Ϸ�.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("����¥�� ���� �Ϸ�.");
				isList = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String word = sc.next();
				TodoUtil.find(l, word);
				break;
			
			case "find_cate":
				String cate = sc.next();
				TodoUtil.find_cate(l, cate);
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("��Ȯ�� �޴����� �Է��ϼ���. ����-help");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
