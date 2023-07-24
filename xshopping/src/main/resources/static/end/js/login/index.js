// 获取 canvas 元素的宽度和高度,并将宽度和高度设置为屏幕的可用宽度和高度。
const width = document.getElementById("canvas").width = screen.availWidth;
const height = document.getElementById("canvas").height = screen.availHeight;
// 获取 canvas 的绘图上下文
const ctx = document.getElementById("canvas").getContext("2d");
// 创建一个大小为 width/10 的数组并填充为 0
const arr = Array(Math.ceil(width / 10)).fill(0);
// 创建一个字符串数组,用于存储字符。
const str = "✧☹︎☠︎☸︎☣︎☢︎☯︎♾♲✰❤︎✦⚛︎☭☮︎⚔︎⚒︎☄︎✵⚰︎☘︎⚘♨︎✞☺︎♘♞☆☃︎★☼☀︎☾◎☽☁︎™Ω℞№ℹ︎❂❁✡︎✣✶✺✷◦◉⦿☒✗☐☞◇☛⚙︎☑︎⌘✘✔︎".split("");
// const str = " 0 1 ︎".split("");
ctx.font = "10px 优设标题黑";
function rain() {
    // 设置颜色，并绘制一个全屏的矩形
    ctx.fillStyle = "rgba(0,0,20,0.05)";
    ctx.fillRect(0, 0, width, height);
    // 设置文字的颜色
    ctx.fillStyle = '#00c8aa';
    arr.forEach(function (value, index) {
        // 根据数组的索引值来绘制文字，x 坐标为索引值 * 10，y 坐标为 value + 10。
        ctx.fillText(str[Math.floor(Math.random() * str.length)], index * 10, value + 10);

        // 从上一次绘制的位置开始，将数组值设置为下一次绘制位置。
        arr[index] = value >= height || value > 8888 * Math.random() ? 0 : value + 10;
    });
}
// 每 30 毫秒执行一次 rain() 函数。
setInterval(rain, 30);