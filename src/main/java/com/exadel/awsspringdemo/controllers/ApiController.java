package com.exadel.awsspringdemo.controllers;

import com.amazonaws.services.iotdata.AWSIotData;
import com.amazonaws.services.iotdata.model.GetThingShadowRequest;
import com.amazonaws.services.iotdata.model.GetThingShadowResult;
import com.amazonaws.services.iotdata.model.UpdateThingShadowRequest;
import com.exadel.awsspringdemo.dto.SqsMessageData;
import com.exadel.awsspringdemo.dto.TargetTemperatureRequest;
import com.exadel.awsspringdemo.iot.ShadowDeviceData;
import com.exadel.awsspringdemo.services.ActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class ApiController {

    @Value("${aws.iot.device.name}")
    private String deviceName;

    @Autowired
    private AWSIotData awsIotDataClient;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ActionService actionService;


    @GetMapping("/api/messages")
    public List<SqsMessageData> getMessages() {
        return Arrays.asList(new SqsMessageData(UUID.randomUUID().toString(), "msgg1", LocalDateTime.now()),
                new SqsMessageData(UUID.randomUUID().toString(), "jcc", LocalDateTime.now().plusMinutes(5)),
                new SqsMessageData(UUID.randomUUID().toString(), "xfhxxux", LocalDateTime.now().plusMinutes(7)));
    }

    @PostMapping("/api/temperature/target")
    public void setTargetTemperature(@RequestBody TargetTemperatureRequest targetTemperatureRequest) throws JsonProcessingException {
        ShadowDeviceData data = new ShadowDeviceData();
        data.getState().getDesired().setTargetTemperature(targetTemperatureRequest.getTargetTemperature());
        awsIotDataClient.updateThingShadow(new UpdateThingShadowRequest()
                .withThingName(deviceName)
                .withPayload(ByteBuffer.wrap(mapper.writeValueAsString(data).getBytes())));
    }

    @GetMapping("/api/temperature/current")
    public Integer getCurrentTemperature() throws IOException {
        GetThingShadowResult getResult = awsIotDataClient.getThingShadow(new GetThingShadowRequest()
                .withThingName(deviceName));
        ShadowDeviceData shadowDeviceData = mapper.readValue(getResult.getPayload().array(), ShadowDeviceData.class);
        return shadowDeviceData.getState().getReported().getCurrentTemperature();
    }

    @GetMapping("/api/async")
    public String asyncMethodWithVoidReturnType() throws InterruptedException {
        String gg = actionService.asyn();
        System.out.println(gg);
        return "finish";
    }


}
