package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.repository.PolicyRepositoryImpl
import com.fork.spoonfeed.domain.repository.PolicyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePolicyRepository(policyDataSource: PolicyDataSource): PolicyRepository{
        return PolicyRepositoryImpl(policyDataSource)
    }
}
