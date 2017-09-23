package com.moldedbits.genesis.passage;

import com.moldedbits.languagetools.models.Passage;

import java.util.List;

/**
 *
 * Created by therealshabi on 07/06/17
 */

interface PassageContract {

    interface IPresenter {

      void getPassages(String passageKey);
    }

    interface IView {


        void showPassages(List<Passage> passages);

    }

}
