package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList ���� ��ɾ� ����>");
        System.out.println("1. ����Ʈ �׸� �߰� (add)");
        System.out.println("2. ����Ʈ �׸� ���� (del)");
        System.out.println("3. ����Ʈ �׸� ���� (edit)");
        System.out.println("4. ����Ʈ ��ü ��� (ls)");
        System.out.println("5. ����Ʈ ����� ���� (ls_name_asc)");
        System.out.println("6. ����Ʈ ���񿪼� ���� (ls_name_desc)");
        System.out.println("7. ����Ʈ ������ ���� (ls_date)");
        System.out.println("8. ����Ʈ �������� ���� (ls_date_desc)");
        System.out.println("9. ī�װ� ��� (ls_cate)");
        System.out.println("10. ����, ���� �˻� (find)");
        System.out.println("11. ī�װ� �˻� (find_cate)");
        System.out.println("12. �۾� �Ϸ��Ű�� (comp)");
        System.out.println("13. �Ϸ�� �۾� ����Ʈ(ls_comp)");
        System.out.println("14. ToDoList ���� ��ɾ� ���� (help)");
        System.out.println("15. ���α׷� ���� (exit)");
    }
    
    public static void prompt()
    {
    	System.out.println();
    	System.out.print("��ɾ� �Է� >");
    }
}
