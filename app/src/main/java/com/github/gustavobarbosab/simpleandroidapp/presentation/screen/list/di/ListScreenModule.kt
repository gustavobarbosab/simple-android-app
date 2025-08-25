package com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.di

import com.github.gustavobarbosab.simpleandroidapp.data.repository.ItemsRepositoryImpl
import com.github.gustavobarbosab.simpleandroidapp.domain.repository.ItemsRepository
import com.github.gustavobarbosab.simpleandroidapp.domain.usecase.GetItemsUseCase
import com.github.gustavobarbosab.simpleandroidapp.domain.usecase.GetItemsUseCaseImpl
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper.ItemToItemModelMapper
import com.github.gustavobarbosab.simpleandroidapp.presentation.screen.list.mapper.ItemToItemModelMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ListScreenModule {

    @Binds
    abstract fun bindsItemsRepository(impl: ItemsRepositoryImpl): ItemsRepository

    @Binds
    abstract fun bindsItemToItemModelMapperImpl(impl: ItemToItemModelMapperImpl): ItemToItemModelMapper

    @Binds
    abstract fun bindsGetItemsUseCase(impl: GetItemsUseCaseImpl): GetItemsUseCase
}