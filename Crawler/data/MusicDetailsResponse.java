2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-api/target/generated-sources/com/upgrad/musichoster/api/model/MusicDetailsResponse.java
package com.upgrad.musichoster.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MusicDetailsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-05T00:37:33.768+05:30")

public class MusicDetailsResponse   {
  @JsonProperty("music")
  private String music = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private String status = null;

  public MusicDetailsResponse music(String music) {
    this.music = music;
    return this;
  }

  /**
   * This is music in Base64 format
   * @return music
  **/
  @ApiModelProperty(required = true, value = "This is music in Base64 format")
  @NotNull


  public String getMusic() {
    return music;
  }

  public void setMusic(String music) {
    this.music = music;
  }

  public MusicDetailsResponse id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the user in a standard UUID format
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Unique identifier of the user in a standard UUID format")
  @NotNull


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public MusicDetailsResponse name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the music
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the music")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MusicDetailsResponse description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the music
   * @return description
  **/
  @ApiModelProperty(required = true, value = "Description of the music")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MusicDetailsResponse status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Music Active Status
   * @return status
  **/
  @ApiModelProperty(required = true, value = "Music Active Status")
  @NotNull


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicDetailsResponse musicDetailsResponse = (MusicDetailsResponse) o;
    return Objects.equals(this.music, musicDetailsResponse.music) &&
        Objects.equals(this.id, musicDetailsResponse.id) &&
        Objects.equals(this.name, musicDetailsResponse.name) &&
        Objects.equals(this.description, musicDetailsResponse.description) &&
        Objects.equals(this.status, musicDetailsResponse.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(music, id, name, description, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MusicDetailsResponse {\n");
    
    sb.append("    music: ").append(toIndentedString(music)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

