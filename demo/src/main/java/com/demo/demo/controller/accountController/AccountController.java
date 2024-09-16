package com.demo.demo.controller.accountController;

import com.demo.demo.responseStatus.PageResponse;
import com.demo.demo.model.Category;
import com.demo.demo.repository.AccountRepo;
import com.demo.demo.repository.CategoryRepository;
import jakarta.validation.Valid;
import com.demo.demo.responseStatus.ResponseData;
import com.demo.demo.responseStatus.ResponseSuccess;
import com.demo.demo.service.accountService.accountService;
import com.demo.demo.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.Optional;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final accountService accountService;
    private final AccountRepo accountRepo;
    private final CategoryRepository cateRepo;

    public Long getAccountId() {
        // Lấy thông tin xác thực từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem thông tin người dùng có tồn tại không
        if (authentication != null && authentication.getPrincipal() instanceof Account) {
            Account accountDetails = (Account) authentication.getPrincipal();

            // Trả về ID của tài khoản
            return (long) accountDetails.getAccountID();

        }

        // Trường hợp không tìm thấy thông tin người dùng
        return null;
    }


    @PutMapping("/save")
    public ResponseData<Account> saveAccount(@RequestBody Account account) {
        accountService.saveAccount(account);
        log.info("Saved account {}", account);
        return new ResponseData<>(HttpStatus.OK.value(), "added");
    }

    @GetMapping("/accountList")
    public List<Account> listAccount() {
        log.info("account listed");
        log.info("list requested by {}", getAccountId());
        return accountService.listAccount();

    }


    @GetMapping("/{id}")
    public ResponseData<Account> getAccountDetail(@PathVariable("id") int id) {
        try {
            Optional<Account> account = getAccountById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "account detail", account.get());
        } catch (Exception e){
            log.error("error:{}",e.getMessage(),e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "error happened");
        }

    }

    @DeleteMapping("/accountDelete/{id}")
    public void deleteAccount(@PathVariable("id") int id) {
            accountService.deleteAccount(id);
    }

    @GetMapping("/account/name/{name}")
    public Account findByName(@PathVariable("name") String name) {

        return (accountService.findByName(name));
    }

    @GetMapping("email/{email}")
    public ResponseData<List<Account>> getAllByEmail(@PathVariable("email") String email,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize ) {

        List<Account> accounts = accountService.getAllByEmail(email,pageNo,pageSize);

        return new ResponseData<>(HttpStatus.OK.value(), "accounts", accounts);

    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Account account, @PathVariable int id) {
        accountService.updateAccount(account, id);
    }

    @GetMapping("/account/testResponse")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> listAccount = accountService.listAccount();
        return new ResponseEntity<>(listAccount, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/test2")
    public ResponseSuccess getAllAccount2() {
        List<Account> listAccount = accountService.listAccount();
        return new ResponseSuccess(HttpStatus.OK, "ok", listAccount);
    }

    @GetMapping("/test3")
    public ResponseData<List<Account>> getAllAccount3() {
        List<Account> listAccount = accountService.listAccount();
        return new ResponseData<>(HttpStatus.CONFLICT.value(), "loaded list", listAccount);
    }

    @GetMapping("/name/{name}")
    public ResponseData<Account> findByName2(@Valid @PathVariable("name") String name) {

        Account account = accountService.findByName(name);

        return new ResponseData<>(HttpStatus.OK.value(), "found", account);

    }

    @GetMapping("/test1")
    public ResponseData<List<Account>> getAllAccount1() {
        List<Account> listAccount = accountService.listAccount();
        return new ResponseData<>(HttpStatus.CONFLICT.value(), "loaded list", listAccount);
    }


    @GetMapping("/v1/{id}")
    public ResponseData<?> getListById(@PathVariable("id") String sortBy,@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize ) {
        try{
            PageResponse<List<Account>> page = (PageResponse<List<Account>>) accountService.listAccountByPaging(pageNo,pageSize,sortBy);

            return new ResponseData<>(HttpStatus.OK.value(), "accounts", page);
        }catch (Exception e){
            log.error("error:{}",e.getMessage(),e.getCause());
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "error happened");

        }


    }

    private Optional<Account> getAccountById(int id) throws NoResourceFoundException {
        return Optional.ofNullable(accountRepo.findByAccountID(id).orElseThrow(() ->
                new NoResourceFoundException(HttpMethod.GET,"not found")
        ));
    }

    @GetMapping("/cate")
    private ResponseData<List<Category>> listAllCategory() {
        return new ResponseData<>(HttpStatus.OK.value(),"found",cateRepo.findAll());
    }

    @GetMapping("/login")
    private String login(){
        return "success";
    }
    
}
