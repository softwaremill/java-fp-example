package com.softwaremill.java_fp_example.contest.oxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxoxo;

public class FacebookImageTest {

	public void testMinimalisticVersion() {
		assert FacebookImageVersion.extractImageAddressFrom("https://softwaremill.com/the-wrong-abstraction-recap/").equals("https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg");
		assert FacebookImageVersion.extractImageAddressFrom("https://softwaremill.com/using-kafka-as-a-message-queue/").equals("https://softwaremill.com/images/uploads/2017/02/kmq.93f842cf.png");
		assert FacebookImageVersion.extractImageAddressFrom("https://twitter.com/softwaremill").equals(FacebookImageVersion.DEFAULT_IMAGE);
		assert FacebookImageVersion.extractImageAddressFrom("http://i-do-not-exist.pl").equals(FacebookImageVersion.DEFAULT_IMAGE);
	}

}
