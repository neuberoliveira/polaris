package br.com.neuberoliveira.polaris

import br.com.neuberoliveira.polaris.Model.EndpointConfig
import br.com.neuberoliveira.polaris.Model.loadEndpoint
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import java.io.File

class MainVerticle : AbstractVerticle(){
	override fun start(startFuture: Future<Void>) {
        /*val stores = mutableListOf<ConfigStoreOptions>()
        val fileStore = ConfigStoreOptions().setType("file").setFormat("yaml").setConfig(jsonObjectOf(
                "path" to "${configsDir.absolutePath}/endpoints/foo.yaml"
        ))
		val sysStore = ConfigStoreOptions().setType("sys")

        stores.add(fileStore)
        stores.add(sysStore)

		val options = configRetrieverOptionsOf(stores=stores)
		val retriever = ConfigRetriever.create(vertx, options)
		retriever.getConfig { evt ->
			if (evt.failed()) {
			} else {
				evt.map {
					val json = it
					val fields = json.fieldNames().sorted()
					fields.forEach { field -> println("$field = ${json.getValue(field)}") }
				}
			}
		}*/


		val server = vertx.createHttpServer()
		val router = Router.router(vertx)

        val projectDir:String = System.getProperty("user.dir")
        val configsDir = File("$projectDir/src/main/conf")

        val endpoints = mutableMapOf<String, EndpointConfig>()
        val dir = File("$configsDir/endpoints")
        dir.listFiles {_, name -> name.contains(".yaml")}.forEach {
            val ep = loadEndpoint(it)
            endpoints[ep.path] = ep
        }

		endpoints.forEach{ (_,ep) ->
			router.route(HttpMethod.GET, "/${ep.path}").handler { req ->
				req.response().putHeader("content-type", "text/plain").end("HAR!!! this is the ${ep.path} endpoint")
			}
		}

		server.requestHandler(router)
		server.listen(8888) { http ->
			if (http.succeeded()) {
				startFuture.complete()
				println("HTTP server started on port 8888")
			} else {
				startFuture.fail(http.cause())
			}
		}
	}
}
