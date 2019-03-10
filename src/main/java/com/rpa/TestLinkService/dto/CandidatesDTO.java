package com.rpa.TestLinkService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
public class CandidatesDTO {

    private String candidateName;

    private String emailId;

    private String contactNo;

    private String appliedJobProfile;

    private Integer experience;

    private String status;
}