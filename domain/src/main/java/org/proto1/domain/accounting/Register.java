package org.proto1.domain.accounting;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.Analitic;
import org.proto1.domain.Period;

@Entity
public class Register {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Ledger ledger;
	@ManyToOne
	private Account account;
	@ManyToOne
	private Period period;
	@OneToMany
	private Set<Analitic> analitics;
	private Double inBalance;
	private Double turnover;
	private Double outBalance;

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

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

	public Set<Analitic> getAnalitics() {
		return analitics;
	}

	public void setAnalitics(Set<Analitic> analitics) {
		this.analitics = analitics;
	}

	public Double getInBalance() {
		return inBalance;
	}

	public void setInBalance(Double inBalance) {
		this.inBalance = inBalance;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Double getOutBalance() {
		return outBalance;
	}

	public void setOutBalance(Double outBalance) {
		this.outBalance = outBalance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
