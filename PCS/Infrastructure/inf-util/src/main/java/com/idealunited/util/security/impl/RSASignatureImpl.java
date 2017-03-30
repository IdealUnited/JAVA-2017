package com.idealunited.util.security.impl;

import java.security.PublicKey;

public class RSASignatureImpl extends AbstractSignatureImpl {

	static final String algname = "SHA1withRSA";
	static final String keyalgname = "RSA";

	public static void main(String[] args) {
		RSASignatureImpl it = new RSASignatureImpl();
		PublicKey pk = null;
		byte[] cltxt = null;

		/*
		 * 
		 * try{ cltxt =
		 * "MIICKjCCAZMCBEiQOTUwDQYJKoZIhvcNAQECBQAwWzELMAkGA1UEBhMCY24xCzAJBgNVBAgTAnNoMQswCQYDVQQHEwJzaDEPMA0GA1UEChMGOTliaWxsMQ8wDQYDVQQLEwZjbGllbnQxEDAOBgNVBAMTB3dlYnNpdGUwIBcNMDgwNzMwMDk0OTQxWhgPMjEwODA3MDYwOTQ5NDFaMFsxCzAJBgNVBAYTAmNuMQswCQYDVQQIEwJzaDELMAkGA1UEBxMCc2gxDzANBgNVBAoTBjk5YmlsbDEPMA0GA1UECxMGY2xpZW50MRAwDgYDVQQDEwd3ZWJzaXRlMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCP8NrNZeIf5mrvilMUJFLcGCRx02sP5NwlzsfHESVJu5Tq9/KS7MS7LWNdrG9rey27Ix+ItnRgjvUElo27Pl4yNa+KGS7gjCiG4YK9p6WmBWK+7BAThaVATxXWDfZuvr6gKSQF4VVIoQTcsBvfoCQyEmTQfENJb/JkzBI45sgrcwIDAQABMA0GCSqGSIb3DQEBAgUAA4GBADPv/Ae7ZTmhqBnGOCUt55T7bJtrAkR7S1NqQJMsnkFKJet+U5SJMnUDDIFIhKWKN6HBorOMLMLDYZy7f7co9o8pv2Om/44+dbime8ylrOlw1rYoBpNaUn5O09Ua5UwrRLdf+0B7yrhujo50IJA8lj+APGtdVFA/jAibhDGBlbSR"
		 * .getBytes(); pk=it.getPublicKey(cltxt); System.out.println("");
		 * }catch(Exception e){ e.printStackTrace(); System.out.println(""); }
		 */

		// byte[] cltxt = "1234abcd中国".getBytes();
		/*
		 * String s=null; try{ RSASignatureImpl it = new RSASignatureImpl();
		 * KeyPair k = it.genPairKey(1024); s=it.genSignature(cltxt,
		 * k.getPrivate()); System.out.println("s="+s); }catch(Exception e){
		 * 
		 * e.printStackTrace(); }
		 */
	}

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
