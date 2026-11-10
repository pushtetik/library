package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(@NotBlank String title,@Size(max=200) @NotBlank String author, @NotBlank @Size(max=20) String isbn) {
}
