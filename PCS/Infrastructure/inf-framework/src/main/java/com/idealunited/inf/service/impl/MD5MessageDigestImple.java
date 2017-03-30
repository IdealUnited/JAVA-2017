package com.idealunited.inf.service.impl;


public class MD5MessageDigestImple extends AbstractMessageDigestImpl {
	
	private static final String algname="MD5";
//	@Override
//	public byte[] genMessageDigest(byte[] src) throws Exception {
//		// TODO Auto-generated method stub
//		return genMessageDigest(src,algname);
//	}
//
//	@Override
//	public boolean validateMessageDigest(byte[] src, byte[] dest)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return validateMessageDigest(src,dest,algname);
//	}

	
	public static void main(String[] args){
		MD5MessageDigestImple it = new MD5MessageDigestImple();
		
		byte[] cltxt = "123abc中国".getBytes();
		String mtxt = null;
		boolean re;
		try{
			mtxt = it.genMessageDigest(cltxt);
			re=it.validateMessageDigest(cltxt, mtxt);
			System.out.println(re);
		}catch(Exception e){
			e.printStackTrace();
		}
	}




	@Override
	public String getAlgname() {
		// TODO Auto-generated method stub
		return "MD5";
	}

}
