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

