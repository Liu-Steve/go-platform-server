window.onload = function () {

    // 围棋棋盘
    let gameDiv = $(".normalGameDiv");
    let game = new NormalGame(gameDiv);

    // 解决取色器关闭后输入法无法使用
    $("input[type='color']").onblur = function () {
        window.open().close();
    }
}
