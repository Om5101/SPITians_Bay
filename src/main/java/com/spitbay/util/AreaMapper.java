package com.spitbay.util;

import java.util.HashMap;
import java.util.Map;

public class AreaMapper {
    private static final Map<Integer, String> areaMap = new HashMap<>();
    
    static {
        areaMap.put(1, "JP Road");
        areaMap.put(2, "Malad");
        areaMap.put(3, "Versova");
        areaMap.put(4, "DN Nagar");
    }
    
    public static String getAreaName(int areaNumber) {
        return areaMap.getOrDefault(areaNumber, "Area " + areaNumber);
    }
    
    public static int getAreaNumber(String areaName) {
        for (Map.Entry<Integer, String> entry : areaMap.entrySet()) {
            if (entry.getValue().equals(areaName)) {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    public static Map<Integer, String> getAllAreas() {
        return new HashMap<>(areaMap);
    }
} 