package uk.co.cksum.ookchessbot.board

@ExperimentalUnsignedTypes
class Square(val index: Int) {
    companion object {
        fun fromPosition(rank: Rank, file: File): Square = Square((rank.ordinal shl 3) or file.ordinal)
        fun fromString(str: String): Square = fromPosition(Rank.values()[str[1].toInt()-1], File.valueOf(str[0].toString()))
    }
    override fun toString(): String {
        val rank = index / 8
        val file = index % 8
        return "${File.values()[file]}${1+rank}"
    }

    fun toBitBoard(): ULong {
        return 1UL shl index
    }
}