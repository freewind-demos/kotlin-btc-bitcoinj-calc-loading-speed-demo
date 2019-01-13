package example

import org.bitcoinj.core.Context
import org.bitcoinj.params.MainNetParams
import org.bitcoinj.utils.BlockFileLoader
import java.io.File

val mainNetParams = MainNetParams()
val blockChainFiles = listOf(File("./btc-data/blocks/blk00000.dat"))

fun main(args: Array<String>) {
    Context.getOrCreate(mainNetParams)

    val loader = BlockFileLoader(mainNetParams, blockChainFiles)

    val start = System.currentTimeMillis()

    var count = 0
    var messageSize = 0
    for (block in loader) {
        messageSize += block.messageSize
        count += 1

        if (count % 1000 == 0) {
            val now = System.currentTimeMillis()
            val seconds = (now - start) / 1000.0
            val M = 1024 * 1024
            println("Speed: ${messageSize / seconds / M} M/s, $count blocks in $seconds seconds")
        }
    }
}