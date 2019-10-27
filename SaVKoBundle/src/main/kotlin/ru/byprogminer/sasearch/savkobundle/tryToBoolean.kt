package ru.byprogminer.sasearch.savkobundle

inline fun <reified E: Throwable> tryToBoolean(block: () -> Unit): Boolean =
        try {
            block()
            true
        } catch (e: Throwable) {
            if (!E::class.java.isInstance(e)) {
                throw e
            }

            e.printStackTrace()
            false
        }
