package sttp.tapir.docs.openapi

import sttp.tapir.docs.openapi.schema.SchemaKey
import sttp.tapir.openapi.OpenAPI.ReferenceOr
import sttp.tapir.openapi.{Schema => OSchema, _}

import scala.collection.immutable.ListMap

private[openapi] class EndpointToOpenApiComponents(
    keyToSchema: ListMap[SchemaKey, ReferenceOr[OSchema]],
    securitySchemes: SecuritySchemes
) {
  def components: Option[Components] = {
    if (keyToSchema.nonEmpty || securitySchemes.nonEmpty)
      Some(Components(keyToSchema, securitySchemes.values.toMap.mapValues(Right(_)).toListMap))
    else None
  }
}
