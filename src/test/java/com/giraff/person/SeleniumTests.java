package com.giraff.person;

import com.sun.webkit.WebPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.test.context.ContextConfiguration;

public class SeleniumTests {

	@Test
	public void createPerson(){
		
		WebDriver driver = null;
		
		//you need Firefox to be installed locally!
		driver = new FirefoxDriver();
		
		driver.get("http://localhost:8080/person");
		
		driver.findElement(By.id("givenName")).sendKeys("Per");
		
		driver.findElement(By.id("familyName")).sendKeys("Sch&ouml;garne");
		
		driver.findElement(By.id("mbox")).sendKeys("per.schogarne@giraff.se");
		
		driver.findElement(By.id("homepage")).sendKeys("www.giraff.se");
		
		driver.findElement(By.id("submitBtn")).submit();

		//go to list in HTML
		driver.get("http://localhost:8080/person/list");


	    //driver.close();
	}
}
