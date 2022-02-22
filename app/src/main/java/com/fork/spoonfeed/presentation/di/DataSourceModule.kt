package com.fork.spoonfeed.presentation.di

import com.fork.spoonfeed.data.remote.api.policy.PolicyService
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSource
import com.fork.spoonfeed.data.remote.datasource.PolicyDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePolicyDataSource(policyService: PolicyService): PolicyDataSource {
        return PolicyDataSourceImpl(policyService)
    }
}
