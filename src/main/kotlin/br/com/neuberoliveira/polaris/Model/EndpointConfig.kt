package br.com.neuberoliveira.polaris.Model

import io.vertx.core.json.JsonObject

class EndpointConfig {
    var path = ""
    var table = ""
    var fields = mutableListOf<EndpointField>()

    fun fromJson(json: JsonObject):EndpointConfig {
        this.path = json.getString("path")
        this.table = json.getString("table")

        val fieldsList = json.getJsonObject("fields")
        fieldsList?.fieldNames()?.forEach { name -> fields.add(
                EndpointField().fromJson(name,fieldsList.getJsonObject(name))
        )}

        return this
    }

    protected fun validate(){

    }
}