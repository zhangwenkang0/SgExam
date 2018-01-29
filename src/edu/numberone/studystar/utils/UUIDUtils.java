package edu.numberone.studystar.utils;

import java.util.UUID;

public class UUIDUtils {
	 public static String getUUId() {
         String uuid = UUID.randomUUID().toString();
       
         return uuid.replace("-", "").substring(0,16);
     }
}
