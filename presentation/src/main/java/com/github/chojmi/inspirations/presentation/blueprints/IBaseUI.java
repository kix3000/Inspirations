package com.github.chojmi.inspirations.presentation.blueprints;

import com.github.chojmi.inspirations.presentation.InspirationsApp;
import com.github.chojmi.inspirations.presentation.main.Navigator;

interface IBaseUI {
    Navigator getNavigator();
    InspirationsApp getInspirationsApp();
}
