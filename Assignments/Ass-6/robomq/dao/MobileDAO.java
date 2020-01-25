package com.robomq.dao;

import java.util.List;

import com.robomq.pojo.Mobile;

public interface MobileDAO {
	Mobile getMobileById(int id);

	List<Mobile> getAllMobile();

	boolean deleteMobile(Mobile mobile);

	boolean updateMobile(Mobile mobile);

	boolean createMobile(Mobile mobile);

	


}
