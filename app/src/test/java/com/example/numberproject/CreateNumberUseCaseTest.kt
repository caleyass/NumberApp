package com.example.numberproject

import com.example.numberproject.data.local.entity.Number
import com.example.numberproject.domain.repositories.NumberRepository
import com.example.numberproject.domain.usecases.CreateNumberUseCase
import com.example.numberproject.domain.usecases.GetNumberFactUseCase
import com.example.numberproject.domain.usecases.InsertNumberUseCase
import io.mockk.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CreateNumberUseCaseTest {

    private lateinit var createNumberUseCase: CreateNumberUseCase
    private lateinit var insertNumberUseCase: InsertNumberUseCase
    private lateinit var getNumberFactUseCase: GetNumberFactUseCase

    // Mock dependencies
    private val numberRepository: NumberRepository = mockk()

    @Before
    fun setup() {
        createNumberUseCase = CreateNumberUseCase() // Replace with necessary dependencies
        insertNumberUseCase = InsertNumberUseCase(numberRepository)
        getNumberFactUseCase = GetNumberFactUseCase(numberRepository)
    }

    @Test
    fun `execute should create a Number object correctly`() {
        // Arrange
        val numberValue = 42L
        val fact = "This is a fact about 42"

        // Mock the repository method
        coEvery { getNumberFactUseCase.execute(numberValue) } returns fact

        // Act
        val result = createNumberUseCase.execute(numberValue, fact)

        // Assert
        assertEquals(Number(number = numberValue, fact = fact), result)
    }

    @Test
    fun `insertNumber should call repository with the correct number`() = runBlocking {
        // Arrange
        val number = Number(0, 1, "A fact about 1")

        // Mock the repository behavior
        coEvery { numberRepository.insertNumber(number) } just Runs

        // Act
        insertNumberUseCase.execute(number)

        // Verify that the repository was called correctly
        coVerify { numberRepository.insertNumber(number) }
    }
}
