2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-api/target/generated-sources/com/upgrad/musichoster/api/model/UpdateMusicRequest.java
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
 * UpdateMusicRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-05T00:37:33.768+05:30")

public class UpdateMusicRequest   {
  @JsonProperty("music")
  private String music = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("status")
  private String status = null;

  public UpdateMusicRequest music(String music) {
    this.music = music;
    return this;
  }

  /**
   * This is music in Base64 format
   * @return music
  **/
  @ApiModelProperty(value = "This is music in Base64 format")


  public String getMusic() {
    return music;
  }

  public void setMusic(String music) {
    this.music = music;
  }

  public UpdateMusicRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the music
   * @return name
  **/
  @ApiModelProperty(value = "Name of the music")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UpdateMusicRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the music
   * @return description
  **/
  @ApiModelProperty(value = "Description of the music")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UpdateMusicRequest status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Status of the music
   * @return status
  **/
  @ApiModelProperty(value = "Status of the music")


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
    UpdateMusicRequest updateMusicRequest = (UpdateMusicRequest) o;
    return Objects.equals(this.music, updateMusicRequest.music) &&
        Objects.equals(this.name, updateMusicRequest.name) &&
        Objects.equals(this.description, updateMusicRequest.description) &&
        Objects.equals(this.status, updateMusicRequest.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(music, name, description, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdateMusicRequest {\n");
    
    sb.append("    music: ").append(toIndentedString(music)).append("\n");
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

