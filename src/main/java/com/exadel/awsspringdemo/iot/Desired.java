package com.exadel.awsspringdemo.iot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Desired {
    private Integer targetTemperature;
}
