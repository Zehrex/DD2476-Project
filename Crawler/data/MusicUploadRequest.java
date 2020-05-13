2
https://raw.githubusercontent.com/aayush-grover/SoundCloud-Rest-Api/master/musichoster-api/target/generated-sources/com/upgrad/musichoster/api/model/MusicUploadRequest.java
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
 * MusicUploadRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-05T00:37:33.420+05:30")

public class MusicUploadRequest   {
  @JsonProperty("music")
  private String music = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  public MusicUploadRequest music(String music) {
    this.music = music;
    return this;
  }

  /**
   * The music in Base64 format
   * @return music
  **/
  @ApiModelProperty(required = true, value = "The music in Base64 format")
  @NotNull


  public String getMusic() {
    return music;
  }

  public void setMusic(String music) {
    this.music = music;
  }

  public MusicUploadRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the music
   * @return name
  **/
  @ApiModelProperty(required = true, value = "The name of the music")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MusicUploadRequest description(String description) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MusicUploadRequest musicUploadRequest = (MusicUploadRequest) o;
    return Objects.equals(this.music, musicUploadRequest.music) &&
        Objects.equals(this.name, musicUploadRequest.name) &&
        Objects.equals(this.description, musicUploadRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(music, name, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MusicUploadRequest {\n");
    
    sb.append("    music: ").append(toIndentedString(music)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

