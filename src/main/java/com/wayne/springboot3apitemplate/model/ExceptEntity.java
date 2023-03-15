package com.wayne.springboot3apitemplate.model;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ExceptEntity {
    String message;
    Date time;
    long status;
}
