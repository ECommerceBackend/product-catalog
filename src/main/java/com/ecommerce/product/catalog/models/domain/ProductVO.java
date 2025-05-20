package com.ecommerce.product.catalog.models.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProductVO {

    @NotBlank
    @Size(min = 3, max = 100, message = "Name of the product must be between 3 to 100 characters long")
    @JsonProperty("product_name")
    private String productName;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    @JsonProperty("description")
    private String description;

    @NotNull
    @Min(value = 0, message = "Price cannot be less than 0")
    @Digits(integer = 8, fraction = 2, message = "precision cant be more than 2")
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Stock cannot be less than 0")
    @JsonProperty("stock_quantity")
    private Integer stockQuantity;

    @Size(max = 50, message = "Category cannot be longer than 50 characters")
    @JsonProperty("category")
    private String category;

    @URL
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("status")
    private Status status = Status.ACTIVE;

    @JsonProperty("created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    private Date updatedDate;
}
