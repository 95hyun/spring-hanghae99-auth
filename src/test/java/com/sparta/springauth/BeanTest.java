package com.sparta.springauth;

import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

//    @Autowired
//    Food food;
    // Food 타입에 이미 bean이 2개 주입되어있어서 두개 중 어느걸로 주입할지 몰라서 빨간줄뜸

    @Autowired
    Food pizza;

    @Autowired
    Food chicken;

    // 기본적으로는 Food타입으로 주입대상을 찾는다.
    // 다음으로는 주입된 빈이 chicken, pizza이니까 직접 빈의 이름을 적어주면 선택되어 적용된다.


    @Test
    @DisplayName("테스트")
    void test1() {
        pizza.eat();
        chicken.eat();
    }
}
