package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("---------------�޴�---------------");
        System.out.println("[add] �׸� �߰�");
        System.out.println("[del] �׸� ����");
        System.out.println("[edit] �׸� ����");
        System.out.println("[ls] ��ü ����Ʈ");
        System.out.println("[ls_cate] ī�װ� ����Ʈ");
        System.out.println("[ls_name_asc] ����Ʈ ����� ����");
        System.out.println("[ls_name_desc] ����Ʈ ������� ����");
        System.out.println("[ls_date] ����Ʈ ��¥�� ����");
        System.out.println("[ls_date_desc] ����Ʈ ����¥�� ����");
        System.out.println("[help] ���� - �޴� ���");
        System.out.println("[find] ����&���� �˻�");
        System.out.println("[find_cate] ī�װ� �˻�");
        System.out.println("[exit] ����");
    }
    public static void prompt() {
    	System.out.print("\n�޴��Է� > ");
    }
}
