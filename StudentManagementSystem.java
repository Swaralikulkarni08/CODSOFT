package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class StudentManagementSystem {
	PreparedStatement pst=null;
	ResultSet rs=null;
	int res=0,roll;
	static int ch;
	static Scanner sc = new Scanner(System.in);
	Student s = new Student();
	static Connection con=null;
	public static void main(String[] args) {
	
		StudentManagementSystem sms = new StudentManagementSystem();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management_system","root","root");
			if(con!=null) {
				System.out.println("Connection created successfully!");
			}else {
				System.out.println("Connection not created successfully!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		do {
			System.out.println("1. Add a Student\n2. Remove a Student\n3. Search for a Student\n4. Display a Student\n5. Update a Student\n6. Exit");
			System.out.println("Enter your choice :");
			ch=sc.nextInt();
			switch(ch) {
				case 1: sms.addStudent();
						break;
				case 2: sms.removeStudent();
						break;
				case 3: sms.searchStudent();
						break;
				case 4: sms.displayStudent();
						break;
				case 5: sms.updateStudent();
						break;
				case 6: System.out.println("Exiting....");
					    System.exit(0);
				default: System.out.println("Enter valid choice!");
			}
		}while(ch!=6);
	}

	private void updateStudent() {
		// TODO Auto-generated method stub
		System.out.println("Enter roll no for updating details of a Student: ");
		roll=sc.nextInt();
		System.out.println("Enter name of student to be updated: ");
		s.name=sc.next();
		System.out.println("Enter grade of student to be updated: ");
		s.grade=sc.next().charAt(0);
		try {
			pst=con.prepareStatement("update addstudent set name=?, grade=? where rollno=?");
			pst.setString(1, s.name);
			pst.setObject(2, s.grade, java.sql.Types.CHAR);
	        pst.setInt(3, roll);
			res=pst.executeUpdate();
			if(res>0) {
			System.out.println("Record updated successfully");
			}else {
				System.out.println("Record not updated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void displayStudent() {
		// TODO Auto-generated method stub
		try {
			pst=con.prepareStatement("select * from addstudent");
			rs=pst.executeQuery();
			System.out.println("Name\tRoll No\tGrade");
			while(rs.next()) {
				System.out.println(rs.getString("name")+"\t"+rs.getInt(2)+"\t"+rs.getString("grade"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchStudent() {
		// TODO Auto-generated method stub
		System.out.println("Enter roll no of student to be searched:");
		roll=sc.nextInt();
		try {
			pst=con.prepareStatement("select name,grade from addstudent where rollno=?");
			pst.setInt(1, roll);
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println("Name\tGrade");
				System.out.println(rs.getString("name")+"\t"+rs.getString("grade"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void removeStudent() {
		
		// TODO Auto-generated method stub
		System.out.println("Enter roll no of student to be deleted :");
		roll=sc.nextInt();
		try {
			pst=con.prepareStatement("delete from addstudent where rollno=?");
			pst.setInt(1, roll);
			res=pst.executeUpdate();
			if(res>0) {
				System.out.println("Record deleted successfully!");
			}else {
				System.out.println("Record not found!");
				}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStudent() {
		int num;
		// TODO Auto-generated method stub
		System.out.println("How many students you want to add: ");
		num=sc.nextInt();
		try {
		 pst=con.prepareStatement("insert into addstudent values(?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=1;i<=num;i++) {
			System.out.println("Enter name of Student: ");
			s.name=sc.next();
			System.out.println("Enter roll no of Student: ");
			s.rollNo=sc.nextInt();
			System.out.println("Enter grade of Student: ");
			s.grade=sc.next().charAt(0);
			
			try {
				pst.setString(1,s.name);
				pst.setInt(2,s.rollNo);
				pst.setObject(3, s.grade, java.sql.Types.CHAR);
				res=pst.executeUpdate();
			}catch(SQLIntegrityConstraintViolationException e) {
				System.out.println("Roll No cannot be same!");
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			

			if(res>0)
			{
			System.out.println("Record is inserted");
			}
			else {
				System.out.println("Record not inserted");
			}
		}
	}
}