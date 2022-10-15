package com.api.sub.test.abstract_interface.wordcomma;

import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;

public class WordContainTextSimilarity extends WordCommaRelTextSimilarity {

    @Override
    public Double similarity(String utterance, String[][] wordRelList) {
        return 1.0;
    }
}
