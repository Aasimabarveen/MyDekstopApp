package com.StaffSubstitution.util;

import java.util.*;
import java.util.concurrent.*;

import com.StaffSubstitution.Model.*;

public class SubstitutionStaffMapUtil {

    // Static ConcurrentHashMap
    private static final ConcurrentHashMap<String, Set<Staff>> substitueStaffMap = new ConcurrentHashMap<>();

    // Private constructor to prevent instantiation
    private SubstitutionStaffMapUtil() {
    }

    // Method to get the map
    public static ConcurrentHashMap<String, Set<Staff>> getSubstitueStaffMap() {
        return substitueStaffMap;
    }

    public static void addPeriodKey(String periodKey) {
        substitueStaffMap.putIfAbsent(periodKey, new HashSet<>());
    }
}