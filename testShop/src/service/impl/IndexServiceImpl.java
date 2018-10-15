package service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IndexDao;
import service.IndexService;

@Service
public class IndexServiceImpl  implements IndexService{
	@Autowired 
	IndexDao indexDao;

}
