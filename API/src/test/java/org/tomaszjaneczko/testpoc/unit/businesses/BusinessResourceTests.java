package org.tomaszjaneczko.testpoc.unit.businesses;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.tomaszjaneczko.testpoc.api.businesses.Business;
import org.tomaszjaneczko.testpoc.api.businesses.BusinessRepository;
import org.tomaszjaneczko.testpoc.api.businesses.BusinessesResource;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class BusinessResourceTests {

    private BusinessRepository repo;
    private BusinessesResource businessesResource;

    @Before
    public void setUp() throws Exception {
        repo = mock(BusinessRepository.class);
        businessesResource = new BusinessesResource(repo);
    }

    @Test
    public void itShouldGetAllBusinessesFromRepository() {
        businessesResource.getBusinesses();

        verify(repo).allBusinesses();
    }

    @Test
    public void itShouldGetSingleBusinessFromRepository() {
        when(repo.findBusiness(anyInt())).thenReturn(Optional.of(testBusiness()));

        final Business resultBusiness = businessesResource.getBusiness(1);

        verify(repo).findBusiness(1);
        assertThat("Same business was returned", resultBusiness, equalTo(testBusiness()));
    }

    @Test(expected = WebApplicationException.class)
    public void itShouldThrowWhenBusinessNotFoundInRepository() {
        when(repo.findBusiness(anyInt())).thenReturn(Optional.<Business>empty());

        businessesResource.getBusiness(1);
    }

    @Test
    public void itShouldAddBusiness() {
        when(repo.createBusiness(any(Business.class)))
                .thenReturn(Optional.of(testBusiness()));

        final Business resultBusiness = businessesResource.createBusiness(newBusiness());

        verify(repo).createBusiness(any(Business.class));
        assertThat("Business from the repository was returned", resultBusiness, Matchers.equalTo(testBusiness()));
    }

    @Test
    public void itShouldRemoveBusiness() {
        when(repo.deleteBusiness(anyLong()))
                .thenReturn(true);

        businessesResource.deleteBusiness(1);

        verify(repo).deleteBusiness(1);
    }

    @Test(expected = WebApplicationException.class)
    public void itShouldThrowWhenBusinessToRemoveNotExists() {
        when(repo.deleteBusiness(anyLong()))
                .thenReturn(false);

        businessesResource.deleteBusiness(1);
    }

    private Business newBusiness() {
        return new Business(null, "Test");
    }

    private Business testBusiness() {
        return new Business(1L, "Test");
    }
}
