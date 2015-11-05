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
/**
 * BOMRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.repository.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.BOM;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BOMRepository extends CrudRepository<BOM, Long> {

	/**
	 * @param languageId
	 * @return
	 */
	@Query("select new Map(bom.id as bomId, bom.documentNo as bomNo, "
			+ "bom.issueDate as bomIssueDate, br.documentNo as bomReceiptName) "
			+ "from BOM bom join bom.receipt br")
	List<Map<String, Object>> getBOMList();

}
