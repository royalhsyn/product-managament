package com.example.productmanagement.baseResponse;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String message;
    private HttpStatus status;

    private T data;

    public static  <T> BaseResponse<T>  succes(T data){

        return  BaseResponse.<T>builder().message("Succes").data(data).status(HttpStatus.OK).build();
    }

}
