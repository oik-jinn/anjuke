package com.anjuke.incubator.mss.service.infosec;

import java.net.URISyntaxException;

public interface InfosecService {

    void fetchBanwordsToRedis(String dict) throws URISyntaxException;
}
