package br.com.neuberoliveira.polaris.Model

import io.vertx.core.json.JsonObject
import org.jetbrains.annotations.Nullable

class EndpointField {
    var name = ""
    var type = "string"

    fun fromJson(name:String, json:JsonObject?):EndpointField {
        this.name = name
        this.type = if (json!==null) json.getString("type") else "string"

        return this
    }
}