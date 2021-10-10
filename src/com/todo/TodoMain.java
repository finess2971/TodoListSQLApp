package com.todo;

import java.sql.*;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
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
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				System.out.println("��������� �����Ͽ����ϴ�");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("���񿪼����� �����Ͽ����ϴ�");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("���������� �����Ͽ����ϴ�");
				TodoUtil.listAll(l, "title", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("������������ �����Ͽ����ϴ�");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
				
			case "find" :
				String find = sc.nextLine();
				TodoUtil.find(l, find.trim());
				break;
				
			case "find_cate":
				String find_cate = sc.nextLine();
				TodoUtil.find_cate(l, find_cate.trim());
				break;
				
			case "comp":
				int id = sc.nextInt();
				TodoUtil.complete(l, id);
				break;
				
			case "ls_comp":
				TodoUtil.ls_comp(l);
				break;

			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;
				
			default:
				System.out.println("��Ȯ�� ��ɾ �Է��Ͻʽÿ� - ���� ��ɾ� ���� ( help )");
				break;
			}
			
		} while (!quit);
	}
}
