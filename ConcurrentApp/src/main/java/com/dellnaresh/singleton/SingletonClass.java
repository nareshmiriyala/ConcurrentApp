/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dellnaresh.singleton;

/**
 *
 * @author nareshm
 */
public class SingletonClass {
    
    private SingletonClass() {
    }
    
    public static SingletonClass getInstance() {
        return SingletonClassHolder.INSTANCE;
    }
    
    private static class SingletonClassHolder {

        private static final SingletonClass INSTANCE = new SingletonClass();
    }
}
