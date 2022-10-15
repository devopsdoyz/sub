package com.api.sub.test.abstract_interface.controller;

import com.api.sub.test.abstract_interface.UtteranceSetSimilarityProvider;
import com.api.sub.test.abstract_interface.UtteranceSetSimilarityProviderImpl;
import com.api.sub.test.abstract_interface.wordcomma.abstracts.WordCommaRelTextSimilarity;
import com.api.sub.test.transaction.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "추상클래스 인터페이스 설계 테스트", description = "추상클래스 인터페이스 설계 테스트")
@RequestMapping("/api")
public class AbstractController {

    //todo UtteranceSetSimilarityProviderImpl대신 UtteranceSetSimilarityProvider 쓰면 에러남
    /*
    Parameter 0 of constructor in com.api.sub.test.abstract_interface.controller.AbstractController required a single bean, but 2 were found:
	- utteranceSetSimilarityProviderImpl: defined in file [/Users/doyz/dev/study/spring/doyz-personal/sub/build/classes/java/main/com/api/sub/test/abstract_interface/UtteranceSetSimilarityProviderImpl.class]
	- utteranceSetSimilarityProvider: defined in file [/Users/doyz/dev/study/spring/doyz-personal/sub/build/classes/java/main/com/api/sub/test/abstract_interface/UtteranceSetSimilarityProvider.class]
    Action:
    Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
     */
    private final UtteranceSetSimilarityProviderImpl utteranceAnalyzerProvider;

    @GetMapping("/test/abstract")
    @Operation(summary = "추상클래스 테스트1", description = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void test() {
        WordCommaRelTextSimilarity wordSimilarity = utteranceAnalyzerProvider.wordSimilarity();
        List<String> list = new ArrayList<>();
        list.add("a");
        double result = wordSimilarity.similarity("a", list.toArray(new String[list.size()]));

        System.out.println(result);
    }

}
