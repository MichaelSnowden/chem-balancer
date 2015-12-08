package com.michaelsnowden.juniper;

import org.apache.tools.ant.filters.StringInputStream;
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

        get("/", new TemplateViewRoute() {
            @Override
            public ModelAndView handle(Request request, Response response) {
                Map<String, String> attributes = new HashMap<>();
                String equation = request.queryParams("equation");
                if (equation != null) {
                    attributes.put("equation", equation);
                    try {
                        attributes.put("balanced", Balancer.balance(new StringInputStream(equation)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        attributes.put("balanced", "Error: " + e.getCause());
                    }
                } else {
                    attributes.put("equation", "");
                    attributes.put("balanced", "");
                }
                return new ModelAndView(attributes, "index.ftl");
            }
        }, new FreeMarkerEngine());
    }

}

