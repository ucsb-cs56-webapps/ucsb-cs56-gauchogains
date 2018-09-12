package edu.ucsb.cs56.gaucho_gains;

import static spark.Spark.port;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class Project {
    public static final String CLASSNAME="Project";
    public static final Logger log = Logger.getLogger(CLASSNAME);
    
    public static void main(String[] args) {
	log.setLevel(Level.INFO);

	log.trace("Trace Message!");
	log.debug("Debug Message!");
	log.info("Info Message!");
	log.warn("Warn Message!");
	log.error("Error Message!");
	log.fatal("Fatal Message!");
	
        port(getHerokuAssignedPort());

	/* Dummy Database */
	Map<String, Object> model = new HashMap<String, Object>();

	/* Login Page */
	get("/", (rq, rs) -> {
		log.info("get /");
		return new ModelAndView(model, "login.mustache");
	    }, new MustacheTemplateEngine());

	/* Sign-Up Page */
	get("/signup", (rq, rs) -> new ModelAndView(model, "signup.mustache"), new MustacheTemplateEngine());

	/* Next Page After Login */
	post("/community", (rq, rs) -> {
		log.info("post /community");
		
		String user = rq.queryParams("email");
		String pass = rq.queryParams("pass");
		UserManager um = new UserManager();
		model.put("validUser", um.checkUser(user,pass));
		model.put("user",user);
		
		return new ModelAndView(model, "community.mustache");
	    }, new MustacheTemplateEngine());

	post("/profile", (rq, rs) -> new ModelAndView(model, "profile.mustache"), new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }	
}
