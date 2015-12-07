package com.michaelsnowden.juniper;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * @author michael.snowden
 */
public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

//        get("/balance", new Route() {
//            @Override
//            public Object handle(Request req, Response res) throws Exception {
//                final String equation = req.queryParams("equation");
//                System.out.println(equation);
//                return Balancer.balance(new StringInputStream(equation));
//            }
//        });

        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, String> attributes = new HashMap<>();
                final String equation = request.queryParams("equation");
                if (equation != null) {
                    attributes.put("equation", equation);
                }
                return new ModelAndView(attributes, "index.ftl");
            }
        }, new FreeMarkerEngine());
    }

}

