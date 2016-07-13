package com.gfu.wicket.cheesr.webapp;

import java.util.ArrayList;

import org.apache.wicket.model.Model;

import com.gfu.wicket.backend.BOServices;
import com.gfu.wicket.backend.bo.Cheese;

public class CheeseListModel extends Model<ArrayList<Cheese>>{

	private static final long serialVersionUID = 1L;

	@Override
	public ArrayList<Cheese> getObject() {
		return new ArrayList<>(BOServices.get().allCheeses());
	}

	
}
