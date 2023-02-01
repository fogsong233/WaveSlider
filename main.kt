fun WaveSlider() {
    var progress by remember { mutableStateOf(0f) }
    val f = remember {
        mutableStateOf(0.0)
    }
    LaunchedEffect(Unit) {
        while (true) {
            f.value += Math.PI / 30
            delay(50)
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Slider(
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.primary,
                disabledThumbColor = MaterialTheme.colors.onSurface
                    .copy(alpha = ContentAlpha.disabled)
                    .compositeOver(MaterialTheme.colors.surface),
                activeTrackColor = Color(0),
                inactiveTrackColor = Color(0),
                disabledActiveTrackColor =
                Color(0),
                disabledInactiveTrackColor =
                Color(0),
                activeTickColor = Color(0),
                inactiveTickColor = Color(0),
                disabledActiveTickColor = Color(0),
                disabledInactiveTickColor = Color(0)
            ),
            value = progress,
            onValueChange = { progress = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .align(Alignment.Center)
                .drawBehind {
                    var yOffset = size.height / 2
                    val step = 5.0
                    var x1 = 0.0
                    var y1 = 0.0 + yOffset
                    val a = 10
                    var x2 = 0.0
                    var y2 = 0.0
                    val w = 2 * Math.PI / (size.width / 10)
                    while (x1 < size.width * progress) {
                        x2 = x1 + step
                        y2 = a * Math.sin(w * x2 + f.value) + yOffset
                        drawLine(
                            color = Color(0xfff93684),
                            start = Offset(x1.toFloat(), y1.toFloat()),
                            end = Offset(x2.toFloat(), y2.toFloat()),
                            strokeWidth = 5f
                        )
                        x1 = x2
                        y1 = y2
                    }
                    drawLine(
                        color = Color(0xfff93684),
                        start = Offset(size.width * progress, yOffset),
                        end = Offset(size.width, yOffset),
                        strokeWidth = 5f
                    )


                }
        )
    }
}
