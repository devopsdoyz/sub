package com.api.sub.test.abstract_interface;

import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;
import org.springframework.stereotype.Service;

public interface UtteranceSetSimilarityProvider {

    /**
     * 단어 분석기 반환
     * @return
     */
    public WordCommaRelTextSimilarity wordSimilarity();

    /**
     * 문장 분석기 반환
     * @return
     */
    public WordCommaRelTextSimilarity sentenceSimilarity();


    /**
     * 고객용 단어 분석기
     * 고객스크립트는 word로만 등록되므로 고객대답용 contains 생성
     * @return
     */
    public WordCommaRelTextSimilarity customerWordSimilarity();
}
