package org.synbiohub.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EvaluatePostRequest
 */

@JsonTypeName("_evaluate_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-17T15:15:48.695589-07:00[America/Denver]", comments = "Generator version: 7.20.0")
public class EvaluatePostRequest {

  private @Nullable String type;

  private @Nullable String url;

  private @Nullable String filename;

  public EvaluatePostRequest type(@Nullable String type) {
    this.type = type;
    return this;
  }

  /**
   * The RDF type of the object (e.g., ComponentDefinition, Collection)
   * @return type
   */
  
  @Schema(name = "type", description = "The RDF type of the object (e.g., ComponentDefinition, Collection)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable String getType() {
    return type;
  }

  public void setType(@Nullable String type) {
    this.type = type;
  }

  public EvaluatePostRequest url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * The URL of the object in SynBioHub
   * @return url
   */
  
  @Schema(name = "url", description = "The URL of the object in SynBioHub", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public EvaluatePostRequest filename(@Nullable String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * Name of the file (for submission plugins)
   * @return filename
   */
  
  @Schema(name = "filename", description = "Name of the file (for submission plugins)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filename")
  public @Nullable String getFilename() {
    return filename;
  }

  public void setFilename(@Nullable String filename) {
    this.filename = filename;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EvaluatePostRequest evaluatePostRequest = (EvaluatePostRequest) o;
    return Objects.equals(this.type, evaluatePostRequest.type) &&
        Objects.equals(this.url, evaluatePostRequest.url) &&
        Objects.equals(this.filename, evaluatePostRequest.filename);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, url, filename);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EvaluatePostRequest {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(@Nullable Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

