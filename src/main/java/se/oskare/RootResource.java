package se.oskare;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class RootResource {

    @Inject
    Template base;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance root() {
        return base.instance();
    }
}
