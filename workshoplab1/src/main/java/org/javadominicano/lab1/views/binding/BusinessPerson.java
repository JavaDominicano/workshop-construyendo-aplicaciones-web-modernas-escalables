package org.javadominicano.lab1.views.binding;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author me@fredpena.dev
 * @created 24/11/2023  - 18:41
 */
@Getter
@Setter
public class BusinessPerson implements Serializable {
    private String name;
    private String title;
    private int age;

    public BusinessPerson() {
    }

}
