package se.oskare.system;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import io.quarkus.qute.Location;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SessionState;
import se.oskare.SpaceBotsApi;

@Path("/systems")
@Produces(MediaType.TEXT_HTML)
public class SystemResource {

    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    SessionState state;

    @Inject
    @Location("system-selection")
    Template systemSelection;

    @Inject
    Template system;

    @Inject
    Template travel;

    @GET
    @Path("/current")
    public TemplateInstance getSystem() {
        var fleet = apiService.getFleet(state.currentFleet);
        var s = apiService.getSystem(fleet.locationSystemId);

        return system.data("system", s);
    }

    @GET
    @Path("/selection")
    public TemplateInstance getSystemSelection() {
        var fleet = apiService.getFleet(state.currentFleet);
        var system = apiService.getSystem(fleet.locationSystemId);

        return systemSelection.data("system", system);
    }

    @POST
    @Path("/travel")
    public Response travel(TravelModel model) {
        var request = new TravelRequest();
        request.destinationSystemId = model.systemId;

        var result = apiService.travel(state.currentFleet, request);

        JsonObject events = new JsonObject();
        JsonObject actionEvent = new JsonObject();
        actionEvent.put("duration", result.duration);
        events.put("action-started", actionEvent);

        return Response
                .ok(travel.data("duration", result.duration))
                .header("HX-Trigger", events)
                .build();
    }
}
