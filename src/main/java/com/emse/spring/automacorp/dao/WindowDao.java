package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WindowDao extends JpaRepository<WindowEntity, Long> {

    List<WindowEntity> findByRoomId(Long roomId); // Custom method for finding windows by room ID.

    @Query("select w from WindowEntity w where w.name=:name")
    WindowEntity findByName(@Param("name") String name);

    @Modifying
    @Query("delete from WindowEntity w where w.name = ?1")
    void deleteByName(String name);
}
