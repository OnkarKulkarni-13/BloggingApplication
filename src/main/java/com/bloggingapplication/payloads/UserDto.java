package com.bloggingapplication.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
	private int id;

	@NotEmpty(message = "User name should not be empty")
	@NotNull(message = "User name should not be Null")
	private String name;

	@Email(message = "Email I'd is not valid")
	private String email;

	@Size(min = 4, max = 10)
	private String password;

	@NotBlank(message = "About should not be empty")
	private String about;

}
