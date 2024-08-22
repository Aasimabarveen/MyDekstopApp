package com.StaffSubstitution.Model;

import javax.persistence.*;

@Entity
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  int staffId;
  @Column(name = "name", unique = true)
  String name;

  public int getStaffId() {
    return staffId;
  }

  public void setStaffId(int staffId) {
    this.staffId = staffId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "Staff{" + "staffId=" + staffId + ", name='" + name + '\'' + '}';
  }

}