package com.api.sub.test.abstract_interface.wordcomma;

import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;

public class CustomerWordContainTextSimilarity extends WordCommaRelTextSimilarity {
    @Override
    public Double similarity(String utterance, String[][] wordRelList) {
        return 3.0;
    }
}
