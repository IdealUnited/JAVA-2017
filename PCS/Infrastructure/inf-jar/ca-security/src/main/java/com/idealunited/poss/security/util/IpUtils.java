/**
 * 
 */
package com.idealunited.poss.security.util;

/**
 * @author 戴德荣
 * @date 2011-1-14 
 *
 */
public class IpUtils {

	public static  boolean  validateIp(String ip){
		String[] ipSeg = ip.split("\\.");
		if (ipSeg.length != 4) {
			return false;
		}
		for(int i=3; i>=0;i--){
			  if (Integer.parseInt( ipSeg[i]) > 256){
				  return false;
			  }
		}
		return true;
	}
	
	public static long ipLongValue(String ip){
		String[] ipSeg = ip.split("\\.");
		long v = 0;
		for(int i=0; i<=3;i++){
			switch (i) {
			case 0:
				v+= 256*256*256*Long.parseLong(ipSeg[i]);break;
			case 1:
				v+= 256*256*Long.parseLong(ipSeg[i]);break;	
			case 2:
				v+= 256*Long.parseLong(ipSeg[i]);break;
			case 3:
				v+= Long.parseLong(ipSeg[i]);break;	
			}
			
		}
		return v;
	}
	
	
	/**
	 * 判断 是否是内网ip
	 *     tcp/ip协议中，专门保留了三个IP地址区域作为私有地址，其地址范围如下：
		10.0.0.0/8：10.0.0.0～10.255.255.255
　　		172.16.0.0/12：172.16.0.0～172.31.255.255
　　		192.168.0.0/16：192.168.0.0～192.168.255.255
	 * @param ip
	 * @return true/false
	 */
	public static boolean isLocalAreaIp(String ip){
		//判断本机
		if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
			return true;
		}
		else {	//判断 内网
			long ipLong  = ipLongValue(ip);
			if(ipLongValue("10.0.0.0")<=ipLong && ipLongValue("10.255.255.255")>=ipLong)
				return true;
			if(ipLongValue("172.16.0.0")<=ipLong && ipLongValue("172.31.255.255")>=ipLong)
				return true;
			if(ipLongValue("192.168.0.0")<=ipLong && ipLongValue("192.168.255.255")>=ipLong)
				return true;
		}
		
		return false;
	}
	
	
}
