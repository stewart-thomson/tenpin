package com.vodafone.tenpin;

import com.vodafone.tenpin.controller.TenPinBowling;

/**
 * Main starting class
 */
public class Application {

    public static void main(String[] args) {
        TenPinBowling tenPinBowling = new TenPinBowling();
        tenPinBowling.start(System.in, System.out);
    }
}
