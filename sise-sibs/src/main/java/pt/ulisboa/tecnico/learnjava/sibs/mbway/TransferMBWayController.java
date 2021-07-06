package pt.ulisboa.tecnico.learnjava.sibs.mbway;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class TransferMBWayController {

	private String sourcePhone;
	private String targetPhone;
	private MbWay mbWay;
	private String amount;

	public TransferMBWayController(MbWay newMBWay, String sourcePhone, String targetPhone, String amount) {
		this.sourcePhone=sourcePhone;
		this.targetPhone=targetPhone;
		this.mbWay=newMBWay;
		this.amount=amount;
	}

	public boolean checkNumber() {
		if(mbWay.mbWayAccount.containsKey(sourcePhone) && mbWay.mbWayAccount.containsKey(targetPhone))
			return true;
		else
			return false;
	}

	public boolean hasMoney() {
		int balance=mbWay.getServices().getAccountByIban(mbWay.mbWayAccount.get(sourcePhone).getIban()).getBalance();
		int amountInt=Integer.parseInt(this.amount);
		if (balance>=amountInt) {
			return true;
		}
		else
			return false;
	}	

	public void doTransfer() throws AccountException {
		mbWay.getServices().withdraw(mbWay.mbWayAccount.get(sourcePhone).getIban(), Integer.parseInt(this.amount));
		mbWay.getServices().deposit(mbWay.mbWayAccount.get(targetPhone).getIban(), Integer.parseInt(this.amount));
	}
}
