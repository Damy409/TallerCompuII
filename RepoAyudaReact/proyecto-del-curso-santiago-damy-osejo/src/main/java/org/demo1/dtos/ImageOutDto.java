package org.demo1.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageOutDto {
    private Long id;
    private String name;
    private String type;
    private byte[] data;

}
