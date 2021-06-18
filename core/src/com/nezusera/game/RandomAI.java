package com.nezusera.game;


import java.util.Random;

public class RandomAI extends AI{

    @Override
    public void AIUpdate(float bugTimer, Random r) {
        //Can we do something?
        if (bugTimer <= 0) {
            //Moves : Left, Right, Wait, Forward
            int options = 4;
            //Select Option
            int option = r.nextInt(4);
            switch (option) {
                case 0:
                    parent.RotateLeft();
                    break;
                case 1:
                    parent.RotateRight();
                    break;
                case 2:
                    parent.Wait();
                    break;
                case 3:
                    parent.Forward();
                    break;
            }
        }
    }
}
