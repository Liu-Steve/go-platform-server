package com.goplatform.server.pojo.domain;

import lombok.Data;

@Data
public class OneMove {
   int player;

    // position
    int[] pos;

    public OneMove(int _player, int[] _pos) {
        player = _player;
        pos = _pos;
    }
}
