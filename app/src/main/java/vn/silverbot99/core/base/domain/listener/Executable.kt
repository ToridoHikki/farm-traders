package vn.silverbot99.core.base.domain.listener

import java.io.Serializable

@FunctionalInterface
interface Executable : Serializable {
    fun execute()
}
