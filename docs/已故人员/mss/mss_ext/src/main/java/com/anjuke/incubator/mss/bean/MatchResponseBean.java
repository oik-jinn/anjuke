package com.anjuke.incubator.mss.bean;

import java.util.Collections;
import java.util.List;

import org.ahocorasick.trie.Emit;

public class MatchResponseBean extends ResponseBean {

    private List<Emit> details = Collections.emptyList();

    public List<Emit> getDetails() {
        return details;
    }

    public void setDetails(List<Emit> details) {
        this.details = details;
    }

}
