package com.unisys.ch.jax.cost.gwt.server;

import java.net.URL;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.unisys.ch.jax.cost.common.model.Cost;
import com.unisys.ch.jax.cost.gwt.client.CostService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CostServiceImpl extends RemoteServiceServlet implements CostService {

	private static boolean LOCAL = false;
	private static int PAGE_SIZE = 10;
	private static String REST_URL;
	static {
		if (LOCAL) {
			REST_URL = "http://localhost:8080/costs/";
		} else {
			REST_URL = "https://cost-server.appspot.com/costs/";
		}
	}

	@Override
	public List<Cost> list(int page) {
		ObjectMapper mapper = new ObjectMapper();
		List<Cost> cost = null;
		try {
			cost = mapper.readValue(new URL(REST_URL + getPageParams(page)), new TypeReference<List<Cost>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cost;
	}

	private String getPageParams(int page) {
		StringBuffer sb = new StringBuffer();
		sb.append("?page=");
		sb.append(page);
		sb.append("&pageSize=");
		sb.append(PAGE_SIZE);
		return sb.toString();
	}

	@Override
	public void save(Cost cost) {
		HttpClient client = new DefaultHttpClient();
		if (cost != null) {
			HttpPost postRequest = new HttpPost(REST_URL + (cost.getId() == null ? "" : cost.getId()));
			postRequest.addHeader("Content-Type", "application/json");
			ObjectMapper mapper = new ObjectMapper();
			try {
				String json = mapper.writeValueAsString(cost);
				postRequest.setEntity(new StringEntity(json));
				client.execute(postRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(List<Long> ids) {
		HttpClient client = new DefaultHttpClient();
		if (ids != null) {
			for (Long id : ids) {
				HttpDelete deleteRequest = new HttpDelete(REST_URL + id);
				try {
					client.execute(deleteRequest);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
