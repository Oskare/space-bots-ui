package se.oskare.ship;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SessionState;
import se.oskare.SpaceBotsApi;

@Path("/ship")
@Produces(MediaType.TEXT_HTML)
public class ShipResource {
    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    SessionState state;

    @Inject
    @Location("ship-selection.qute.html")
    Template shipSelection;

    @Path("/selection")
    @GET
    public TemplateInstance getPlayerInfo() {
        return shipSelection.data("shipTypes", apiService.getShipTypes());
    }

    @Path("/purchase")
    @POST
    public Response purchaseShips(PurchaseModel model) {
        var request = new PurchaseRequest();
        request.shipsToBuy.miner = model.minerCount;

        apiService.buyShips(state.currentFleet, request);

        return Response.ok().header("HX-Trigger", "purchase-completed").build();
    }
}
