package com.api.sub.test.abstract_interface.wordcomma.abstracts;

import com.api.sub.test.abstract_interface.SetTextSimilarity;
import com.api.sub.test.abstract_interface.wordcomma.CustomerWordContainTextSimilarity;
import com.api.sub.test.abstract_interface.wordcomma.WordContainTextSimilarity;
import com.api.sub.test.abstract_interface.wordcomma.WordEqualsTextSimilarity;
import org.apache.commons.lang.StringUtils;

import java.util.stream.Stream;

//todo 추상클래스는 인터페이스 implements시 오버라이딩 안해도됨
public abstract class WordCommaRelTextSimilarity implements SetTextSimilarity {
    public Double similarity(String utterance, String[] texts) {

        String[][] wordRelList = Stream.of(texts)
                .map(words -> Stream.of(words.split(":::")).map(String::trim).filter(StringUtils::isNotBlank).toArray(String[]::new))
                .toArray(String[][]::new);

        return similarity(utterance, wordRelList);
    }

    abstract public Double similarity(String utterance, String[][] wordRelList);

    final static public WordCommaRelTextSimilarity wordContainTextSimilarity  = new WordContainTextSimilarity();

    final static public WordCommaRelTextSimilarity customerWordContainTextSimilarity  = new CustomerWordContainTextSimilarity();

    final static public WordCommaRelTextSimilarity wordEqualsTextSimilarity = new WordEqualsTextSimilarity();


}
