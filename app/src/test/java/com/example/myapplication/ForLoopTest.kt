package com.example.myapplication

import org.junit.Test

class ForLoopTest {

    data class FOCK(val id: Int, val type: Int, var loopIn : Int = 0, var listSize : Int = 0)

    @Test
    fun testForLoop() {
        val fockList = ArrayList<FOCK>()
        val runList = ArrayList<FOCK>()

        for (i in 0..99) {
            fockList.add(FOCK(i, i % 3))
        }

        val fockListSize = fockList.size
        var loopTime = 0
        for (i in 0..fockList.size) {
            var data = fockList.getOrNull(i)
            data?.loopIn = loopTime
            data?.listSize = fockList.size
            if (data?.type == 1)
                fockList.remove(data)
            else{
                data?.run {
                    runList.add(this)
                }
            }

            loopTime++
        }

        assert(fockListSize == 100)

    }
}