package com.todo.dao;

import java.sql.*;
import java.util.*;

import com.todo.service.*;

public class TodoList {
	private Connection con;
	private String sql;
	int cnt;

	public TodoList() {
		this.con = DBConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		sql = "insert into list (title, memo, category, current_date, due_date)"
				+ "values (?,?,?,?,?);";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCate());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue());
			cnt = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	public int deleteItem(int id) {
		sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			cnt = pstmt.executeUpdate();
			pstmt.close();		
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return cnt;
	}

	public int editItem(TodoItem t) {
		sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=? where id=?;";
		PreparedStatement pstmt;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCate());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue());
			pstmt.setInt(6,t.getId());
			cnt = pstmt.executeUpdate();
			pstmt.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<TodoItem> getList(String find) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		find = "%"+find+"%";
		sql = "Select * from list where title like ? or memo like ?;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, find);
			pstmt.setString(2, find);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String cate = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due = rs.getString("due_date");
				String current = rs.getString("current_date");
				int is_comp = rs.getInt("is_completed");
				TodoItem t = new TodoItem(cate,title,desc,due,is_comp);
				t.setId(id);
				t.setCurrent_date(current);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stat;
		try {
			stat = con.createStatement();
			sql = "Select * from list";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String cate = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due = rs.getString("due_date");
				String current = rs.getString("current_date");
				int is_comp = rs.getInt("is_completed");
				TodoItem t = new TodoItem(cate,title,desc,due,is_comp);
				t.setId(id);
				t.setCurrent_date(current);
				list.add(t);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount() {
		Statement stat;
		try {
			stat = con.createStatement();
			sql = "select count(id) from list;";
			ResultSet rs = stat.executeQuery(sql);
			rs.next();
			cnt = rs.getInt("count(id)");
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stat;
		try {
			stat = con.createStatement();
			sql = "select ditinct category from list;";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				String cate = rs.getString("category");
				list.add(cate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategories(String find){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		sql = "Select * from list where category = ?;";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, find);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String cate = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due = rs.getString("due_date");
				String current = rs.getString("current_date");
				int is_comp = rs.getInt("is_completed");
				TodoItem t = new TodoItem(cate,title,desc,due,is_comp);
				t.setId(id);
				t.setCurrent_date(current);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stat;
		try {
			stat = con.createStatement();
			sql = "Select * from list order by "+ orderby;
			if(ordering==0)	sql = sql+" desc;";
			else sql = sql+";";
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String cate = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due = rs.getString("due_date");
				String current = rs.getString("current_date");
				int is_comp = rs.getInt("is_completed");
				TodoItem t = new TodoItem(cate,title,desc,due,is_comp);
				t.setId(id);
				t.setCurrent_date(current);
				list.add(t);
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int complete(TodoList l, int id) {
		sql = "update list set is_complement = 1 where id=" + id + ";";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			cnt = pstmt.executeUpdate();
			pstmt.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public ArrayList<TodoItem> getListComp(){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		sql = "Select * from list where is_completed = 1;";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String cate = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("memo");
				String due = rs.getString("due_date");
				String current = rs.getString("current_date");
				int comp = rs.getInt("is_completed");
				TodoItem t = new TodoItem(cate,title,desc,due,comp);
				t.setId(id);
				t.setCurrent_date(current);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean isDuplicate(String title) {
		sql = "Select * from list where title like ?;";
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				stat.close();
				return true;
			}
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
