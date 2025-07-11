package com.ve.task_management.payload.response;

import lombok.Data;

@Data
public class ClientResponse {

    private Integer clientId;

    private String clientName;

    private String industry;

    private String gstNumber;

    private String address;

    private String city;

    private String contactPerson;

    private String contactNumber;

    private String contactEmail;

    private String remarks;
}
