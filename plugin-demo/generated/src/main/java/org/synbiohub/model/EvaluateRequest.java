package org.synbiohub.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
/**
 * Payload passed through from SynBioHub dispatcher for evaluate. Shape varies by plugin category. 
 */

@Schema(name = "EvaluateRequest", description = "Payload passed through from SynBioHub dispatcher for evaluate. Shape varies by plugin category. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-17T15:37:21.401452-07:00[America/Denver]", comments = "Generator version: 7.20.0")
public class EvaluateRequest {

  private @Nullable String type;

  private @Nullable String url;

  private @Nullable String filename;

  public EvaluateRequest type(@Nullable String type) {
    this.type = type;
    return this;
  }

  /**
   * RDF type of the object.
   * @return type
   */
  
  @Schema(name = "type", description = "RDF type of the object.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable String getType() {
    return type;
  }

  public void setType(@Nullable String type) {
    this.type = type;
  }

  public EvaluateRequest url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * Object URL in SynBioHub.
   * @return url
   */
  
  @Schema(name = "url", description = "Object URL in SynBioHub.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public EvaluateRequest filename(@Nullable String filename) {
    this.filename = filename;
    return this;
  }

  /**
   * File name for submission workflows.
   * @return filename
   */
  
  @Schema(name = "filename", description = "File name for submission workflows.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("filename")
  public @Nullable String getFilename() {
    return filename;
  }

  public void setFilename(@Nullable String filename) {
    this.filename = filename;
  }
    /**
    * A container for additional, undeclared properties.
    * This is a holder for any undeclared properties as specified with
    * the 'additionalProperties' keyword in the OAS document.
    */
    private Map<String, Object> additionalProperties;

    /**
    * Set the additional (undeclared) property with the specified name and value.
    * If the property does not already exist, create it otherwise replace it.
    */
    @JsonAnySetter
    public EvaluateRequest putAdditionalProperty(String key, Object value) {
        if (this.additionalProperties == null) {
            this.additionalProperties = new HashMap<String, Object>();
        }
        this.additionalProperties.put(key, value);
        return this;
    }

    /**
    * Return the additional (undeclared) property.
    */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    /**
    * Return the additional (undeclared) property with the specified name.
    */
    public Object getAdditionalProperty(String key) {
        if (this.additionalProperties == null) {
            return null;
        }
        return this.additionalProperties.get(key);
    }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EvaluateRequest evaluateRequest = (EvaluateRequest) o;
    return Objects.equals(this.type, evaluateRequest.type) &&
        Objects.equals(this.url, evaluateRequest.url) &&
        Objects.equals(this.filename, evaluateRequest.filename) &&
    Objects.equals(this.additionalProperties, evaluateRequest.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, url, filename, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EvaluateRequest {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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

