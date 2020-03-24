/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.activity.result

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ActivityResultRegistryTest {
    val registry = object : ActivityResultRegistry() {
        override fun <I : Any?, O : Any?> invoke(
            requestCode: Int,
            contract: ActivityResultContract<I, O>,
            input: I
        ) {}
    }

    @Test
    fun testOnRestoreInstanceState() {
        registry.register("key", StartActivityForResult()) {}

        val savedState = Bundle()
        registry.onSaveInstanceState(savedState)

        registry.onRestoreInstanceState(savedState)
    }

    @Test
    fun testOnRestoreInstanceStateNoKeys() {
        registry.onRestoreInstanceState(Bundle())
    }
}