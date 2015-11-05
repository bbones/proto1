/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
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
