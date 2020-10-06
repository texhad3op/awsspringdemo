package com.exadel.awsspringdemo.iot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reported {
    private Integer currentTemperature;
}
