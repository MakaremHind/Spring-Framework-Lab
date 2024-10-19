package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.dao.WindowDao;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest // 
class WindowDaoTest {
    @Autowired // 
    private WindowDao windowDao;

    @Test
    public void shouldFindAWindowById() {
        WindowEntity window = windowDao.getReferenceById(-10L); //
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getWindowStatus().getValue()).isEqualTo(1.0);
    }
}