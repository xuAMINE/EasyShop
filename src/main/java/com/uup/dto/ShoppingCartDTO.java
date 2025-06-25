package com.uup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Map<Integer, ShoppingCartItemDTO> items;
    private BigDecimal total;
}
