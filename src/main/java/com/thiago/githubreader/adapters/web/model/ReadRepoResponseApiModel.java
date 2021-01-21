package com.thiago.githubreader.adapters.web.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.thiago.githubreader.adapters.web.model.ReadRepoGroupApiModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReadRepoResponseApiModel
 */

public class ReadRepoResponseApiModel   {
  @JsonProperty("totalLines")
  private BigDecimal totalLines;

  @JsonProperty("totalSize")
  private String totalSize;

  @JsonProperty("fileGroups")
  @Valid
  private List<ReadRepoGroupApiModel> fileGroups = new ArrayList<>();

  public ReadRepoResponseApiModel totalLines(BigDecimal totalLines) {
    this.totalLines = totalLines;
    return this;
  }

  /**
   * Get totalLines
   * @return totalLines
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getTotalLines() {
    return totalLines;
  }

  public void setTotalLines(BigDecimal totalLines) {
    this.totalLines = totalLines;
  }

  public ReadRepoResponseApiModel totalSize(String totalSize) {
    this.totalSize = totalSize;
    return this;
  }

  /**
   * Get totalSize
   * @return totalSize
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(String totalSize) {
    this.totalSize = totalSize;
  }

  public ReadRepoResponseApiModel fileGroups(List<ReadRepoGroupApiModel> fileGroups) {
    this.fileGroups = fileGroups;
    return this;
  }

  public ReadRepoResponseApiModel addFileGroupsItem(ReadRepoGroupApiModel fileGroupsItem) {
    this.fileGroups.add(fileGroupsItem);
    return this;
  }

  /**
   * Get fileGroups
   * @return fileGroups
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ReadRepoGroupApiModel> getFileGroups() {
    return fileGroups;
  }

  public void setFileGroups(List<ReadRepoGroupApiModel> fileGroups) {
    this.fileGroups = fileGroups;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReadRepoResponseApiModel readRepoResponse = (ReadRepoResponseApiModel) o;
    return Objects.equals(this.totalLines, readRepoResponse.totalLines) &&
        Objects.equals(this.totalSize, readRepoResponse.totalSize) &&
        Objects.equals(this.fileGroups, readRepoResponse.fileGroups);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalLines, totalSize, fileGroups);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReadRepoResponseApiModel {\n");
    
    sb.append("    totalLines: ").append(toIndentedString(totalLines)).append("\n");
    sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
    sb.append("    fileGroups: ").append(toIndentedString(fileGroups)).append("\n");
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

