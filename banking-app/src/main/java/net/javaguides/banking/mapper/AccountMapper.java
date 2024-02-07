package net.javaguides.banking.mapper;

import net.javaguides.banking.dto.AccountDTo;
import net.javaguides.banking.entity.Account;

public class AccountMapper {
   public static Account mapToAccount(AccountDTo accountDTo) {
      Account account = new Account(
              accountDTo.getId(),
              accountDTo.getAccountHolderName(),
              accountDTo.getBalance()
      );

      return account;

   }

   public static AccountDTo mapToAccountDto(Account account) {
      AccountDTo accountDTo = new AccountDTo(
              account.getId(),
              account.getAccountHolderName(),
              account.getBalance()
      );
      return accountDTo;
   }
}
