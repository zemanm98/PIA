package cz.kiv.pia.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LocationDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-06T15:22:57.561711400+01:00[Europe/Prague]")
public class LocationDTO {

  private Double longitude;

  private Double latitude;

  public LocationDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public LocationDTO(Double longitude, Double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public LocationDTO longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Get longitude
   * @return longitude
  */
  @NotNull 
  @Schema(name = "longitude", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("longitude")
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public LocationDTO latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Get latitude
   * @return latitude
  */
  @NotNull 
  @Schema(name = "latitude", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("latitude")
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationDTO locationDTO = (LocationDTO) o;
    return Objects.equals(this.longitude, locationDTO.longitude) &&
        Objects.equals(this.latitude, locationDTO.latitude);
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationDTO {\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

