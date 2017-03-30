package com.idealunited.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class FormatNumber {

	private final static BigDecimal dividend = new BigDecimal(1);
	
    public static double getDecimal(double number) {
        double b = 0L;
        b = Double.valueOf(new java.text.DecimalFormat("###.00").format(number));
        return b;
    }
    
    public static double get2Decimal(Double number) {
        double b = 0L;
        java.text.DecimalFormat sdf=new DecimalFormat("#.00");
        
        String str=number.toString();
        String numberStr=sdf.format(str);
        if(numberStr.indexOf(".")>-1){
            //numberStr
        }
        return b;
    }
    
    public static double round(double value){

        return Math.round( value * 100 ) / 100.0;

     }
    
    
    public static String doubleFormat(double d) {
    	DecimalFormat df = new DecimalFormat("###.00");   
    	return df.format(d);

        }    




    /**
     * 保留小数点后N位（不足则补零）
     * @param str
     * @param num 保留的位数
     * @param div 是否四舍五入 
     * @return
     */
    public static String RoundOf(String str, int num,boolean div) {
        if (str.indexOf(".") > -1) {
            String str1 = str.substring(str.indexOf(".") + 1, str.indexOf(".") + 1 + str.length()
                    - (str.indexOf(".") + 1));
      
            int i = 0;
            if (num < 0)
                num = 0; // 如果指定要保留的小数位小于零,则初始化为零
            if (str1.length() > num) {
                if (Integer.valueOf(str1.substring(num, num + 1)) > 4) {
                    int val = 0;
                    String val2="";
                    
                   if(div){
                    val = Integer.valueOf(str1.substring(0, num + 1))
                            + (10 - Integer.valueOf(str1.substring(num, num + 1)));
                    val2=String.valueOf(val);
                   }else{
                       val2=str1.substring(0, num + 1);
                   }
                  
                    if (val2.length() > (num + 1)) {
                        i = 1;
                        str1 = val2.substring(1, num + 1);
                    } else {
                        str1 = val2.substring(0, num);
                    }
                } else {
                    str1 = str1.substring(0, num);
                }
               
                if (str.indexOf(".") > 0) {
                    String str2 = str.substring(0, str.indexOf("."));
                    if (num > 0) {
                        str = String.valueOf(Integer.valueOf(str2) + i) + "." + str1;
                    } else {
                        str = String.valueOf(Integer.valueOf(str2) + i);
                    }
                } else {
                    if (num > 0) {
                        str = String.valueOf(i) + "." + str1;
                    } else {
                        str = String.valueOf(i);
                    }
                }
            } else if (str1.length() < num) {
                for (int n = 0; n < (num - str1.length()); n++) {
                    str = str + "0";
                }
            }
        }else{
            String b="0";
            str+=".";
            for(int i=0;i<num;i++){
                str+=b;
            }
           
        }
        return str;
    }
    
    public static BigDecimal decimalsRound(BigDecimal blance,int d){
        BigDecimal p=blance.divide(dividend, d,BigDecimal.ROUND_HALF_UP);
        return p;
   }

    public static void main(String[] args) {
      //  System.out.println(FormatNumber.round(999.299));
        //System.out.println(FormatNumber.doubleFormat(100000));
        //System.out.println(FormatNumber.RoundOf(String.valueOf(100000),2,true));
    	
    }
}
