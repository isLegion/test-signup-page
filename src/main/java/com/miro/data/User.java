package com.miro.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    public String name;
    public String email;
    public String password;
}
