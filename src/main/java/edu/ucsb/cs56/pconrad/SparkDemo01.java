package edu.ucsb.cs56.pconrad;

import static spark.Spark.port;


/**
 * Hello world!
 *
 */

public class SparkDemo01 {
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
		
		System.out.println("");
		System.out.println("(Don't worry about the warnings below about SLF4J... we'll deal with those later)");
		System.out.println("");						  
		System.out.println("In browser, visit: http://localhost:" + getHerokuAssignedPort() + "/hello");
		System.out.println("");
        spark.Spark.get("/", (req, res) -> "Hello World");
		spark.Spark.get("/hello", (req, res) -> "Hello World");
		spark.Spark.get("/bye", (req, res) -> "Goodbye World");
		spark.Spark.get("/yo", (req, res) -> "S'up World");
		spark.Spark.get("/tension", (req, res) -> "Midterm next week.  No problem dude");		

	}
	
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

	
}
