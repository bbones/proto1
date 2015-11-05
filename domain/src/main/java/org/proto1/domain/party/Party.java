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
// TODO query
//	select p.id, p.address, p.name, pn.lastName from Party p join p.personNames pn where pn.language.id=1
package org.proto1.domain.party;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 

public abstract class  Party extends AbstractEntity {
	public abstract String getName(Language language);
}
