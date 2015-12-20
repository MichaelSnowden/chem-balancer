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

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });


        before(new Filter() {
            @Override
            public void handle(Request request, Response response) throws Exception {
                response.header("Access-Control-Allow-Origin", "*");
            }
        });

        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                return new ModelAndView(null, "index.ftl");
            }
        }, new FreeMarkerEngine());

        get("/solve", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String equation = request.queryParams("equation");
                if (equation != null) {
                    return new EquationFactory().getBalancedEquation(new StringInputStream(equation)).asPlainText();
                }
                return "";
            }
        });

        get("/solve-json", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String equation = request.queryParams("equation");
                if (equation != null) {
                    return new EquationFactory().getBalancedEquation(new StringInputStream(equation)).asPlainText();
                }
                return "";
            }
        });
    }
}

