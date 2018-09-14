package edu.ucsb.cs56.gaucho_gains;

import edu.ucsb.cs56.gaucho_gains.GainsDatabase;

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

		GainsDatabase db = new GainsDatabase();
		port(getHerokuAssignedPort());

		/* Dummy Database */
		HashMap<String, Object> model = new HashMap<String, Object>();

		/* Login Page */
		get("/", (rq, rs) ->  new ModelAndView(model, "login.mustache"), new MustacheTemplateEngine());

		/* Profile Page */
		post("/profile", (rq, rs) -> {
			if (!rq.queryParams("password").equals(rq.queryParams("passwordConfirm"))) {
				model.put("errMess", "Passwords do not match");
				return (new ModelAndView(model, "signuperror.mustache"));
			}
			String check = db.signUp(rq);
                        model.put("errMess", check);
                        return check.equals("Sign Up Success") ? (new ModelAndView(model, "profile.mustache")):(new ModelAndView(model, "signuperror.mustache"));
		}, new MustacheTemplateEngine());

		/* Next Page After Login */
		post("/community", (rq, rs) -> {
			String check = db.logIn(rq);
			model.put("errMess", check);
			return check.equals("Login Success") ? (new ModelAndView(model, "community.mustache")):(new ModelAndView(model, "signuperror.mustache"));
		}, new MustacheTemplateEngine());

		get("/signup", (rq, rs) -> new ModelAndView(model, "signup.mustache"), new MustacheTemplateEngine());
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null)
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}	
}
