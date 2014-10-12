package org.tomaszjaneczko.testpoc.api_integration_tests;

import com.sun.jersey.api.Responses;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.tomaszjaneczko.testpoc.api.TestAPIApplication;
import org.tomaszjaneczko.testpoc.api.TestAPIConfiguration;
import org.tomaszjaneczko.testpoc.api.businesses.Business;

import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class BusinessIntegrationTests {

    @ClassRule
    public static final DropwizardAppRule<TestAPIConfiguration> RULE =
            new DropwizardAppRule<TestAPIConfiguration>(TestAPIApplication.class, null);

    private Client client;
    private String businessesUrl;

    @Before
    public void setup() {
        client = new Client();
        businessesUrl = "http://localhost:" + RULE.getLocalPort() + "/api/businesses";
    }

    @Test
    public void testIfAddsBusinesses() {
        final int initialCount = getCurrentListCount();

        Business newBusiness = new Business(null, "TestBusiness");

        final ClientResponse response = getBusinessesClient()
                .post(ClientResponse.class, newBusiness);

        assertThat("Response is successful", response.getStatus(), is(200));

        final Business createdBusiness = response.getEntity(Business.class);
        checkResultBusinessIsValid(createdBusiness, newBusiness);

        assertThat("List count changed by one", getCurrentListCount(), is(initialCount + 1));
    }

    @Test
    public void testIfRemovesBusinesses() {
        final ClientResponse response = createTestBusiness();
        final int countBeforeDelete = getCurrentListCount();

        long newId = response.getEntity(Business.class).getId();

        final ClientResponse clientResponse = getBusinessesClient(newId)
                .delete(ClientResponse.class);

        assertThat("Response is no content", clientResponse.getStatus(), is(Responses.NO_CONTENT));
        assertThat("There is one element less in the list",
                getCurrentListCount(), is(countBeforeDelete - 1));
    }

    // Create methods
    private ClientResponse createTestBusiness() {
        Business newBusiness = new Business(null, "TestBusiness");

        final ClientResponse response = getBusinessesClient()
                .post(ClientResponse.class, newBusiness);

        assertThat("Response is successful", response.getStatus(), is(200));
        return response;
    }

    // Validators
    private void checkResultBusinessIsValid(Business resultBusiness, Business requestedBusiness) {
        assertThat("Id is set in result object", resultBusiness.getId(), is(notNullValue()));
        assertThat("Names are the same", resultBusiness.getName(), is(requestedBusiness.getName()));
    }

    // Check methods
    private int getCurrentListCount() {
        final ClientResponse getResponse = getBusinessesClient().get(ClientResponse.class);
        final List initialList = getResponse.getEntity(List.class);
        return initialList.size();
    }

    // Client building
    private WebResource.Builder getBusinessesClient(long id) {
        return this.client.resource(businessesUrl).path(String.valueOf(id))
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .type(MediaType.APPLICATION_JSON_TYPE);
    }

    private WebResource.Builder getBusinessesClient() {
        return this.client.resource(businessesUrl)
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .type(MediaType.APPLICATION_JSON_TYPE);
    }


}
