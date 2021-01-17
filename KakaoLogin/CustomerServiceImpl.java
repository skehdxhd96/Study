package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.CustomerVO;
import org.zerock.mapper.CustomerMapper;
import org.zerock.mapper.testMapper;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CustomerServiceImpl implements CustomerService{

	@Setter(onMethod_ = @Autowired)
	private CustomerMapper cm;
	
	@Override
	public void UserLogin(CustomerVO c) {
		
		cm.UserLogin(c);
	}
}
