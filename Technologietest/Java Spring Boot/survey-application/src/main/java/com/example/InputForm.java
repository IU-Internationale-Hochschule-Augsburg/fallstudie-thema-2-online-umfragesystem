package com.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InputForm {
    private List<TextInput> textInputs = new ArrayList<>();
}
