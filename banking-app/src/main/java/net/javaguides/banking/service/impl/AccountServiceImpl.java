package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDTo;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
   private AccountRepository accountRepository;

   public AccountServiceImpl(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
   }

   @Override
   public AccountDTo createAccount(AccountDTo accountDto) {
      Account account = AccountMapper.mapToAccount(accountDto);
      Account savedAccount = accountRepository.save(account);
      return AccountMapper.mapToAccountDto(savedAccount);
   }

   @Override
   public AccountDTo getAccountById(Long id) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Account does not exists"));
      return AccountMapper.mapToAccountDto(account);
   }

   @Override
   public AccountDTo deposit(Long id, double amount) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Account does not exists"));

      double total = account.getBalance() + amount;
      account.setBalance(total);
      Account savedAccount = accountRepository.save(account);
      return AccountMapper.mapToAccountDto(savedAccount);
   }

   @Override
   public AccountDTo withdraw(Long id, double amount) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Account does not exists"));

      if (account.getBalance() < amount) {
         throw new RuntimeException("Insufficient amount");
      }

      double total = account.getBalance() - amount;
      account.setBalance(total);
      Account savedAccount = accountRepository.save(account);
      return AccountMapper.mapToAccountDto(savedAccount);
   }

   @Override
   public List<AccountDTo> getAllAccount() {
      List<Account> accounts = accountRepository.findAll();
      return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
   }

   @Override
   public void deleteAccount(Long id) {
      Account account = accountRepository
              .findById(id)
              .orElseThrow(() -> new RuntimeException("Account does not exists"));

      accountRepository.deleteById(id);
   }
}
