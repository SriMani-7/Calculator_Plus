package com.ithoughts.dev.g3.calculator.logic

import androidx.annotation.DrawableRes
import com.ithoughts.dev.g3.calculator.R.drawable


sealed class Category(val factor: Double, val name: String, @DrawableRes val res: Int? = null) {
    object ACCELERATION : Category(1.0E0, "Acceleration", drawable.icon_acceleration)
    object ANGLE : Category(1.0, "Angle", drawable.ic_baseline_architecture_24)
    object AREA : Category(1.0, "Area", drawable.ic_outline_area_chart_24)
    object CSS : Category(1.0, "CSS Units", drawable.ic_baseline_css_24)
    object CURRENT : Category(1.0, "Current", drawable.ic_drawable_current)
    object DATA : Category(1.0, "Data", drawable.ic_baseline_data_object_24)
    object ELECTRIC : Category(1.6022E-19, "Electric charge", drawable.ic_drawable_electric)
    object ENERGY : Category(1.0E0, "Energy", drawable.ic_baseline_electric_bolt_24)
    object FORCE : Category(1.0, "Force")
    object LENGTH : Category(1.0, "Length", drawable.icon_length)
    object LUMINANCE : Category(1.0, "Luminance")
    object LUMINOUS_FLUX : Category(1.0, "Luminous flux")
    object MASS : Category(1.0E-6, "Mass", drawable.icon_weight)
    object PRESSURE : Category(1.0E0, "Pressure",drawable.ic_baseline_compress_24)
    object SPEED : Category(1.0E0, "Speed", drawable.ic_baseline_speed_24)
    object TEMPERATURE_GRADIENT : Category(1.0, "Temperature gradient", drawable.icon_temperature)
    object TIME : Category(1.0, "Time", drawable.ic_drawable_time)
    object TORQUE : Category(1.0, "Torque")
    object VOLUME : Category(1.0E0, "Volume", drawable.icon_weight)
    object VOLTAGE : Category(1.0E0, "Voltage", drawable.ic_baseline_electrical_services_24)
    object WORK : Category(1.0E0, "Work", drawable.ic_baseline_work_outline_24)
    object BLOOD_GLUCOSE : Category(1.0, "Blood glucose", drawable.ic_baseline_bloodtype_24)

    object StandardCALCULATOR : Category(0.0, "Standard Calculator", drawable.ic_outline_calculate_24)
    object ScientificCALCULATOR : Category(0.0, "Scientific Calculator", drawable.ic_baseline_functions_24)
    object ProgrammerCALCULATOR : Category(0.0, "Programmer Calculator", drawable.ic_baseline_code_24)

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
                ACCELERATION,
                TORQUE,
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