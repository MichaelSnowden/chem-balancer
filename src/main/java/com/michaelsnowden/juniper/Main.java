package com.michaelsnowden.juniper;

import org.apache.tools.ant.filters.StringInputStream;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.*;

/**
 * @author michael.snowden
 */
public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/hello", new Route() {
            @Override
            public Object handle(Request req, Response res) throws Exception {
                final String equation = req.queryParams("equation");
                System.out.println(equation);
                return Balancer.balance(new StringInputStream(equation));
            }
        });
    }

}

