package org.synbiohub.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
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
 * Payload passed through from SynBioHub dispatcher for run. The dispatcher enriches URI-based inputs with generated links. 
 */

@Schema(name = "RunRequest", description = "Payload passed through from SynBioHub dispatcher for run. The dispatcher enriches URI-based inputs with generated links. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-17T15:37:21.401452-07:00[America/Denver]", comments = "Generator version: 7.20.0")
public class RunRequest {

  private @Nullable String url;

  private @Nullable String completeSbol;

  private @Nullable String shallowSbol;

  private @Nullable String genbank;

  private @Nullable String topLevel;

  @Valid
  private Map<String, Object> params = new HashMap<>();

  public RunRequest url(@Nullable String url) {
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

  public RunRequest completeSbol(@Nullable String completeSbol) {
    this.completeSbol = completeSbol;
    return this;
  }

  /**
   * URL to fetch complete SBOL.
   * @return completeSbol
   */
  
  @Schema(name = "complete_sbol", description = "URL to fetch complete SBOL.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("complete_sbol")
  public @Nullable String getCompleteSbol() {
    return completeSbol;
  }

  public void setCompleteSbol(@Nullable String completeSbol) {
    this.completeSbol = completeSbol;
  }

  public RunRequest shallowSbol(@Nullable String shallowSbol) {
    this.shallowSbol = shallowSbol;
    return this;
  }

  /**
   * URL to fetch non-recursive SBOL.
   * @return shallowSbol
   */
  
  @Schema(name = "shallow_sbol", description = "URL to fetch non-recursive SBOL.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("shallow_sbol")
  public @Nullable String getShallowSbol() {
    return shallowSbol;
  }

  public void setShallowSbol(@Nullable String shallowSbol) {
    this.shallowSbol = shallowSbol;
  }

  public RunRequest genbank(@Nullable String genbank) {
    this.genbank = genbank;
    return this;
  }

  /**
   * URL to fetch GenBank representation.
   * @return genbank
   */
  
  @Schema(name = "genbank", description = "URL to fetch GenBank representation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("genbank")
  public @Nullable String getGenbank() {
    return genbank;
  }

  public void setGenbank(@Nullable String genbank) {
    this.genbank = genbank;
  }

  public RunRequest topLevel(@Nullable String topLevel) {
    this.topLevel = topLevel;
    return this;
  }

  /**
   * Top-level object identifier.
   * @return topLevel
   */
  
  @Schema(name = "top_level", description = "Top-level object identifier.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("top_level")
  public @Nullable String getTopLevel() {
    return topLevel;
  }

  public void setTopLevel(@Nullable String topLevel) {
    this.topLevel = topLevel;
  }

  public RunRequest params(Map<String, Object> params) {
    this.params = params;
    return this;
  }

  public RunRequest putParamsItem(String key, Object paramsItem) {
    if (this.params == null) {
      this.params = new HashMap<>();
    }
    this.params.put(key, paramsItem);
    return this;
  }

  /**
   * Additional plugin parameters.
   * @return params
   */
  
  @Schema(name = "params", description = "Additional plugin parameters.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("params")
  public Map<String, Object> getParams() {
    return params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
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
    public RunRequest putAdditionalProperty(String key, Object value) {
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
    RunRequest runRequest = (RunRequest) o;
    return Objects.equals(this.url, runRequest.url) &&
        Objects.equals(this.completeSbol, runRequest.completeSbol) &&
        Objects.equals(this.shallowSbol, runRequest.shallowSbol) &&
        Objects.equals(this.genbank, runRequest.genbank) &&
        Objects.equals(this.topLevel, runRequest.topLevel) &&
        Objects.equals(this.params, runRequest.params) &&
    Objects.equals(this.additionalProperties, runRequest.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, completeSbol, shallowSbol, genbank, topLevel, params, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RunRequest {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    completeSbol: ").append(toIndentedString(completeSbol)).append("\n");
    sb.append("    shallowSbol: ").append(toIndentedString(shallowSbol)).append("\n");
    sb.append("    genbank: ").append(toIndentedString(genbank)).append("\n");
    sb.append("    topLevel: ").append(toIndentedString(topLevel)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
    
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

