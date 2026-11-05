package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequest(@NotBlank String title,@Size(max=200) @NotBlank String author) {
}
