package com.michaelsnowden.juniper;

import org.apache.tools.ant.filters.StringInputStream;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
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
                final String equation = request.queryParams("equation");
                if (equation != null) {
                    try {
                        attributes.put("equation", Balancer.balance(new StringInputStream(equation)));
                    } catch (IOException e) {
                        e.printStackTrace();
                        attributes.put("equation", e.getMessage());
                    }
                } else {
                    attributes.put("equation", "");
                }
                return new ModelAndView(attributes, "index.ftl");
            }
        }, new FreeMarkerEngine());
    }

}

