package com.ve.task_management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_Id")
    private Integer clientId;

    @Column(name="client_name")
    private String clientName;

    @Column(name="industry")
    private String industry;

    @Column(name="gst_number")
    private String gstNumber;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="contact_person")
    private String contactPerson;

    @Column(name="contact_number")
    private String contactNumber;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="remarks")
    private String remarks;

    @CreatedDate
    @Column(name="created_at",updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(name="deleted",nullable = false)
    private boolean deleted = false;

}
