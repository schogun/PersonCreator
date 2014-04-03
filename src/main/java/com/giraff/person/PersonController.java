package com.giraff.person;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

@Controller
public class PersonController {

	@Autowired
	PersonManager personManager;
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Root called! The client locale is {}.", locale);
		
		
		return "home";
	}
	
	@RequestMapping(value = "/view/{personId}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable("personId") long personId,
			RedirectAttributes redirectAttributes) {
		logger.info("Call to view person with id {}",personId);
		
		Person person = personManager.getPersonById(personId);
		
		ModelAndView mv = new ModelAndView("viewPerson");
		mv.addObject(person);
		return mv;
	}
	
	@RequestMapping(value = "/view/json/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String viewJson(@PathVariable("personId") long personId, HttpServletResponse response) {
		
		Gson gson = new Gson();
		
		Person person = personManager.getPersonById(personId);
		
		String jsonString = gson.toJson(person);
		
		response.setContentType("application/json");
		return jsonString;
	}
	
	@RequestMapping(value = "/delete/{personId}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("personId") long personId,
			RedirectAttributes redirectAttributes) {
		logger.info("Call to delete person with id {}",personId);
		
		personManager.delete(personId);
		
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		logger.info("Call to get person list.");
		
		List<Person> personList = personManager.getAllPersons();
		
		ModelAndView mv = new ModelAndView("listPersons");
		mv.addObject(personList);
		return mv;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String listJson(HttpServletResponse response) {
		logger.info("Call to get person list as JSON.");
		
		Gson gson = new Gson();
		
		List<Person> personList = personManager.getAllPersons();
		
		String listString = gson.toJson(personList);

		response.setContentType("application/json");
		return listString;
	}
	
	@RequestMapping(value = "/createPerson", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Post to create person.");
		
		String givenName = request.getParameter("givenName");
		String familyName = request.getParameter("familyName");
		String email = request.getParameter("mbox");
		String homepage = request.getParameter("homepage");
		String gender = request.getParameter("gender");
		
		Person person = new Person();
		person.setGivenName(givenName);
		person.setFamilyName(familyName);

		try{
			person.setGender(Person.Gender.valueOf(gender));	
		}catch(IllegalArgumentException arge){
			logger.error("Gender selected is not in enum.");
		}
		
		
		person.setMbox(email);
		person.setHomepage(homepage);
		
		personManager.persist(person);
		
		return new ModelAndView("redirect:/view/" + person.getId());
	}
}
