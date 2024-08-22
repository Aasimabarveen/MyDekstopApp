package com.StaffSubstitution.Service;

import java.sql.*;
import com.StaffSubstitution.DAO.*;
import java.util.List;
import java.util.*;
import com.StaffSubstitution.Model.*;

public class StaffService {
  public List<Staff> getAllStaffs() {
    try {
      StaffDAO staffDao = new StaffDAO();
      List<Staff> staffList = staffDao.getAllStaff();
      System.out.println("NO OF ENTRIES: " + staffList.size());
      System.out.println(staffList.toString());
      return staffList;
    } catch (SQLException ex) {
      System.out.println("Error while reading data from DB \n");
      ex.printStackTrace();
      return null;
    }
  }

  public List<Integer> getAbsentStaff() {
    System.out.println("Please Enter absent staff list as ids given above\n enter `done` when complete");
    Scanner scanner = new Scanner(System.in);
    List<Integer> absentStaffList = new ArrayList<>();
    while (true) {
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("done"))
        break;
      try {
        int id = Integer.parseInt(input);
        absentStaffList.add(id);
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer ID.");
      }
    }
    for (int numbr : absentStaffList)
      System.out.println(numbr + "\n");
    scanner.close();
    return absentStaffList;
  }

}