package org.tomaszjaneczko.testpoc.api.businesses;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Business {
    private long id;

    @NotEmpty
    private String name;

    public Business() {
        // Jackson
    }

    public Business(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public Business copyWithNewId(long newId) {
        return new Business(newId, name);
    }
}
