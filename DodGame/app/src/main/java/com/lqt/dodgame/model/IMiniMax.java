package com.lqt.dodgame.model;

public interface IMiniMax {
    int MOVE2LEFT = 1;
    int MOVE2UP = 2;
    int MOVE2RIGHT = 3;
    int MOVE2DOWN = 4;
    int MOVE2FINAL = 5;
    int MAP_ROOT[] = {-1, 0, 0, -1, 0, 0, 0, 1, 1};
    int POINTS_OF_WHITE[] = {30, 35, 40, 15, 20, 25, 0, 5, 10};
    int POINTS_OF_BLACK[] = {10, 25, 40, 5, 20, 35, 0, 15, 30};
}
