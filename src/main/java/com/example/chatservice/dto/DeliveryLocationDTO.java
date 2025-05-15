package com.example.chatservice.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryLocationDTO {
    private String merchantUid;
    private Integer riderUserUid;
    private Integer riderSocialUid;
    private double lat;
    private double lng;
}
