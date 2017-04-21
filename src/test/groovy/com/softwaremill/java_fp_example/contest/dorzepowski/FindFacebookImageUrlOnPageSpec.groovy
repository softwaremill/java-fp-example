package com.softwaremill.java_fp_example.contest.dorzepowski

import spock.lang.Specification

class FindFacebookImageUrlOnPageSpec extends Specification {

	public static
	final String DEFAULT_IMAGE = "https://softwaremill.com/images/logo-vertical.023d8496.png"


	def "Find facebook image url for page that contain such url"() {
		given:
			def urlWithImage = new URL("https://softwaremill.com/the-wrong-abstraction-recap/")
			def page = new Page(urlWithImage)
		when:
			def facebookImageUrl = page.facebookImage().url()
		then:
			'https://softwaremill.com/images/uploads/2017/02/street-shoe-chewing-gum.0526d557.jpg' == facebookImageUrl.toString()
	}


	def "Find default url when retrieving facebook image url from not existing page"() {
		given:
			def notExistingUrl = new URL("http://i-do-not-exist.pl")
			def page = new Page(notExistingUrl)
		when:
			def facebookImageUrl = page.facebookImage().url()
		then:
			DEFAULT_IMAGE == facebookImageUrl.toString()
	}

	def "Find default url when retrieving facebook image url from page that doesn't contain facebook image"() {
		given:
			def urlWithoutImage = new URL("https://twitter.com/softwaremill")
			def page = new Page(urlWithoutImage)
		when:
			def facebookImageUrl = page.facebookImage().url()
		then:
			DEFAULT_IMAGE == facebookImageUrl.toString()
	}

	def "Find default url when url is null"() {
		given:
			def page = new Page(null)
		when:
			def facebookImageUrl = page.facebookImage().url()
		then:
			DEFAULT_IMAGE == facebookImageUrl.toString()
	}
}
