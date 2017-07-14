package com.flow.client.test;

import java.util.UUID;

public class MakeUUID {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));

	}

}
