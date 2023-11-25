package com.example.productmanagement.exceptionModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionBody {
    private List<String> globalExceptions;
    private List<FieldException> fieldExceptions;
}
