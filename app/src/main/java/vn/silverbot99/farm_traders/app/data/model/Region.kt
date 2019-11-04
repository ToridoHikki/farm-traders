package vn.silverbot99.farm_traders.app.data.model

interface Region {

    fun getId(): Int

    fun getName(): String

    fun getParent(): Region?

    fun getGasoline(): Int

    fun getMedical(): Int

}