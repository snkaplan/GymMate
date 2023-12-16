package com.gym.gymmate.feature.home

import androidx.lifecycle.viewModelScope
import com.gym.gymmate.core.common.BaseViewModel
import com.gym.gymmate.core.common.IViewEvents
import com.gym.gymmate.core.common.IViewState
import com.gym.gymmate.core.common.Resource
import com.gym.gymmate.core.common.asResource
import com.gym.gymmate.core.domain.category.CategoryItem
import com.gym.gymmate.core.domain.category.CategoryResult
import com.gym.gymmate.core.domain.category.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase) :
    BaseViewModel<HomeViewState, HomeViewEvents>() {
    override fun createInitialState(): HomeViewState = HomeViewState()

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().asResource()
                .onEach { result ->
                    when (result) {
                        Resource.Loading -> {
                            updateState { copy(loading = true) }
                        }

                        is Resource.Error -> {
                            updateState { copy(loading = false) }
                        }

                        is Resource.Success -> {
                            updateState {
                                copy(loading = false, categoryResult = result.data)
                            }
                        }
                    }
                }.launchIn(this)
        }
    }

    fun onCategoryClick(categoryItem: CategoryItem) {
        viewModelScope.launch {
            fireEvent(HomeViewEvents.NavigateCategory(categoryItem.id))
        }
    }
}

data class HomeViewState(
    val loading: Boolean = false,
    val categoryResult: CategoryResult = CategoryResult(listOf())
) : IViewState


sealed class HomeViewEvents : IViewEvents {
    data class NavigateCategory(val id: String) : HomeViewEvents()
}