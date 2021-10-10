package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		String category, due_date;
		int is_comp;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� �߰�]\n"
				+ "ī�װ� > ");
		
		category = sc.next().trim();
		
		System.out.print("���� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� ������ ����� �� �����ϴ�");
			return;
		}
		
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine();
		
		System.out.print("D-Day�� �Է��Ͻʽÿ� > ");
		due_date = sc.nextLine();
		
		System.out.print("�Ϸ�� �׸��̶�� 1�� �ƴ϶�� 0�� �Է��Ͻʽÿ� > ");
		is_comp = sc.nextInt();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, is_comp);
		if(list.addItem(t)>0) {
			System.out.println("�߰��Ǿ����ϴ�");
		}
	}

	public static void deleteItem(TodoList l) {
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻʽÿ� > ");		
		
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		if(l.deleteItem(id)>0) {
			System.out.println("������ �Ϸ�Ǿ����ϴ�");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻʽÿ� > ");
		
		int id = sc.nextInt();
		
		System.out.print("�� ī�װ� > ");
		String new_category = sc.next().trim();
		
		System.out.print("�� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.printf("�̹� �ִ� ������ ����� �� �����ϴ�");
			return;
		}
		
		System.out.print("�� ���� > ");
		sc.nextLine();
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� D-Day > ");
		String new_due_date = sc.nextLine().trim();
		
		System.out.print("�Ϸ�� �׸��̶�� 1�� �ƴ϶�� 0�� �Է��Ͻʽÿ� > ");
		int is_comp = sc.nextInt();
		
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date, is_comp);
		t.setId(id);
		if(l.editItem(t)>0){
			System.out.println("�׸��� �����Ǿ����ϴ�");
		}
		
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ��� %d��]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void find(TodoList l, String find) {
		int cnt = 0;
		for(TodoItem t : l.getList(find)) {
			System.out.println(t.toString());
			cnt++;
		}
		if(cnt==0) System.out.println("��ġ�ϴ� �׸��� �����ϴ�");
		else System.out.println("\n�� "+cnt+"���� �׸��� ã�ҽ��ϴ�");
	}
	
	public static void find_cate(TodoList l, String find) {
		int cnt=0;
		for(TodoItem t : l.getListCategories(find)) {
			System.out.println(t.toString());
			cnt++;
		}
		
		if(cnt==0) System.out.println("��ġ�ϴ� �׸��� �����ϴ�");
		else System.out.println("\n�� "+cnt+"���� �׸��� ã�ҽ��ϴ�");
	}
	
	public static void ls_cate(TodoList l) {
		int cnt = 0;
		
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			cnt++;
		}

		System.out.println("\n�� "+cnt+"���� ī�װ��� ��ϵǾ� �ֽ��ϴ�");
	}
	
	public static void complete(TodoList l, int id) {
		if(l.complete(l, id)>0) {
			System.out.println("�Ϸ�Ǿ����ϴ�");
		}
	}
	
	public static void ls_comp(TodoList l) {
		int cnt = 0;
		
		for(TodoItem t : l.getListComp()) {
			System.out.println(t.toString());
			cnt++;
		}
		if(cnt==0) System.out.println("�Ϸ�� �׸��� �����ϴ�");
		else System.out.println("\n�� "+cnt+"���� �׸��� �Ϸ�Ǿ����ϴ�");
	}
}
