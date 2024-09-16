package com.demo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer>{
	Account findByFullName(String name);

	ArrayList<Account> findAllByEmail(String email);

	Optional<Account> findByEmail(String email);

	public Optional<Account> findByAccountID(int id);

	Account findByEmailAndPassword(String email, String password);
}
