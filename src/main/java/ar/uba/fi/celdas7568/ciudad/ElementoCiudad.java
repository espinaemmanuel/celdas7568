package ar.uba.fi.celdas7568.ciudad;

import java.util.Set;

import com.google.common.collect.Sets;

import ar.uba.fi.celdas7568.ciudad.gui.ItemCiudad;

public class ElementoCiudad {

	public int x;
	public int y;
	
	public Set<ItemCiudad> itemsCiudad = Sets.newHashSet();
	
	public ElementoCiudad(int x, int y, Set<ItemCiudad> itemsCiudad) {
		super();
		this.x = x;
		this.y = y;
		this.itemsCiudad = itemsCiudad;
	}
	
	public ElementoCiudad(int x, int y, ItemCiudad itemCiudad) {
		this(x, y, Sets.newHashSet(itemCiudad));
	}
}
