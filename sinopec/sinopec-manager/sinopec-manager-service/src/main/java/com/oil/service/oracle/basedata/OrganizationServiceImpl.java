package com.oil.service.oracle.basedata;

import java.awt.JobAttributes;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.dao.OrganizationDAO;
import com.oil.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Resource
	OrganizationDAO organizationDAO;
	@Override
	public String GetOrganizationTree() {
		List<Organization> areas=organizationDAO.findByProperty("片区机关");
		List<Organization>gasStations=organizationDAO.findByProperty("加油站");
		List<Organization>oilDepots=organizationDAO.findByProperty("油库");
		List<Organization>departs=organizationDAO.findByProperty("科室");
		List<Organization> companys=organizationDAO.findByName("石家庄石油分公司");
		
		JSONArray ja=new JSONArray();
		
		Organization company=new Organization();
		if (companys!=null&&companys.size()>0) {
			company=companys.get(0);
		}
		JSONObject cjo=new JSONObject();
		cjo.put("id", company.getId());
		cjo.put("pId", 0);
		cjo.put("name", company.getCode3265()+"--"+company.getName());
		ja.add(cjo);

		for (Organization d : departs) {
			JSONObject departjo=new JSONObject();
			departjo.put("id", d.getId());
			departjo.put("pId", d.getParentId());
			departjo.put("name", d.getCode3265()+"--"+d.getName());
			ja.add(departjo);
		}
		for (Organization od : oilDepots) {
			JSONObject oilDepotjo=new JSONObject();
			oilDepotjo.put("id", od.getId());
			oilDepotjo.put("pId", od.getParentId());
			oilDepotjo.put("name", od.getCode3265()+"--"+od.getName());
			ja.add(oilDepotjo);
		}
		for (Organization a : areas) {
			JSONObject areajo=new JSONObject();
			areajo.put("id", a.getId());
			areajo.put("pId", a.getParentId());
			areajo.put("name", a.getCode3265()+"--"+a.getName());
			ja.add(areajo);
		}

		for (Organization g : gasStations) {
			JSONObject gasStationjo=new JSONObject();
			gasStationjo.put("id", g.getId());
			gasStationjo.put("pId", g.getParentId());
			gasStationjo.put("name", g.getCode3265()+"--"+g.getName());
			ja.add(gasStationjo);
		}
		
		return ja.toJSONString();
	}

}
