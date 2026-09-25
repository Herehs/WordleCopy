package com.herehs.worldecopy.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.herehs.worldecopy.GolosText_Medium
import com.herehs.worldecopy.GolosText_Regular
import com.herehs.worldecopy.GolosText_SemiBold
import com.herehs.worldecopy.Res
import org.jetbrains.compose.resources.Font


@Composable
fun golosFontFamily() = FontFamily(
    Font(Res.font.GolosText_Regular, FontWeight.Normal),
    Font(Res.font.GolosText_Medium, FontWeight.Medium),
    Font(Res.font.GolosText_SemiBold, FontWeight.SemiBold)
)