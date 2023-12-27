package se.oskare.fleet;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SessionState;
import se.oskare.SpaceBotsApi;

@Path("/fleet")
@Produces(MediaType.TEXT_HTML)
public class FleetResource {
    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    SessionState state;

    @Inject
    Template fleet;

    @GET
    public TemplateInstance getFleetInfo() {
        return fleet.data("fleet", apiService.getFleet(state.currentFleet));
    }
}
