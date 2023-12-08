package cz.kiv.pia.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import cz.kiv.pia.api.model.BikeDTO;
import cz.kiv.pia.api.model.LocationDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * StandDTO
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-12-08T14:02:30.270309700+01:00[Europe/Prague]")
public class StandDTO {

  private Integer id;

  private String name;

  private LocationDTO location;

  @Valid
  private List<@Valid BikeDTO> bikes;

  public StandDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public StandDTO(Integer id, String name, LocationDTO location) {
    this.id = id;
    this.name = name;
    this.location = location;
  }

  public StandDTO id(Integer id) {
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

  public StandDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StandDTO location(LocationDTO location) {
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

  public StandDTO bikes(List<@Valid BikeDTO> bikes) {
    this.bikes = bikes;
    return this;
  }

  public StandDTO addBikesItem(BikeDTO bikesItem) {
    if (this.bikes == null) {
      this.bikes = new ArrayList<>();
    }
    this.bikes.add(bikesItem);
    return this;
  }

  /**
   * Get bikes
   * @return bikes
  */
  @Valid 
  @Schema(name = "bikes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bikes")
  public List<@Valid BikeDTO> getBikes() {
    return bikes;
  }

  public void setBikes(List<@Valid BikeDTO> bikes) {
    this.bikes = bikes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StandDTO standDTO = (StandDTO) o;
    return Objects.equals(this.id, standDTO.id) &&
        Objects.equals(this.name, standDTO.name) &&
        Objects.equals(this.location, standDTO.location) &&
        Objects.equals(this.bikes, standDTO.bikes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, location, bikes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StandDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
    sb.append("    bikes: ").append(toIndentedString(bikes)).append("\n");
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

