package org.demo1.dtos;

import lombok.Data;

@Data
public class UserWithProfileDTO {
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String profileDescription;
    private String imageName;
    private String imageType;
    private byte[] imageData;
}

