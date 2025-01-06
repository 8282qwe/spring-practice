package aop.main;

import aop.domain.Product;
import aop.service.ProductService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        // @Before, @After, @AfterReturning, @Around
        test01();

        // @Before, @After, @AfterThrowing
//        test02();
    }

    public static void test01() {
        AbstractApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");

        ProductService productService = (ProductService) ac.getBean("productService");
        Product product = productService.find("TV");
        System.out.println(product);

        ac.close();
    }

    public static void test02() {
        try {
            AbstractApplicationContext ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");

            ProductService productService = (ProductService) ac.getBean("productService");
            Product product = productService.find("");
            System.out.println(product);

            ac.close();

        } catch (RuntimeException e) {
            // nothing
        }
    }
}
