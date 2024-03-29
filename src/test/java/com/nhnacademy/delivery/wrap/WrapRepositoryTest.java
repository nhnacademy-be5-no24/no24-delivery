package com.nhnacademy.delivery.wrap;

import com.nhnacademy.delivery.wrap.domain.Wrap;
import com.nhnacademy.delivery.wrap.repository.WrapRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@WebAppConfiguration
class WrapRepositoryTest {

    @Autowired
    private WrapRepository wrapRepository;

    @Test
    void testFindWrapByWrapName(){
        Wrap wrap1 = Wrap.builder()
                .wrapId(null)
                .wrapName("name1")
                .wrapCost(1L)
                .build();
        Wrap wrap2 = Wrap.builder()
                .wrapId(null)
                .wrapName("name2")
                .wrapCost(2L)
                .build();

        wrapRepository.save(wrap1);
        wrapRepository.save(wrap2);

        Optional<Wrap> optionalWrap = wrapRepository.findWrapByWrapName("name1");
        assertEquals("name1", optionalWrap.get().getWrapName());

    }
}
