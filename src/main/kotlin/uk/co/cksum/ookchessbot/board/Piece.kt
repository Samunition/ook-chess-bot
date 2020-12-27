package uk.co.cksum.ookchessbot.board

enum class Piece {
    PAWN {
        override fun toString(): String = "p"
    },
    KNIGHT {
        override fun toString(): String = "n"
    },
    BISHOP {
        override fun toString(): String = "b"
    },
    ROOK {
        override fun toString(): String = "r"
    },
    QUEEN {
        override fun toString(): String = "q"
    },
    KING {
        override fun toString(): String = "k"
    }
}