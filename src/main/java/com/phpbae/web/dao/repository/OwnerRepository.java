package com.phpbae.web.dao.repository;

import com.phpbae.web.business.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * JpaRepository 인터페이스를 상속하는 걸로, DAO 인터페이스 작성이 완료.
 * 상속 시, 제네릭으로 2개의 형을 지정 <T,ID>
 * 첫번째는 Entity Class / 두번째는 Entity Class 의 ID 형.
 */

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
