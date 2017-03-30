/**
 * 
 */
package com.idealunited.inf.service.impl;


public class SHAMessageDigestImpl extends AbstractMessageDigestImpl {
	
	private final String algname ="SHA-1"; 
	/* (non-Javadoc)
	 */

	@Override
	public String getAlgname() {
		return "SHA-1";
	}

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
		SHAMessageDigestImpl it = new SHAMessageDigestImpl();
		
		byte[] cltxt = "123abc中国".getBytes();
		String mtxt = null;
		boolean re;
		try{
			mtxt = it.genMessageDigest(cltxt);
			System.out.println(mtxt.length());
			re=it.validateMessageDigest(cltxt, mtxt);
			System.out.println(re);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	
}
