package com.bloggingapplication.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDto {
	private Integer categoryId;

	@NotEmpty(message = "CategoryTitle should not be empty")
	@NotNull(message = "CategoryTitle should not be Null")
	private String categoryTitle;

	@NotEmpty(message = "CategoryDescription should not be empty")
	@NotNull(message = "categoryDescription should not be Null")
	private String categoryDescription;

}
