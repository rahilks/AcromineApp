package com.rahil.acronymapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.rahil.acronymapp.data.LongForm

@Composable
    fun LongFormListItem(lf: LongForm) {
        Row {
            Column {
                Text(text = lf.lf, style = typography.h6)
                Text(text = lf.since.toString(), style = typography.caption)
            }
        }
    }