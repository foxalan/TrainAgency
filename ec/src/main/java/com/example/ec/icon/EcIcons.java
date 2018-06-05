package com.example.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @author alan
 *
 */

public enum EcIcons implements Icon {

    icon_scan('\ue602'),
    /**
     * 支付宝图标
     */
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
