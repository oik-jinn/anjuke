package com.anjuke.incubator.mss.bean;

import org.ahocorasick.trie.Emit;

/**
 * Emit 已经包括了 keyword,start,end
 * 增加了一个 type 字段
 *
 */
public class DetailsBean extends Emit {

    private String type;

    public DetailsBean(int start, int end, String keyword) {
        super(start, end, keyword);
    }

    public DetailsBean(Emit emit) {
        super(emit.getStart(), emit.getEnd(), emit.getKeyword());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
