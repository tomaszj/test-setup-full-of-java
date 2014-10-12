package org.tomaszjaneczko.testpoc.api.businesses;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;

class BusinessRepository {
    final List<Business> businesses;

    public BusinessRepository() {
        final Business business1 = new Business(1L, "Business 1");
        final Business business2 = new Business(2L, "Business 2");
        businesses = Lists.newArrayList(business1, business2);
    }

    public List<Business> allBusinesses() {
        return businesses;
    }

    public Optional<Business> findBusiness(long id) {
        return businesses.stream().filter(
            b -> b != null && b.getId() == id
        ).findFirst();
    }
}
