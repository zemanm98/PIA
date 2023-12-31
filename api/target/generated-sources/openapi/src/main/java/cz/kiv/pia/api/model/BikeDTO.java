package cz.kiv.pia.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.kiv.pia.api.model.LocationDTO;
import cz.kiv.pia.api.model.StandDTO;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BikeDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-06T15:22:57.561711400+01:00[Europe/Prague]")
public class BikeDTO {

  private Integer id;

  private LocationDTO location;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate lastServiceStamp;

  private StandDTO stand;

  public BikeDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BikeDTO(Integer id, LocationDTO location) {
    this.id = id;
    this.location = location;
  }

  public BikeDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BikeDTO location(LocationDTO location) {
    this.location = location;
    return this;
  }

  /**
   * Get location
   * @return location
  */
  @NotNull @Valid 
  @Schema(name = "location", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("location")
  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO location) {
    this.location = location;
  }

  public BikeDTO lastServiceStamp(LocalDate lastServiceStamp) {
    this.lastServiceStamp = lastServiceStamp;
    return this;
  }

  /**
   * Get lastServiceStamp
   * @return lastServiceStamp
  */
  @Valid 
  @Schema(name = "lastServiceStamp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastServiceStamp")
  public LocalDate getLastServiceStamp() {
    return lastServiceStamp;
  }

  public void setLastServiceStamp(LocalDate lastServiceStamp) {
    this.lastServiceStamp = lastServiceStamp;
  }

  public BikeDTO stand(StandDTO stand) {
    this.stand = stand;
    return this;
  }

  /**
   * Get stand
   * @return stand
  */
  @Valid 
  @Schema(name = "stand", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stand")
  public StandDTO getStand() {
    return stand;
  }

  public void setStand(StandDTO stand) {
    this.stand = stand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BikeDTO bikeDTO = (BikeDTO) o;
    return Objects.equals(this.id, bikeDTO.id) &&
        Objects.equals(this.location, bikeDTO.location) &&
        Objects.equals(this.lastServiceStamp, bikeDTO.lastServiceStamp) &&
        Objects.equals(this.stand, bikeDTO.stand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, location, lastServiceStamp, stand);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BikeDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    lastServiceStamp: ").append(toIndentedString(lastServiceStamp)).append("\n");
    sb.append("    stand: ").append(toIndentedString(stand)).append("\n");
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

