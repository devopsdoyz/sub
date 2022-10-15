package com.api.sub.test.abstract_interface.wordcomma;

import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;

public class WordEqualsTextSimilarity extends WordCommaRelTextSimilarity {
    @Override
    public Double similarity(String utterance, String[][] wordRelList) {
        return 2.0;
    }
}
