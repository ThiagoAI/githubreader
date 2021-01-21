package com.thiago.githubreader.adapters.web.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.thiago.githubreader.adapters.web.model.ReadRepoFileApiModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ReadRepoGroupApiModel
 */

public class ReadRepoGroupApiModel   {
  @JsonProperty("fileExtension")
  private String fileExtension;

  @JsonProperty("lineCount")
  private BigDecimal lineCount;

  @JsonProperty("totalSize")
  private String totalSize;

  @JsonProperty("files")
  @Valid
  private List<ReadRepoFileApiModel> files = new ArrayList<>();

  public ReadRepoGroupApiModel fileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
    return this;
  }

  /**
   * Get fileExtension
   * @return fileExtension
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getFileExtension() {
    return fileExtension;
  }

  public void setFileExtension(String fileExtension) {
    this.fileExtension = fileExtension;
  }

  public ReadRepoGroupApiModel lineCount(BigDecimal lineCount) {
    this.lineCount = lineCount;
    return this;
  }

  /**
   * Get lineCount
   * @return lineCount
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public BigDecimal getLineCount() {
    return lineCount;
  }

  public void setLineCount(BigDecimal lineCount) {
    this.lineCount = lineCount;
  }

  public ReadRepoGroupApiModel totalSize(String totalSize) {
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

  public ReadRepoGroupApiModel files(List<ReadRepoFileApiModel> files) {
    this.files = files;
    return this;
  }

  public ReadRepoGroupApiModel addFilesItem(ReadRepoFileApiModel filesItem) {
    this.files.add(filesItem);
    return this;
  }

  /**
   * Get files
   * @return files
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ReadRepoFileApiModel> getFiles() {
    return files;
  }

  public void setFiles(List<ReadRepoFileApiModel> files) {
    this.files = files;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReadRepoGroupApiModel readRepoGroup = (ReadRepoGroupApiModel) o;
    return Objects.equals(this.fileExtension, readRepoGroup.fileExtension) &&
        Objects.equals(this.lineCount, readRepoGroup.lineCount) &&
        Objects.equals(this.totalSize, readRepoGroup.totalSize) &&
        Objects.equals(this.files, readRepoGroup.files);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileExtension, lineCount, totalSize, files);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReadRepoGroupApiModel {\n");
    
    sb.append("    fileExtension: ").append(toIndentedString(fileExtension)).append("\n");
    sb.append("    lineCount: ").append(toIndentedString(lineCount)).append("\n");
    sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
    sb.append("    files: ").append(toIndentedString(files)).append("\n");
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

