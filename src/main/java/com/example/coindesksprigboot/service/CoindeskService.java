package com.example.coindesksprigboot.service;

import java.util.Map;

public interface CoindeskService {

    Map<String, Object> getCoindeskApi();

    Map<String, Map<String, Object>> getCoindesk();
}
