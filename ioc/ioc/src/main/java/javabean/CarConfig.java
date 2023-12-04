package javabean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration      //
public class CarConfig {
    @Bean(name = "hyundai")    //외부에서 생성해서 주입받아서 쓸 수 있음
    public CarMaker hyundai(){
        CarMaker carMaker = new Hyundai();
        return carMaker;
    }
    @Bean
    public CarMaker kia(){
        CarMaker carMaker = new Kia();
        return carMaker;
    }
    @Bean
    public CarMaker ssangyong(){
        CarMaker carMaker = new Ssangyong();
        return carMaker;
    }
    @Bean(name = "carAgent")
    public CarAgent carAgent(){
        CarAgent carAgent = new CarAgent(hyundai());
        return carAgent;
    }
}
