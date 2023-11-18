package com.goplatform.server.pojo.domain;

import com.goplatform.server.pojo.constant.Player;

public class OneMove {
    Player player;

    // position
    int r, c;

    public OneMove(Player _player, int _r, int _c) {
        player = _player;
        r = _r;
        c = _c;
    }
}
