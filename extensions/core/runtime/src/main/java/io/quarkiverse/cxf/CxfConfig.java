package io.quarkiverse.cxf;

import java.util.Map;
import java.util.Optional;

import io.quarkiverse.cxf.LoggingConfig.GlobalLoggingConfig;
import io.quarkus.runtime.annotations.ConfigDocIgnore;
import io.quarkus.runtime.annotations.ConfigDocMapKey;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefaults;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "quarkus.cxf")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface CxfConfig {

    // The formatter breaks the java snippet
    // @formatter:off
    /**
     * An URI base to use as a prefix of `quarkus.cxf.client."client-name".decoupled-endpoint`. You will typically want to set this
     * to something like the following:
     *
     * [source,properties]
     * ----
     * quarkus.cxf.decoupled-endpoint-base = https://api.example.com:${quarkus.http.ssl-port}${quarkus.cxf.path}
     * # or for plain HTTP
     * quarkus.cxf.decoupled-endpoint-base = http://api.example.com:${quarkus.http.port}${quarkus.cxf.path}
     * ----
     *
     * If you invoke your WS client from within a HTTP handler, you can leave this option unspecified and rather set it
     * dynamically on the request context of your WS client using the `org.apache.cxf.ws.addressing.decoupled.endpoint.base`
     * key. Here is an example how to do that from a RESTeasy handler method:
     *
     * [source,java]
     * ----
     * import java.util.Map;
     * import jakarta.inject.Inject;
     * import jakarta.ws.rs.POST;
     * import jakarta.ws.rs.Path;
     * import jakarta.ws.rs.Produces;
     * import jakarta.ws.rs.core.Context;
     * import jakarta.ws.rs.core.MediaType;
     * import jakarta.ws.rs.core.UriInfo;
     * import jakarta.xml.ws.BindingProvider;
     * import io.quarkiverse.cxf.annotation.CXFClient;
     * import org.eclipse.microprofile.config.inject.ConfigProperty;
     *
     * &#64;Path("/my-rest")
     * public class MyRestEasyResource {
     *
     *     &#64;Inject
     *     &#64;CXFClient("hello")
     *     HelloService helloService;
     *
     *     &#64;ConfigProperty(name = "quarkus.cxf.path")
     *                      String quarkusCxfPath;
     *
     *     &#64;POST
     *     &#64;Path("/hello")
     *     &#64;Produces(MediaType.TEXT_PLAIN)
     *         public String hello(String body, &#64;Context UriInfo uriInfo) throws IOException {
     *
     *         // You may consider doing this only once if you are sure that your service is accessed
     *         // through a single hostname
     *         String decoupledEndpointBase = uriInfo.getBaseUriBuilder().path(quarkusCxfPath);
     *         Map>String, Object< requestContext = ((BindingProvider)
     *         helloService).getRequestContext();
     *         requestContext.put("org.apache.cxf.ws.addressing.decoupled.endpoint.base",
     *         decoupledEndpointBase);
     *
     *         return wsrmHelloService.hello(body);
     *     }
     * }
     * ----
     *
     * @since 2.7.0
     * @asciidoclet
     */
    // @formatter:on
    public Optional<String> decoupledEndpointBase();

    /**
     * Choose the path of each web services.
     *
     * @asciidoclet
     */
    @WithName("endpoint")
    @ConfigDocMapKey("/endpoint-path")
    @WithDefaults
    public Map<String, CxfEndpointConfig> endpoints();

    /**
     * Configure client proxies.
     *
     * @asciidoclet
     */
    @WithName("client")
    @ConfigDocMapKey("client-name")

    @WithDefaults
    public Map<String, CxfClientConfig> clients();

    /**
     * This exists just as a convenient way to get a `CxfClientConfig` with all defaults set. It is not intended to be used by
     * end users.
     *
     * @asciidoclet
     */
    public InternalConfig internal();

    /**
     * Global logging related configuration
     *
     * @asciidoclet
     */
    GlobalLoggingConfig logging();

    default boolean isClientPresent(String key) {
        return Optional.ofNullable(clients()).map(m -> m.containsKey(key)).orElse(false);
    }

    default CxfClientConfig getClient(String key) {
        return Optional.ofNullable(clients()).map(m -> m.get(key)).orElse(null);
    }

    public interface InternalConfig {

        @ConfigDocIgnore
        public CxfClientConfig client();
    }
}
