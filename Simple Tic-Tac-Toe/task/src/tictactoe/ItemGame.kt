package tictactoe

data class ItemGame(
            var adress: Int,
            var valueItem: Char,
            var coordinates: String,
            var status: Boolean
            ) {
    operator fun invoke(c: Char, b: Boolean) {
        valueItem = c
        status = b
    }
}
