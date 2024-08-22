package com.StaffSubstitution.Model;

import javax.persistence.*;

@Entity
@Table(name = "TimeTable")
public class TimeTable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int Id;
  String weekDay;
  String period1;
  String period2;
  String period3;
  String period4;
  String period5;
  String period6;
  String period7;
  String period8;
  @ManyToOne
  @JoinColumn(name = "staffId")
  Staff staff;

  public Staff getStaff() {
    return staff;
  }

  public void setStaff(Staff staff) {
    this.staff = staff;
  }

  public int getId() {
    return Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  public String getweekDay() {
    return weekDay;
  }

  public void setweekDay(String weekDay) {
    this.weekDay = weekDay;
  }

  public String getPeriod1() {
    return period1;
  }

  public void setPeriod1(String period1) {
    this.period1 = period1;
  }

  public String getPeriod2() {
    return period2;
  }

  public void setPeriod2(String period2) {
    this.period2 = period2;
  }

  public String getPeriod3() {
    return period3;
  }

  public void setPeriod3(String period3) {
    this.period3 = period3;
  }

  public String getPeriod4() {
    return period4;
  }

  public void setPeriod4(String period4) {
    this.period4 = period4;
  }

  public String getPeriod5() {
    return period5;
  }

  public void setPeriod5(String period5) {
    this.period5 = period5;
  }

  public String getPeriod6() {
    return period6;
  }

  public void setPeriod6(String period6) {
    this.period6 = period6;
  }

  public String getPeriod7() {
    return period7;
  }

  public void setPeriod7(String period7) {
    this.period7 = period7;
  }

  public String getPeriod8() {
    return period8;
  }

  public void setPeriod8(String period8) {
    this.period8 = period8;
  }

  public String toString() {
    return String.format(
        "TimeTable{Id=%d, weekDay='%s', period1='%s', period2='%s', period3='%s', period4='%s', period5='%s', period6='%s', period7='%s', period8='%s', staff=%s}",
        Id, weekDay, period1, period2, period3, period4, period5, period6, period7, period8, staff);
  }
}