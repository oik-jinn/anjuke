package com.anjuke.incubator.mss.bean;

import java.util.Collections;
import java.util.List;

public class SearchResponseBean extends ResponseBean {

    private List<DetailsBean> details = Collections.emptyList();

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

}
