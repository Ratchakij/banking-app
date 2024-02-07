package net.javaguides.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTo {
   private Long id;
   private String accountHolderName;
   private double balance;
}
