package com.moldedbits.genesis.models;

import com.moldedbits.genesis.models.response.BaseResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by abhishek
 * on 08/04/16.
 */
public class ApiError {

    @Getter
    @Setter
    String errors;

    @Getter
    @Setter
    BaseResponse.Status success;
}
