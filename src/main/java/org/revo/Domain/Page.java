package org.revo.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ashraf on 31/01/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private int number = 0;
    private int size = 10;
}