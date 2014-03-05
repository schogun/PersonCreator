package com.giraff.person;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonManager {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@PersistenceContext 
	private EntityManager em;
	
	@Transactional
    public void persist(Person person) {
        try{
        	em.persist(person);
        	
        }catch(Exception e){
        	logger.error(e.getMessage());
        }
        finally{
        	try {
				em.close();
			} catch (Exception e) {
				logger.error("Could not close connection", e);
			}
        }
    }
	
	@Transactional
	public void upate(Person person) {
        try{
        	em.merge(person);
        }catch(Exception e){
        	logger.error(e.getMessage());
        }
        finally{
        	try {
				em.close();
			} catch (Exception e) {
				logger.error("Could not close connection", e);
			}
        }
    }
	
	@Transactional
	public void delete(Long id) {
        try{
        	Person person = getPersonById(id);
        	em.remove(person);
        }catch(Exception e){
        	logger.error(e.getMessage());
        }
        finally{
        	try {
				em.close();
			} catch (Exception e) {
				logger.error("Could not delete person connection", e);
			}
        }
    }
	
	public Person getPersonById(Long id) {
        Person person = em.find(Person.class,id);
        return person;
    }
	
	public List<Person> getAllPersons() {
        TypedQuery<Person> query = em.createQuery(
            "SELECT p FROM Person p ORDER BY p.familyName", Person.class);
        return query.getResultList();
    }
}
