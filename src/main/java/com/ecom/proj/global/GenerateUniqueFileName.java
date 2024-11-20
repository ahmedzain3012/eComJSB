package com.ecom.proj.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateUniqueFileName {
    public static String generateUniqueFileName() 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        return "prod_" + timestamp+"_";
    }
}
