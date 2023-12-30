package com.sparta.springauth.food;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pizza")
// 같은 타입의 Bean들에 Qualifier와 Primary가 동시에 적용되어있다면 Qualifier의 우선순위가 더 높습니다.
public class Pizza implements Food {
    @Override
    public void eat() {
        System.out.println("피자를 먹습니다.");
    }
}