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
 * RunPostRequest
 */

@JsonTypeName("_run_post_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-17T15:15:48.695589-07:00[America/Denver]", comments = "Generator version: 7.20.0")
public class RunPostRequest {

  private @Nullable String url;

  private @Nullable String completeSbol;

  private @Nullable Object params;

  public RunPostRequest url(@Nullable String url) {
    this.url = url;
    return this;
  }

  /**
   * The URL of the object
   * @return url
   */
  
  @Schema(name = "url", description = "The URL of the object", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("url")
  public @Nullable String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  public RunPostRequest completeSbol(@Nullable String completeSbol) {
    this.completeSbol = completeSbol;
    return this;
  }

  /**
   * URL to fetch the complete SBOL data
   * @return completeSbol
   */
  
  @Schema(name = "complete_sbol", description = "URL to fetch the complete SBOL data", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("complete_sbol")
  public @Nullable String getCompleteSbol() {
    return completeSbol;
  }

  public void setCompleteSbol(@Nullable String completeSbol) {
    this.completeSbol = completeSbol;
  }

  public RunPostRequest params(@Nullable Object params) {
    this.params = params;
    return this;
  }

  /**
   * Any additional parameters passed from the frontend
   * @return params
   */
  
  @Schema(name = "params", description = "Any additional parameters passed from the frontend", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("params")
  public @Nullable Object getParams() {
    return params;
  }

  public void setParams(@Nullable Object params) {
    this.params = params;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RunPostRequest runPostRequest = (RunPostRequest) o;
    return Objects.equals(this.url, runPostRequest.url) &&
        Objects.equals(this.completeSbol, runPostRequest.completeSbol) &&
        Objects.equals(this.params, runPostRequest.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, completeSbol, params);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunPostRequest {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    completeSbol: ").append(toIndentedString(completeSbol)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
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

