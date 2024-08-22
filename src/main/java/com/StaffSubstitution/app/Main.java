package com.StaffSubstitution.app;

//https://allaroundjava.com/setting-up-java-application-with-hibernate-example/
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import com.StaffSubstitution.Service.StaffService;
import com.StaffSubstitution.Service.TimeTableService;
import com.StaffSubstitution.Service.loadExcelData;
import com.StaffSubstitution.util.*;
import org.hibernate.SessionFactory;
import java.util.List;
import com.StaffSubstitution.Model.*;
import java.util.concurrent.*;

public class Main {
    static HashMap<Integer, ArrayList<String>> actingStaffList = new HashMap<Integer, ArrayList<String>>();

    public static void main(String[] args) {
        ConcurrentHashMap<String, Set<Staff>> availableStaffMap = SubstitutionStaffMapUtil.getSubstitueStaffMap();

        try {
            String fileName = "src/main/resources/SAMPLE.xlsx";
            SessionFactory sess = HibernateUtil.getSessionFactory();
            loadExcelData loadService = new loadExcelData();
            StaffService staffService = new StaffService();
            TimeTableService timeTableService = new TimeTableService();
            // loadService.readAndLoadData(fileName);
            List<Staff> staffList = staffService.getAllStaffs();
            // timeTableService.getAllTimeTable();
            for (Staff staff : staffList)
                System.out.println(staff.getStaffId() + "\t" + staff.getName() + "\n_________________________");
            List<Integer> absentStaffIds = staffService.getAbsentStaff();
            for (Integer staffID : absentStaffIds)
                System.out.println(staffID + "\n_________________________");
            List<TimeTable> absentstaffTimeTableList = timeTableService.getAbsentStaffTimeTable(absentStaffIds);
            for (TimeTable timeTable : absentstaffTimeTableList)
                System.out.println(timeTable.toString() + "\n");
            timeTableService.processStaffTimetables(absentstaffTimeTableList);
            System.out.println("The periods need staff subtitution" + SubstitutionStaffMapUtil.getSubstitueStaffMap());
            timeTableService.findSubstituteStaffForPeriods();
            System.out
                    .println("The Available Substitute Staff are: " + SubstitutionStaffMapUtil.getSubstitueStaffMap());
            sess.close();

        } catch (Exception e) {
            System.err.println("Error retrieving staff data: ");
            e.printStackTrace();
        }

    }
}
