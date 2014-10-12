package org.tomaszjaneczko.testpoc.api;


import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.tomaszjaneczko.testpoc.api.businesses.BusinessesResource;

public class TestAPIApplication extends Application<TestAPIConfiguration> {
    public static void main(String[] args) throws Exception {
        new TestAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "test-api";
    }

    @Override
    public void initialize(Bootstrap<TestAPIConfiguration> bootstrap) {

    }

    @Override
    public void run(TestAPIConfiguration configuration, Environment environment) {
        environment.jersey().register(new BusinessesResource());
    }
}
