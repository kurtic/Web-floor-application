var canvas = document.querySelector("canvas");
var ctx = canvas.getContext('2d')

canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;

function draw(x, y) {
    ctx.strokeStyle = "#49e508";
    ctx.lineWidth = 5;
    ctx.lineCap = "butt";
    ctx.lineTo(20 * x, 20 * y);
    ctx.stroke();
}

function drawElse(x,y){
    ctx.lineTo(20 * x, 20 * y);
    ctx.stroke();

    for (var i = 0.5; i < 550; i += 10) {
        ctx.moveTo(i, 0);
        ctx.lineTo(i, 550);
    }

    for (var j = 0.5; j < 550; j += 10) {
        ctx.moveTo(0, j);
        ctx.lineTo(550, j);
    }

    ctx.strokeStyle = "#888";
    ctx.lineWidth = "1";
    ctx.stroke();
}