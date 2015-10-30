package com.anjuke.incubator.mss.service.algorithm;

import java.util.Set;

import org.ahocorasick.trie.Trie;

/**
 * 应该起一个更直观的名字
 * 比如， Trie Map?
 *
 * 这个类的作用是在内存的保存一个 HashMap
 * Key 是字典名，Value 是 Trie
 *
 */
public interface Algorithm {

    void reload(String dict);

    Boolean create(String dict);

    Boolean delete(String dict);

    Boolean exists(String dict);

    Trie getTrie(String dict) throws Exception;

    void reloadAll();

    Set<String> getDicts();
}
