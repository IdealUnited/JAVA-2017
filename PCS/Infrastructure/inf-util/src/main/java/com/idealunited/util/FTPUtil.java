/**
 *  File: ContinueFTP.java
 *  Description:
 *  Copyright 2006-2013 pay Corporation. All rights reserved.
 *  Date      Author      Changes
 *  2013-5-22   liwei     Create
 *
 */
package com.idealunited.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传
 * 
 * @author lIWEI
 * @Date 2013-5-22
 * @Description
 */
public class FTPUtil {
	private static final Log log = LogFactory.getLog(FTPUtil.class);

	public static void main(String[] args) {
		try {
			// String local = "/fromftp/2011/0926/111.txt";
			String local = "/fromftp/1_92_1222.bmp";
			FileInputStream in = new FileInputStream(new File(local));
			boolean flag = FTPUtil.uploadFile("10.2.169.201", "21",
					"pay_test", "pay.com@123456",
					"/home/pay_test/liwei/upload", "b.bmp", in);
			System.out.println(flag);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接FTP出错：" + e.getMessage());
		}
	}

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url, String port, String username,
			String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, Integer.valueOf(port));// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.error("连接ftp服务器异常：：：" + url + "  " + filename);
				return success;
			}
			boolean flag = ftp.changeWorkingDirectory(path);
			if (flag) {
				ftp.setFileType(FTP.BINARY_FILE_TYPE);
				ftp.storeFile(filename, input);
				success = true;
			} else {
				log.error("ftp目录不存在：：：" + url + "  " + path + "  " + filename);
			}

			input.close();
			ftp.logout();

		}catch (ConnectException e) {
			log.error("上传失败连接FTP服务器失败：：："  + url + "  "
					+ port,e);
		}catch (IOException e) {
//			e.printStackTrace();
			log.error("上传失败：：：" + e.getMessage() + "   " + "  " + url + "  "
					+ filename);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					log.error("关闭ftp异常：：：" + ioe.getMessage() + "   " + "  "
							+ url + "  " + filename);
				}
			}
		}
		return success;
	}
	
	
	
	/** 
     *   download 
     *   从ftp下载文件到本地 
     * @throws java.lang.Exception 
     * @return  
     * @param newfilename 本地生成的文件名 
     * @param filename 服务器上的文件名 
     */ 
	public static Boolean downloadFile(String url, String port, String username,
			String password,String remotePath, String localPath, String fileName) {   
		boolean success = false;   
        FTPClient ftp = new FTPClient();   
        try {   
            int reply;   
            ftp.connect(url, Integer.parseInt(port));   
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器    
            ftp.login(username, password);//登录    
            reply = ftp.getReplyCode();   
            if (!FTPReply.isPositiveCompletion(reply)) {   
                ftp.disconnect();   
                return success;   
            }   
            boolean isSuccess=ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录    
            
            if(isSuccess==false){
            	log.error("FTP文件目录不存在：：:  " + url + "  "
    					+ remotePath);
            }else{
            	
            	FTPFile[] fs = ftp.listFiles(File.separator+remotePath);   
                for(FTPFile ff:fs){   
                    if(ff.getName().equals(fileName)){   
                        
                    	File dFile=new File(localPath);
                    	if(!dFile.exists()){
                    		dFile.mkdirs();
                    	}
                    	
                    	File localFile = new File(localPath+File.separator+ff.getName());
                        if(!localFile.exists()){
                        	OutputStream is = new FileOutputStream(localFile);    
                            ftp.retrieveFile(ff.getName(), is);   
                            is.close();  
                        }
                        success = true;
                    }   
                }   
            }
            ftp.logout();   
        }catch (ConnectException e) {
			log.error("下载文件失败连接FTP服务器失败：：："  + url + "  "
					+ port,e);
		}catch (IOException e) {  
        	log.error("下载文件失败：：：" + e.getMessage() + "   " + "  " + url + "  "
					+ fileName);
            e.printStackTrace();   
        } finally {   
            if (ftp.isConnected()) {   
                try {   
                    ftp.disconnect();   
                } catch (IOException ioe) {
                	log.error("取消连接失败：：：" + url + "  " + remotePath + "  " + fileName);
                }   
            }   
        }   
        return success; 
	} 
	
	/** 
	   * 取得某个目录下某个文件
	   * 
	   */ 
	public static List getFileList(String url, String port, String username,
			String password,String remotePath,String fileName) 
	{ 
	     List list = new ArrayList(); 
	     FTPClient ftp = new FTPClient(); 
	    try{ 
	    	ftp.connect(url, Integer.parseInt(port));   
            //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器    
            ftp.login(username, password);//登录    
            int reply = ftp.getReplyCode();   
            if (!FTPReply.isPositiveCompletion(reply)) { 
            	log.error("连接失败：：" + "  " + url);
                ftp.disconnect();
                return list;   
            }   
           ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
           FTPFile[] fs = ftp.listFiles();   
           for(FTPFile ff:fs){   
               if(ff.getName().equals(fileName)){
            	   list.add(ff);
               }
           }
           ftp.logout(); 
	    
	     } catch (Exception e)  
	     { 
	    	log.error("获取失败：：：" + e.getMessage() + "   " + "  " + url + "  "
						+ fileName);
	        e.printStackTrace(); 
	     } finally{
	    	 
	    	if (ftp.isConnected()) {   
                try {   
                    ftp.disconnect();   
                } catch (IOException ioe) {
                	log.error("取消连接失败：：：" + url + "  " + remotePath + "  " + fileName);
                }   
            }   
	     }
	    return list; 
	} 
	
	public static boolean isActive(String url, String port, String username,String password) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, Integer.valueOf(port));// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				log.error("登录ftp服务器异常：：：" + url + "  " + port);
				return success;
			}
			ftp.logout();
			success=true;
			
		}catch (Exception e) {
			log.error("连接FTP服务器失败：：："  + url + "  "
					+ port,e);
		}finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					log.error("关闭ftp异常：：：" + ioe.getMessage() + "   " + "  "
							+ url + "  " + port);
				}
			}
		}
		return success;
	}
	
	

}
