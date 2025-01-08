package com.bankingapp.serviceimpl;





import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bankingapp.dto.AccountDto;
import com.bankingapp.entities.Account;
import com.bankingapp.mapper.AccountMapper;
import com.bankingapp.repository.AccountRepository;
import com.bankingapp.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	private AccountRepository accountRepository;
	

	public AccountServiceImpl(AccountRepository accountRepository) {
		
		this.accountRepository = accountRepository;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		// TODO Auto-generated method stub
		Account account= AccountMapper.mapToAccount( accountDto);

		Account savedAccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		double total= account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		// TODO Auto-generated method stub
		Account account=accountRepository
				.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		
		if(account.getBalance()< amount) {
			throw new RuntimeException("Insufficient amount");
		}
		double total=account.getBalance()-amount;account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		// TODO Auto-generated method stub
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account) ->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
	}



	@Override
	public void deleteAccount(Long id) {
		// TODO Auto-generated method stub
		Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exits"));
		accountRepository.deleteById(id);
		
		
	}
	
	}

