package se.oskare.cargo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.json.Json;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SessionState;
import se.oskare.SpaceBotsApi;

@Path("/cargo")
@Produces(MediaType.TEXT_HTML)
public class CargoResource {

    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    SessionState state;

    @POST
    @Path("/sell-all")
    public Response sellAll() {
        var inventory = apiService.getFleet(state.currentFleet);

        var request = new DirectSellRequest();
        request.resources.aluminium = inventory.cargo.aluminium;
        request.resources.zinc = inventory.cargo.zinc;
        request.resources.titanium = inventory.cargo.titanium;
        request.resources.zirconium = inventory.cargo.zirconium;
        request.resources.mithril = inventory.cargo.mithril;

        apiService.directSell(state.currentFleet, request);

        return Response.ok().header("HX-Trigger", "sale-completed").build();
    }
}
