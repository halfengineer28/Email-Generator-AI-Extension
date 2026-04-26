package com.email.emailwriter.dto;

import lombok.Data;

@Data
public class EmailRequestDTO {
    private String emailContent;
    private String tone;
}
