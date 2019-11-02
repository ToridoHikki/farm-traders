package vn.minerva.travinh.app.data.model

interface Region {

    fun getId(): Int

    fun getName(): String

    fun getParent(): Region?

    fun getGasoline(): Int

    fun getMedical(): Int

}