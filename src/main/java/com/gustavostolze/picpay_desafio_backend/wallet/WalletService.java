package com.gustavostolze.picpay_desafio_backend.wallet;

import java.util.List;

import com.gustavostolze.picpay_desafio_backend.PicpayDesafioBackendApplication;
import org.springframework.stereotype.Service;

@Service
public class WalletService {


	private final WalletRepository walletRepository;

	public WalletService(WalletRepository walletRepository, PicpayDesafioBackendApplication picpayDesafioBackendApplication) {
		this.walletRepository = walletRepository;
	}
	
	public List<Wallet> list() {
		return walletRepository.findAll();
	}

}
