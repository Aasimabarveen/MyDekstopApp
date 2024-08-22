package com.StaffSubstitution.DAO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.StaffSubstitution.Model.Staff;
import com.StaffSubstitution.util.HibernateUtil;
import org.hibernate.*;

public class StaffDAO {
  public List<Staff> getAllStaff() throws SQLException {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Staff> staffList = null;
    try {
      Query<Staff> query = session.createQuery("from Staff", Staff.class);
      staffList = query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }

    return staffList;
  }

}