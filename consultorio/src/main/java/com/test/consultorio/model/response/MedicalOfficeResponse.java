package com.test.consultorio.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalOfficeResponse {
    private Integer id;
    private Integer number;
    private Integer floor;
}
