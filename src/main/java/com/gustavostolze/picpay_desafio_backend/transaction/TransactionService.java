package com.gustavostolze.picpay_desafio_backend.transaction;

import org.springframework.stereotype.Service;

import com.gustavostolze.picpay_desafio_backend.authorization.AuthorizationService;
import com.gustavostolze.picpay_desafio_backend.notification.NotificationService;
import com.gustavostolze.picpay_desafio_backend.wallet.Wallet;
import com.gustavostolze.picpay_desafio_backend.wallet.WalletRepository;
import com.gustavostolze.picpay_desafio_backend.wallet.WalletType;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final WalletRepository walletRepository;
	private final AuthorizationService authorizationService;
	private final NotificationService notificationService;

	public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository,
			AuthorizationService authorizationService, NotificationService notificationService) {
		this.transactionRepository = transactionRepository;
		this.walletRepository = walletRepository;
		this.authorizationService = authorizationService;
		this.notificationService = notificationService;
	}

	public Transaction create(Transaction transaction) {
		// validate transaction

		validate(transaction);

		// save transaction

		Transaction newTransaction = transactionRepository.save(transaction);

		// debit and credit wallets

		Wallet payerWallet = walletRepository.findById(transaction.getPayer()).get().debit(transaction.getValue());
		Wallet payeeWallet = walletRepository.findById(transaction.getPayee()).get().credit(transaction.getValue());
		walletRepository.save(payerWallet);
		walletRepository.save(payeeWallet);

		// call external services
		// authorize sync

		authorizationService.authorize();

		// notification async with kafka

		notificationService.notify(transaction);

		return newTransaction;
	}

	// payer can not be payee
	// payer can not be merchant type
	// payer balance <= transaction value
	private void validate(Transaction transaction) {
		walletRepository.findById(transaction.getPayee())
				.map((payee) -> walletRepository.findById(transaction.getPayer())
						.map((payer) -> isValid(transaction, payee, payer))
						.orElseThrow(() -> new InvalidTransactionException("Invalid PAYER ID!")))
				.orElseThrow(() -> new InvalidTransactionException("Invalid PAYEE ID!"));
	}

	private boolean isValid(Transaction transaction, Wallet payee, Wallet payer) {
		return !payer.equals(payee) && payer.getType() != WalletType.MERCHANT.getValue()
				&& payer.getBalance().compareTo(transaction.getValue()) >= 0;
	}
}
