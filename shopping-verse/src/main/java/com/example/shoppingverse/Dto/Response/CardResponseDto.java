package com.example.shoppingverse.Dto.Response;

import com.example.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {

    String customerName;

    String cardNo;

    Date validTill;

    CardType cardType;

}
