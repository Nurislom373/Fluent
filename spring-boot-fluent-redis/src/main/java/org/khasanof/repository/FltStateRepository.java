package org.khasanof.repository;

import org.khasanof.entity.FltStateHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 3/16/2024 7:58 PM
 */
@Repository
public interface FltStateRepository extends CrudRepository<FltStateHash, Long> {

    Optional<FltStateHash> findTop1ByUserId(Long userId);

    void deleteByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
