package org.tomaszjaneczko.testpoc.api.useless_info;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/useless-info")
public class UselessInfoResource {

    @GET
    @Timed
    public String getUselessInfo() {
        return "Wat?";
    }
}
