package org.tomaszjaneczko.testpoc.api.businesses;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/api/businesses")
public class BusinessesResource {

    @GET
    @Timed
    public List<Business> getBusinesses() {
        return new BusinessRepository().allBusinesses();
    }

    @GET
    @Path("{business_id}")
    @Timed
    public Business getBusiness(@PathParam("business_id") String pathBusinessId) {
        long businessId = Long.valueOf(pathBusinessId);

        Optional<Business> business = new BusinessRepository().findBusiness(businessId);

        if (business.isPresent()) {
            return business.get();
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}