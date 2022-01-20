package it.ddcompendium.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends Exception {
    private static final long serialVersionUID = 7762958786114062771L;

    private int code;

    public ServiceException(int i, String s) {
        super(s);
        this.code = i;
    }
}
