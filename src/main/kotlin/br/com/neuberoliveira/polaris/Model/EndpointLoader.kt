package br.com.neuberoliveira.polaris.Model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import io.vertx.core.json.JsonObject
import java.io.File

fun loadEndpoint(file: File):EndpointConfig {

    return EndpointConfig().fromJson(parseYaml(file))
}

fun loadEndpoint(path:String):EndpointConfig {
    return loadEndpoint(File(path))
}

fun parseYaml(file:File): JsonObject {
    val map = YAMLMapper()

    val tree = map.readTree(file)
    println(tree)
    return JsonObject.mapFrom(tree)
}