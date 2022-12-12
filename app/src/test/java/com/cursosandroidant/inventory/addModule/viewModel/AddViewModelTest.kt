package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addProductTest() {
        val addViewModel = AddViewModel()
        val observer = Observer<Boolean> { }
        try {
            addViewModel.getResult().observeForever(observer)
            addViewModel.addProduct(product)
            val result = addViewModel.getResult().value
            assertThat(result, `is`(true))
            assertThat(result, not(nullValue())) //optional validation
        } finally {
            addViewModel.getResult().removeObserver(observer)
        }
    }

    companion object {
        val product = Product(
            117,
            "Xbox",
            135,
            "https://imagenes.20minutos.es/files/image_990_v3/uploads/imagenes/2022/10/27/xbox-microsoft.jpeg",
            4.8,
            56
        )
    }
}