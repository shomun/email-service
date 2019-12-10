package com.monirul.test.emailservice.data;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class EmailData {

    @NotNull(message = "Email is required")
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*", message = "Email has invalid characters. Multiple emails should be comma separated.")
    private String tos;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*", message = "CC Email has invalid characters. Multiple emails should be comma separated.")
    private String ccs;

    @Pattern(regexp = "\\w+@\\w+\\.\\w+(,\\s*\\w+@\\w+\\.\\w+)*", message = "BCC Email has invalid characters. Multiple emails should be comma separated.")
    String bccs;

    @NotNull(message = "Sender's Email is required")
    @NotBlank(message = "Sender's Email is required")
    @Pattern(regexp = "\\w+@\\w+\\.\\w+", message = "Sender's Email has invalid characters.")

    private String sender;
    @NotNull(message = "Subject is required")
    @NotBlank(message = "Subject is required")
    @Size(max = 50, message = "Subject value is too long, max limit 50 characters.")
    private String subject;

    @Size(max = 500, message = "Email Body value is too long, max limit 500 characters.")
    private String body;
}
