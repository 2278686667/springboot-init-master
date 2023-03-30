package com.zhby.database.dto;

import com.zhby.utils.entity.JsonTreeData;

import java.util.List;



public class treeTSysMenu extends JsonTreeData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public String menuico;
	public String menuIco;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuIco() {
		return menuIco;
	}

	public void setMenuIco(String menuIco) {
		this.menuIco = menuIco;
	}

	public String getMenuico() {
		return menuico;
	}

	public void setMenuico(String menuico) {
		this.menuico = menuico;
	}

	

	
}
