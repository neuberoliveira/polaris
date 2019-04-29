package br.com.neuberoliveira.polaris.Request

import br.com.neuberoliveira.polaris.Model.EndpointConfig
import io.vertx.ext.web.RoutingContext

typealias Handler = (routingContext:RoutingContext)->Unit

class RequestHandler(ep:EndpointConfig) {
	var endpoint:EndpointConfig = ep

	fun handleSave():Handler {
		return {req -> req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${endpoint.path} SAVE")}
	}

	fun handleList():Handler {
		return {req -> req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${endpoint.path} LIST")}
	}

	fun handleShow():Handler {
		return {req -> req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${endpoint.path} SHOW")}
	}

	fun handleUpdate():Handler {
		return {req -> req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${endpoint.path} UPDATE")}
	}

	fun handleDelete():Handler {
		return {req -> req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${endpoint.path} DELETE")}
	}
}
