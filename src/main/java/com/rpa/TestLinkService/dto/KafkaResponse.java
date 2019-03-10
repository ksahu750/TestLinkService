package com.rpa.TestLinkService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class KafkaResponse {

    private String email;

    private String result;

}
