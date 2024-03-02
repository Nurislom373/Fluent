package org.khasanof.repository;

import org.khasanof.entity.FltStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.repository
 * @since 3/2/2024 12:57 AM
 */
@Repository
public interface FltStateRepository extends JpaRepository<FltStateEntity, Long> {

    Optional<FltStateEntity> findTop1ByUser_UserId(Long user_userId);

    boolean existsByUser_UserId(Long user_userId);

    void deleteByUser_UserId(Long user_userId);
}
