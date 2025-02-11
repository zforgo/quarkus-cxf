package io.quarkiverse.cxf.it.redirect.retransmitcache;

import java.util.concurrent.Future;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.AsyncHandler;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.Response;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.5
 * 2024-11-21T16:40:52.615+01:00
 * Generated source version: 4.0.5
 *
 */
@WebService(targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", name = "RetransmitCacheService")
@XmlSeeAlso({ ObjectFactory.class })
public interface RetransmitCacheService {

    @WebMethod(operationName = "retransmitCache")
    @RequestWrapper(localName = "retransmitCache", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCache")
    @ResponseWrapper(localName = "retransmitCacheResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheResponse")
    public Response<io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheResponse> retransmitCacheAsync(
            @WebParam(name = "expectedFileCount", targetNamespace = "") int expectedFileCount,
            @WebParam(name = "payload", targetNamespace = "") String payload);

    @WebMethod(operationName = "retransmitCache")
    @ResponseWrapper(localName = "retransmitCacheResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheResponse")
    @RequestWrapper(localName = "retransmitCache", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCache")
    public Future<?> retransmitCacheAsync(
            @WebParam(name = "expectedFileCount", targetNamespace = "") int expectedFileCount,
            @WebParam(name = "prefix", targetNamespace = "") String payload,
            @WebParam(name = "asyncHandler", targetNamespace = "") AsyncHandler<io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheResponse> asyncHandler);

    @WebMethod
    @RequestWrapper(localName = "retransmitCache", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCache")
    @ResponseWrapper(localName = "retransmitCacheResponse", targetNamespace = "https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test", className = "io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheResponse")
    @WebResult(name = "return", targetNamespace = "")
    public io.quarkiverse.cxf.it.redirect.retransmitcache.RetransmitCacheOutput retransmitCache(
            @WebParam(name = "expectedFileCount", targetNamespace = "") int expectedFileCount,
            @WebParam(name = "payload", targetNamespace = "") String payload);
}
