package com.rahil.acronymapp.data

import com.google.gson.annotations.SerializedName

data class Acromine(
    @SerializedName("sf"  ) var sf  : String,
    @SerializedName("lfs" ) var lfs : List<LongForm>,
)
