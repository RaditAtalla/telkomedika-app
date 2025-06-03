package com.dpbo.telkomedika;

import java.util.Random;

public class CodeGeneration {
	private String code;
	private static final String character = "ABCDEFGHIJKLMOPQRSTUVWXYZ1234567890";
	private static Random random = new Random();
	
	public void generateCode() {
		String hasil = "";
		for (int i = 0; i < 6; i++) {
			int index = random.nextInt(character.length());
			hasil += character.charAt(index);
		}
		this.code = hasil;
	}

	public String getCode() {
		return code;
	}
	
	
}
