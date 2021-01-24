package com.thiago.githubreader.adapters.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * ReadRepoFileApiModel
 */

public class ReadRepoFileApiModel {
    @JsonProperty("name")
    private String name;

    @JsonProperty("lineCount")
    private BigDecimal lineCount;

    @JsonProperty("size")
    private String size;

    public ReadRepoFileApiModel name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReadRepoFileApiModel lineCount(BigDecimal lineCount) {
        this.lineCount = lineCount;
        return this;
    }

    /**
     * Get lineCount
     *
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

    public ReadRepoFileApiModel size(String size) {
        this.size = size;
        return this;
    }

    /**
     * Get size
     *
     * @return size
     */
    @ApiModelProperty(required = true, value = "")
    @NotNull


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReadRepoFileApiModel readRepoFile = (ReadRepoFileApiModel) o;
        return Objects.equals(this.name, readRepoFile.name) &&
                Objects.equals(this.lineCount, readRepoFile.lineCount) &&
                Objects.equals(this.size, readRepoFile.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lineCount, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ReadRepoFileApiModel {\n");

        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    lineCount: ").append(toIndentedString(lineCount)).append("\n");
        sb.append("    size: ").append(toIndentedString(size)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

