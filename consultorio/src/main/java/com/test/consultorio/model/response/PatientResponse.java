package com.test.consultorio.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientResponse {
    private Integer id;
    private String name;
    private String lastName;
    private String secondLastName;
}
