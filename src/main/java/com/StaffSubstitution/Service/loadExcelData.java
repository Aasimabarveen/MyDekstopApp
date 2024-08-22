package com.StaffSubstitution.Service;

import org.apache.poi.ss.usermodel.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import com.StaffSubstitution.util.HibernateUtil;
import com.StaffSubstitution.Model.*;

import org.hibernate.*;

public class loadExcelData {

  public void readAndLoadData(String filename) {
    Integer i = 0;
    try (FileInputStream fis = new FileInputStream(filename);

        Workbook workbook = new XSSFWorkbook(fis)) {

      Session session = HibernateUtil.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();

      try {
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
          // Skip header row
          if (row.getRowNum() == 0) {
            continue;
          }
          if (row.getCell(1) == null || row.getCell(1).getCellType() == CellType.BLANK)
            break;
          String staffName = row.getCell(0).getStringCellValue();
          String day = row.getCell(1).getStringCellValue();
          String period1 = row.getCell(2).getStringCellValue();
          String period2 = row.getCell(3).getStringCellValue();
          String period3 = row.getCell(4).getStringCellValue();
          String period4 = row.getCell(5).getStringCellValue();
          String period5 = row.getCell(6).getStringCellValue();
          String period6 = row.getCell(7).getStringCellValue();
          String period7 = row.getCell(8).getStringCellValue();
          String period8 = row.getCell(9).getStringCellValue();
          // Save data to the database
          saveDataToDatabase(session, staffName, day, period1, period2, period3, period4, period5, period6,
              period7,
              period8);
        }
        transaction.commit();
      } catch (Exception e) {
        if (transaction != null && transaction.getStatus().canRollback()) {
          System.out.println("Rolling back transaction due to exception...");
          transaction.rollback();
        }

        e.printStackTrace();
      } finally {
        session.close();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Staff checkStaffExist(Session session, String staffName) {
    // Check if staff member with the same name already exists
    Query<Staff> query = session.createQuery("SELECT s FROM Staff s WHERE s.name = :name", Staff.class);
    query.setParameter("name", staffName);
    Staff existingStaff = query.uniqueResult();

    if (existingStaff != null) {
      return existingStaff;
    }
    return null;

  }

  private void saveDataToDatabase(Session session, String staffName, String day, String period1, String period2,
      String period3, String period4, String period5, String period6, String period7, String period8) {
    try {
      // Create or update Staff record
      Staff staff = checkStaffExist(session, staffName);
      if (staff == null) {
        staff = new Staff();
        staff.setName(staffName);
        session.saveOrUpdate(staff);
      }
      // Create or update TimeTable record
      TimeTable timeTable = new TimeTable();
      timeTable.setStaff(staff); // Ensure that staffId is set
      timeTable.setweekDay(day);
      timeTable.setPeriod1(period1);
      timeTable.setPeriod2(period2);
      timeTable.setPeriod3(period3);
      timeTable.setPeriod4(period4);
      timeTable.setPeriod5(period5);
      timeTable.setPeriod6(period6);
      timeTable.setPeriod7(period7);
      timeTable.setPeriod8(period8);
      session.saveOrUpdate(timeTable);
    } catch (Exception e) {
      e.printStackTrace();

    }
  }

}
