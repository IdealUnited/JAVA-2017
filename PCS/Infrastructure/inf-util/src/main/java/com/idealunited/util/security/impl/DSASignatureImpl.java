/**
 * 
 */
package com.idealunited.util.security.impl;


public class DSASignatureImpl extends AbstractSignatureImpl {
	static final  String algname="DSA";
	static final  String keyalgname="DSA";

	@Override
	public String getAlgname() {
		// TODO Auto-generated method stub
		return algname;
	}

	@Override
	public String getKeyAlgname() {
		// TODO Auto-generated method stub
		return keyalgname;
	} 
}
