package com.github.roroche.eoandroidworkmanager

import android.content.Context
import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestDriver
import androidx.work.testing.WorkManagerTestInitHelper
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for [OneTimeRestWorkRequest].
 */
@RunWith(AndroidJUnit4::class)
class OneTimeRestWorkRequestTest {

    private lateinit var context: Context
    private lateinit var workManager: WorkManager
    private lateinit var testDriver: TestDriver

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
        workManager = WorkManager.getInstance(context)
        testDriver = WorkManagerTestInitHelper.getTestDriver(context)!!
    }

    /**
     * Test that work is processed successfully.
     */
    @Test
    fun testWorkIsProcessedSuccessfully() {
        testWorkIsProcessedWithResult(
            success = true,
            expectedState = WorkInfo.State.SUCCEEDED
        )
    }

    /**
     * Test that work is processed with failure.
     */
    @Test
    fun testWorkIsProcessedWithFailure() {
        testWorkIsProcessedWithResult(
            success = false,
            expectedState = WorkInfo.State.FAILED
        )
    }

    private fun testWorkIsProcessedWithResult(
        success: Boolean,
        expectedState: WorkInfo.State
    ) {
        val request: ManagedRestRequest = OneTimeRestWorkRequest(
            workManager = workManager,
            workerClass = BooleanWorker::class.java,
            data = BooleanInputData(success = success).toData()
        )

        request.operation().result

        testDriver.setAllConstraintsMet(request.id())
        assertThat(
            request.workInfo().get().state,
            `is`(expectedState)
        )
    }
}
