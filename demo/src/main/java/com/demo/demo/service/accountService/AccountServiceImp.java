package com.demo.demo.service.accountService;

import com.demo.demo.responseStatus.PageResponse;
import com.demo.demo.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.demo.repository.AccountRepo;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements accountService {

    private final AccountRepo accRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetailsService getUserDetailsService() {
        return email -> accRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Account not found"));
    }

    @Override
    public void saveAccount(Account account) {
        Account accountEncrypted = Account.builder()
                .email(account.getEmail())
                .phone(account.getPhone())
                .username(account.getUsername())
                .password(encoder.encode(account.getPassword()))
                .roleID(account.getRoleID())
                .build();
        accRepo.save(accountEncrypted);

    }

    @Override
    public List<Account> listAccount() {
        // TODO Auto-generated method stub
        return accRepo.findAll();
    }

    @Override
    public Account getAccountDetail(int id) throws AccountNotFoundException {

        Optional<Account> account = accRepo.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotFoundException("small test");
        }
        return account.get();
    }

    @Override
    public void deleteAccount(int id) {
        // TODO Auto-generated method stub
        accRepo.deleteById(id);
    }

    @Override
    public Account findByName(String name) {
        // TODO Auto-generated method stub

        return accRepo.findByEmail(name).orElseThrow(()-> new UsernameNotFoundException("Account not found"));
    }

    @Override
    public List<Account> getAllByEmail(String email, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return accRepo.findAll(pageable).stream().toList();

    }

    @Override
    public void updateAccount(Account account, int id) {
        Optional<Account> existingAccount = accRepo.findById(id);
        if (existingAccount.isPresent()) {

            existingAccount.get().setFullName(account.getFullName());
            existingAccount.get().setEmail(account.getEmail());
            existingAccount.get().setPassword(account.getPassword());
            accRepo.saveAndFlush(existingAccount.get());

        }
    }

    @Override
    public PageResponse<?> listAccountByPaging(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sortBy));

        Page<Account> account = accRepo.findAll(pageable);

        return new PageResponse<>(page, size, account.getTotalPages(), (int) account.getTotalElements(), account.stream().toList());
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return Optional.empty();
    }

    private Account getAccountByAccountId(int id) {

        return null;
    }
}