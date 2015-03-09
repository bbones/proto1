package org.proto1.domain.inventory;

import org.proto1.domain.AbstractEntity;

public class InventoryRecord extends AbstractEntity {
	private InventoryLot inventoryItem;
	private Location source;
	private Location destination;

	public InventoryLot getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryLot inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

}
