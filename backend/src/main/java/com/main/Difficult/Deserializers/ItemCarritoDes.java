package com.main.Difficult.Deserializers;

import javax.validation.constraints.Positive;

public class ItemCarritoDes {
    public String productoId;
    @Positive public Long loteId;
    @Positive public Integer cantidad;

}
