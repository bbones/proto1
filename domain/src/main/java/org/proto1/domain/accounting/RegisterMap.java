package org.proto1.domain.accounting;

import java.util.Map;
import java.util.Set;

import org.proto1.domain.Analitic;
import org.proto1.domain.Period;

public class RegisterMap {
	private Account account;
	private Period period;
	private Map<Set<Analitic>, Register> registers;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Map<Set<Analitic>, Register> getRegisters() {
		return registers;
	}

	public void setRegisters(Map<Set<Analitic>, Register> registers) {
		this.registers = registers;
	}

	
	public void addRegister(Register register) {
		registers.put(register.getAnalitics(), register);
	}
}
