package uk.co.cksum.ookchessbot

import uk.co.cksum.ookchessbot.board.BitBoard
import uk.co.cksum.ookchessbot.board.Piece

@ExperimentalUnsignedTypes
fun main() {
    val pieces = Array(2) { ULongArray(6)}
    val combinedColour = ulongArrayOf(0UL, 0UL)
    var combinedAll = 0UL

    pieces[0][Piece.PAWN.ordinal] = 0x000000000000FF00UL
    pieces[0][Piece.KNIGHT.ordinal] = 0x0000000000000042UL
    pieces[0][Piece.BISHOP.ordinal] = 0x0000000000000024UL
    pieces[0][Piece.ROOK.ordinal] = 0x0000000000000081UL
    pieces[0][Piece.QUEEN.ordinal] = 0x0000000000000008UL
    pieces[0][Piece.KING.ordinal] = 0x0000000000000010UL

    pieces[1][Piece.PAWN.ordinal] = 0x00FF000000000000UL
    pieces[1][Piece.KNIGHT.ordinal] = 0x4200000000000000UL
    pieces[1][Piece.BISHOP.ordinal] = 0x2400000000000000UL
    pieces[1][Piece.ROOK.ordinal] = 0x8100000000000000UL
    pieces[1][Piece.QUEEN.ordinal] = 0x0800000000000000UL
    pieces[1][Piece.KING.ordinal] = 0x1000000000000000UL

    Piece.values().forEach {
        combinedColour[0] = combinedColour[0] or pieces[0][it.ordinal]
        combinedColour[1] = combinedColour[1] or pieces[1][it.ordinal]
    }
    combinedAll = combinedColour[0] or combinedColour[1]
    println(BitBoard.toString(combinedAll))
    BitBoard.occupiedSquares(combinedAll).forEach(::println)
}
