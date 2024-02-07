package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDTo;

import java.util.List;

public interface AccountService {
   AccountDTo createAccount(AccountDTo accountDto);

   AccountDTo getAccountById(Long id);

   AccountDTo deposit(Long id, double amount);

   AccountDTo withdraw(Long id, double amount);

   List<AccountDTo> getAllAccount();

   void deleteAccount(Long id);
}
