package com.nhnacademy.delivery.wrap.repository;

import com.nhnacademy.delivery.wrap.domain.Wrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WrapRepository extends JpaRepository<Wrap, Long> {
    Optional<Wrap> findWrapByWrapName(String wrapName);
}
