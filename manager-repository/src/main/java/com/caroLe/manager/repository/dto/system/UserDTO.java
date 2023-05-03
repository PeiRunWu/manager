package com.caroLe.manager.repository.dto.system;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/22 16:15
 * @Description
 */
@Data
public class UserDTO {

    private String id;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("client_id")
    private String clientId;

    private List<String> authorities;

}
