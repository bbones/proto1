package org.proto1.domain.accounting;

import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.Analitic;
import org.proto1.domain.Period;

@Entity
public class Record {

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
	private Map<AnaliticRole, Analitic> analitics;
	private Double amount;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Map<AnaliticRole, Analitic> getAnalitics() {
		return analitics;
	}

	public void setAnalitics(Map<AnaliticRole, Analitic> analitics) {
		this.analitics = analitics;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
