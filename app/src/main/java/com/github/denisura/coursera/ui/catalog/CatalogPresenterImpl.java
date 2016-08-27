/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.github.denisura.coursera.ui.catalog;

import java.util.List;

public class CatalogPresenterImpl implements CatalogPresenter, FindItemsInteractor.OnFinishedListener {

    private CatalogView mCatalogView;
    private FindItemsInteractor findItemsInteractor;

    public CatalogPresenterImpl(CatalogView catalogView, FindItemsInteractor findItemsInteractor) {
        this.mCatalogView = catalogView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override public void onResume() {
        if (mCatalogView != null) {
            mCatalogView.showProgress();
        }

        findItemsInteractor.findItems(this);
    }

    @Override public void onDestroy() {
        mCatalogView = null;
    }

    @Override public void onFinished(List<String> items) {
        if (mCatalogView != null) {
            mCatalogView.setItems(items);
            mCatalogView.hideProgress();
        }
    }

    public CatalogView getCatalogView() {
        return mCatalogView;
    }
}
