package com.giraff.person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:**/servlet-context.xml","file:**/root-context.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class PersonManagerTest {

	private static final Logger logger = LoggerFactory.getLogger(PersonManagerTest.class);
	
	@Autowired
	PersonManager personManager;
	
	private Person rockyBalboa = new Person();
	private Person ivanDrago = new Person();

	@Before
	public void setUp() throws Exception {

		rockyBalboa.setFamilyName("Balboa");
		rockyBalboa.setMbox("test@giraff.se");
		rockyBalboa.setGender(Person.Gender.Male);
		rockyBalboa.setGivenName("Rocky");
		rockyBalboa.setName("Rocky Balboa");
		rockyBalboa.setHomepage("www.giraff.se");

		ivanDrago.setFamilyName("Drago");
		ivanDrago.setMbox("test@giraff.se");
		ivanDrago.setGender(Person.Gender.Female);
		ivanDrago.setGivenName("Ivan");
		ivanDrago.setName("Ivan Drago");
		ivanDrago.setHomepage("www.giraff.se");
		
	}
	@Test
	public void testDatabase(){


		testPersistence();

		testGetAllPersons();

		testUpdate();
		
	}

	private void testPersistence(){

		personManager.persist(rockyBalboa);

		personManager.persist(ivanDrago);

		//Id is assigned during save to database, since we use @GeneratedValue in Person class
		assertTrue(rockyBalboa.getId() != 0);

	}

	private void testGetAllPersons() {

		assertTrue(personManager.getAllPersons().size() == 2);
	}

	private void testUpdate() {
		
		String name = rockyBalboa.getFamilyName();
		
		rockyBalboa.setFamilyName("Changed");
		
		personManager.upate(rockyBalboa);
		
		Person assertPerson = personManager.getPersonById(rockyBalboa.getId());
		
		assertFalse(assertPerson.getFamilyName().equals(name));
		
	}


	//only for tear down, since it is the same transaction we can't assert delete (or we need to use explicit transaction
	@After
	public void cleanUp(){
		long id = rockyBalboa.getId();
		personManager.delete(id);
		Person assertPerson = null;
		try{
			assertPerson = personManager.getPersonById(id);
		}catch(Exception e){
			logger.info(e.getMessage());
		}

	}
}
