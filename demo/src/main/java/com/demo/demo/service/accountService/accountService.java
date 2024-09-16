package com.demo.demo.service.accountService;


import com.demo.demo.responseStatus.PageResponse;
import com.demo.demo.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

public interface accountService {
    UserDetailsService getUserDetailsService();

    public void saveAccount(Account account);

    public List<Account> listAccount();

    public Account getAccountDetail(int id) throws AccountNotFoundException;

    public void deleteAccount(int id);

    public Account findByName(String name);


    public List<Account> getAllByEmail(String email,int pageNo, int pageSize);

    public void updateAccount(Account account, int id);

    public PageResponse<?> listAccountByPaging(int page,int size, String sortBy);

    public Optional<Account> getAccountById(int id);


}
