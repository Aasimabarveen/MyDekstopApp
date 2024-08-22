package com.StaffSubstitution.DAO;

import java.sql.SQLException;
import java.util.*;
import com.StaffSubstitution.Model.*;
import com.StaffSubstitution.util.HibernateUtil;
import org.hibernate.*;

public class TimeTableDAO {
  private Session session;

  public TimeTableDAO() {
    session = HibernateUtil.getSessionFactory().openSession();
  }

  public List<TimeTable> getAllTimeTable() throws SQLException {
    List<TimeTable> timeTableList = null;
    try {
      Query<TimeTable> query = session.createQuery("from TimeTable", TimeTable.class);
      timeTableList = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return timeTableList;
  }

  public List<TimeTable> getTimeTableByStaffId(Integer staffId) throws SQLException {
    List<TimeTable> timeTableList = null;
    try {
      Query<TimeTable> query = session.createQuery("FROM TimeTable WHERE staff.staffId = :staffId", TimeTable.class);
      query.setParameter("staffId", staffId);
      timeTableList = query.list();
    } catch (Exception e) {
      System.err.println("Error ");
      e.printStackTrace();
    }
    return timeTableList;
  }

  public List<TimeTable> getTimeTablesForAbsentStaff(List<Integer> absentStaffIds, String currentDay)
      throws SQLException {
    String hql = "FROM TimeTable WHERE staff.staffId IN :absentStaffIds AND LOWER(weekDay) = LOWER(:currentDay)";
    Query query = session.createQuery(hql);
    query.setParameterList("absentStaffIds", absentStaffIds);
    query.setParameter("currentDay", currentDay);
    return query.list();
  }

  public Set<Staff> findAvailableStaffForPeriod(String periodKey, String day) {
    Set<Staff> availableStaff = new HashSet<>();

    try {
      String hql = "SELECT t.staff FROM TimeTable t WHERE LOWER(t.weekDay) = LOWER(:currentDay) AND " + "t.period"
          + periodKey + " = '-'"; // Dynamic
      // period
      // column
      Query query = session.createQuery(hql);
      query.setParameter("currentDay", day);
      availableStaff = new HashSet<>(query.list());
      System.out.println("******" + availableStaff);
    } catch (Exception e) {
      System.err.println("Error finding available staff for period " + periodKey + ": ");
      e.printStackTrace();
    }
    return availableStaff;
  }

}