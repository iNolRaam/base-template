package com.inolraam.basetemplate.usecase.user.dto;

import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.adapter.in.validation.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserInput implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Email(message = MessageCodes.EMAIL)
    @Size(max = 100, message = MessageCodes.SIZE)
    private String email;

    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Size(min = 3, max = 30, message = MessageCodes.SIZE)
    @Pattern(regexp = RegexPatterns.USERNAME_FORMAT, message = MessageCodes.REGEX_USERNAME_FORMAT)
    private String username;

    @NotBlank(message = MessageCodes.NOT_BLANK)
    @Size(max = 10, message = MessageCodes.SIZE)
    private String status;

    @NotNull(message = MessageCodes.NOT_NULL)
    private Set<Long> profiles;
}