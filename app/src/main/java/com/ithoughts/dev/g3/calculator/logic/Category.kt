package com.ithoughts.dev.g3.calculator.logic

import androidx.annotation.DrawableRes


sealed class Category(val factor: Double, val name: String, @DrawableRes val res: Int? = null) {
    object ACCELERATION : Category(1.0E0, "Acceleration")
    object ANGLE : Category(1.0, "Angle")
    object AREA : Category(1.0, "Area")
    object CSS : Category(1.0, "CSS Units")
    object CURRENT : Category(1.0, "Current")
    object DATA : Category(1.0, "Data")
    object ELECTRIC : Category(1.6022E-19, "Electric charge")
    object ENERGY : Category(1.0E0, "Energy")
    object FORCE : Category(1.0, "Force")
    object LENGTH : Category(1.0, "Length")
    object LUMINANCE : Category(1.0, "Luminance")
    object LUMINOUS_FLUX : Category(1.0, "Luminous flux")
    object MASS : Category(1.0E-6, "Mass")
    object PRESSURE : Category(1.0E0, "Pressure")
    object SPEED : Category(1.0E0, "Speed")
    object TEMPERATURE_GRADIENT : Category(1.0, "Temperature gradient")
    object TIME : Category(1.0, "Time")
    object TORQUE : Category(1.0, "Torque")
    object VOLUME : Category(1.0E0, "Volume")
    object VOLTAGE : Category(1.0E0, "Voltage")
    object WORK : Category(1.0E0, "Work")
    object BLOOD_GLUCOSE : Category(1.0, "Blood glucose")

    object StandardCALCULATOR : Category(0.0, "Standard Calculator")
    object ScientificCALCULATOR : Category(0.0, "Scientific Calculator")
    object ProgrammerCALCULATOR : Category(0.0, "Programmer Calculator")

    companion object {
        fun values(): Array<Category> {
            return arrayOf(
                LENGTH,
                ANGLE,
                AREA,
                CSS,
                CURRENT,
                ENERGY,
                DATA,
                ELECTRIC,
                MASS,
                PRESSURE,
                SPEED,
                TEMPERATURE_GRADIENT,
                TIME,
                VOLUME,
                VOLTAGE,
                WORK,
                BLOOD_GLUCOSE,
                TORQUE,
                ACCELERATION,
                LUMINOUS_FLUX,
                LUMINANCE,
                FORCE
            )
        }

        fun calculators() = listOf(
            StandardCALCULATOR,
            ScientificCALCULATOR,
            ProgrammerCALCULATOR
        )
    }
}