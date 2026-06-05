package com.size.springboot3.param.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author linsz
 * @version v1.0
 * @date 2026/6/6 00:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginWithTypeDto {

    @NotNull(message = "用户ID不能为空")
    private Long loginId;

    @NotBlank(message = "用户名不能为空")
    private String loginType;
}
