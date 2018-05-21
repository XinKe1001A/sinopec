package com.oil.dao.oracle;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oil.entity.Dataclass;
import com.oil.entity.Instance;

@Transactional
@Repository
public class DataclassDAO_Plus {
	@Resource
	BaseDAO<Dataclass> baseDAO;
}
