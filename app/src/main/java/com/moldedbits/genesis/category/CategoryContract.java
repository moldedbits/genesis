package com.moldedbits.genesis.category;

import com.moldedbits.languagetools.models.Category;

import java.util.List;

/**
 *
 * Created by therealshabi on 07/06/17
 */

interface CategoryContract {

    interface IPresenter {

      void getCategories();
    }

    interface IView {


        void showCategories(List<Category> categories);

    }

}
