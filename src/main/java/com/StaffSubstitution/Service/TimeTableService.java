package com.StaffSubstitution.Service;

import java.sql.*;
import com.StaffSubstitution.DAO.*;
import java.util.*;
import java.time.*;
import com.StaffSubstitution.Model.*;
import com.StaffSubstitution.util.*;
import java.util.concurrent.*;

public class TimeTableService {
  public List<TimeTable> getAllTimeTable() {
    try {
      TimeTableDAO timeTableDAO = new TimeTableDAO();
      List<TimeTable> timeTableList = timeTableDAO.getAllTimeTable();
      System.out.println("NO OF ENTRIES: " + timeTableList.size());
      System.out.println(timeTableList.toString());
      return timeTableList;
    } catch (SQLException ex) {
      System.out.println("Error while reading data from DB \n");
      ex.printStackTrace();
      return null;
    }
  }

  public List<TimeTable> getAbsentStaffTimeTable(List<Integer> absentStaff) {
    try {
      TimeTableDAO timeTableDAO = new TimeTableDAO();
      return timeTableDAO.getTimeTablesForAbsentStaff(absentStaff, getCurrentDay());
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public String getCurrentDay() {
    // Logic to get the current day of the week
    LocalDate today = LocalDate.now();
    return today.getDayOfWeek().toString().toLowerCase();
  }

  public void processStaffTimetables(List<TimeTable> absentStaffTimetables) {
    for (TimeTable timetable : absentStaffTimetables) {
      System.out.println("absent TT: " + timetable.toString());
      processTimetable(timetable);
    }
  }

  private void processTimetable(TimeTable timetable) {
    String[] periods = { timetable.getPeriod1(), timetable.getPeriod2(), timetable.getPeriod3(),
        timetable.getPeriod4(), timetable.getPeriod5(), timetable.getPeriod6(),
        timetable.getPeriod7(), timetable.getPeriod8() };

    for (int i = 0; i < periods.length; i++) {
      System.out.println(periods[i]);
      if (!periods[i].equalsIgnoreCase("-")) { // Check if the period has a class assigned
        String periodKey = String.valueOf(i + 1); // Extract last digit (period number)
        SubstitutionStaffMapUtil.addPeriodKey(periodKey);
      }
    }
  }

  public void findSubstituteStaffForPeriods() {
    TimeTableDAO timeTableDAO = new TimeTableDAO();
    ConcurrentHashMap<String, Set<Staff>> availableStaffMap = SubstitutionStaffMapUtil.getSubstitueStaffMap();
    String currentDay = getCurrentDay(); // Assuming this method returns the current day as a string

    availableStaffMap.forEach((periodKey, staffList) -> {
      Set<Staff> substituteStaffMap = availableStaffMap.get(periodKey);
      Set<Staff> substituteStaffList = timeTableDAO.findAvailableStaffForPeriod(periodKey, currentDay);
      substituteStaffMap.addAll(substituteStaffList);
    });
  }

}