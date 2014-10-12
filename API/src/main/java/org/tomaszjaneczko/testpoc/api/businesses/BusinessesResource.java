package org.tomaszjaneczko.testpoc.api.businesses;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;

import javax.validation.Valid;
import javax.ws.rs.*;
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

    @POST
    @Timed
    public Business createBusiness(@Valid Business business) {
        final Optional<Business> createdBusiness = new BusinessRepository().createBusiness(business);

        if (createdBusiness.isPresent()) {
            return createdBusiness.get();
        } else {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}