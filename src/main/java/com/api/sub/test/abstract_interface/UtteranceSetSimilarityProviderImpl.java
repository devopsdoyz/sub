package com.api.sub.test.abstract_interface;

import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;
import org.springframework.stereotype.Service;

@Service
public class UtteranceSetSimilarityProviderImpl implements UtteranceSetSimilarityProvider{

    @Override
    public WordCommaRelTextSimilarity wordSimilarity() {
        return WordCommaRelTextSimilarity.wordEqualsTextSimilarity;
    }

    @Override
    public WordCommaRelTextSimilarity sentenceSimilarity() {
        return WordCommaRelTextSimilarity.wordContainTextSimilarity;
    }

    @Override
    public WordCommaRelTextSimilarity customerWordSimilarity() {
        return WordCommaRelTextSimilarity.customerWordContainTextSimilarity;
    }
}
