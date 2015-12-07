package com.michaelsnowden.juniper;

import org.apache.tools.ant.filters.StringInputStream;
import spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;

/**
 * @author michael.snowden
 */
public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/balance", new Route() {
            @Override
            public Object handle(Request req, Response res) throws Exception {
                final String equation = req.queryParams("equation");
                System.out.println(equation);
                return Balancer.balance(new StringInputStream(equation));
            }
        });

        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(null, "index.ftl");
            }
        }, new FreeMarkerEngine());
    }

}

