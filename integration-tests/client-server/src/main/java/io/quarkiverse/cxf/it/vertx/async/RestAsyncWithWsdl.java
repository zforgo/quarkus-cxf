package io.quarkiverse.cxf.it.vertx.async;

import jakarta.enterprise.inject.Instance;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkiverse.cxf.deployment.test.HelloService;
import io.smallrye.mutiny.Uni;

@Path("/RestAsyncWithWsdl")
public class RestAsyncWithWsdl {

    @CXFClient("helloWithWsdl")
    Instance<HelloService> helloWithWsdl;

    @Path("/helloWithWsdl")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> helloWithWsdl(String body) {
        /* With WSDL and without @Blocking should fail due to blocking WSDL call on the I/O thread */
        return Uni.createFrom()
                .future(helloWithWsdl.get().helloAsync(body))
                .map(addResponse -> addResponse.getReturn())
                .map(String::valueOf);
    }

}