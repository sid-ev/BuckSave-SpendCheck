package com.expensetracker.BuckSave.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String email;

    private String password;
}