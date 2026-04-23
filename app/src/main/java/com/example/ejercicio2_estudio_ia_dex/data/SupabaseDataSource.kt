package com.example.ejercicio2_estudio_ia_dex.data

import com.example.ejercicio2_estudio_ia_dex.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order

class SupabaseDataSource {

    val supabase = createSupabaseClient(
        supabaseUrl = BuildConfig.SUPABASE_URL,
        supabaseKey = BuildConfig.SUPABASE_KEY
    ) {
        install(Postgrest)
    }

    suspend fun getMyForms() : List<Formulario> {
        return supabase
            .from("formulario")
            .select() {
                order(column = "priority", order = Order.DESCENDING)
            }
            .decodeList()
    }

    suspend fun uploadForm(form: FormularioRequest) {
        supabase
            .from("formulario")
            .insert(form)
    }
}