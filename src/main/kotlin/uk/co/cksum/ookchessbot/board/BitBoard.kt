package uk.co.cksum.ookchessbot.board

@ExperimentalUnsignedTypes
object BitBoard {
    val debruijn = 0x03f79d71b4cb0a89UL

    val ls1bLookup = arrayOf(
        0,  1, 48,  2, 57, 49, 28,  3,
        61, 58, 50, 42, 38, 29, 17,  4,
        62, 55, 59, 36, 53, 51, 43, 22,
        45, 39, 33, 30, 24, 18, 12,  5,
        63, 47, 56, 27, 60, 41, 37, 16,
        54, 35, 52, 21, 44, 32, 23, 11,
        46, 26, 40, 15, 34, 20, 31, 10,
        25, 14, 19,  9, 13,  8,  7,  6
    )

    val ms1bLookup = arrayOf(
        0, 47,  1, 56, 48, 27,  2, 60,
        57, 49, 41, 37, 28, 16,  3, 61,
        54, 58, 35, 52, 50, 42, 21, 44,
        38, 32, 29, 23, 17, 11,  4, 62,
        46, 55, 26, 59, 40, 36, 15, 53,
        34, 51, 20, 43, 31, 22, 10, 45,
        25, 39, 14, 33, 19, 30,  9, 24,
        13, 18,  8, 12,  7,  6,  5, 63
    )

    fun ls1bBitScan(bitBoard: ULong) = ls1bLookup[(((bitBoard and 0UL-bitBoard) * debruijn) shr 58).toInt()]

    fun ms1bBitScan(bitBoard: ULong): Int {
        var bb = bitBoard or bitBoard shr 1
        bb = bb or bb shr 2
        bb = bb or bb shr 4
        bb = bb or bb shr 8
        bb = bb or bb shr 16
        bb = bb or bb shr 32
        return ms1bLookup[((bb * debruijn) shr 58).toInt()]
    }

    fun occupiedSquares(bitBoard: ULong): List<Square> {
        var board = bitBoard
        val occupied = mutableListOf<Square>()
        while (board != 0UL) {
            val square = Square(ls1bBitScan(board))
            occupied.add(square)
            board = board xor square.toBitBoard()
        }
        return occupied
    }

    fun isSet(bitBoard: ULong, square: Square): Boolean = (square.toBitBoard() and bitBoard) != 0UL

    fun toString(bitBoard: ULong): String {
        var str = ""
        for (rank in Rank.values().reversed()) {
            for (file in File.values()) {
                val square = Square.fromPosition(rank, file)
                str += if (isSet(bitBoard, square)) "1" else "."
            }
            str += "\n"
        }
        return str
    }

    fun clearSquare(bitBoard: ULong, square: Square): ULong = square.toBitBoard().inv() and bitBoard
    fun setSquare(bitBoard: ULong, square: Square): ULong = square.toBitBoard() or bitBoard
}
