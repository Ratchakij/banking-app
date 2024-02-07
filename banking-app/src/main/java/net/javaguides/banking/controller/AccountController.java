package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDTo;
import net.javaguides.banking.service.AccountService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
   private AccountService accountService;

   public AccountController(AccountService accountService) {
      this.accountService = accountService;
   }

   // Add Account REST API
   @PostMapping
   public ResponseEntity<AccountDTo> addAccount(@RequestBody AccountDTo accountDTo) {
      return new ResponseEntity<>(accountService.createAccount(accountDTo), HttpStatus.CREATED);

   }

   // Get Account REST API
   @GetMapping("/{id}")
   public ResponseEntity<AccountDTo> getAccountById(@PathVariable Long id) {
      AccountDTo accountDTo = accountService.getAccountById(id);
      return ResponseEntity.ok(accountDTo);
   }

   // Deposit REST API
   @PutMapping("/{id}/deposit")
   public ResponseEntity<AccountDTo> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
      Double amount = request.get("amount");
      AccountDTo accountDTo = accountService.deposit(id, amount);
      return ResponseEntity.ok(accountDTo);
   }

   // Withdraw REST API
   @PutMapping("/{id}/withdraw")
   public ResponseEntity<AccountDTo> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
      double amount = request.get("amount");
      AccountDTo accountDTo = accountService.withdraw(id, amount);
      return ResponseEntity.ok(accountDTo);
   }

   // Get All Accounts REST API
   @GetMapping
   public ResponseEntity<List<AccountDTo>> getAllAccounts() {
      List<AccountDTo> accounts = accountService.getAllAccount();
      return ResponseEntity.ok(accounts);
   }

   // Delete Account REST API
   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
      accountService.deleteAccount(id);
      return ResponseEntity.ok("Account is deleted successfully!");
   }
}
