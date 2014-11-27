package com.dellnaresh.concurrentapp;

import com.dellnaresh.entity.Trans;
import com.dellnaresh.jpa.TransJpaController;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import java.util.Map;

/**
 *
 * @author nareshm
 */
public class Start {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("com.dellnaresh_ConcurrentApp_jar_1.0-SNAPSHOTPU");
       TransJpaController transJpaController=new TransJpaController(entityManagerFactory);
//        transJpaController.create(new Trans());
        System.out.println(transJpaController.getTransCount());

    }

}
